var mysql = require('mysql');
const express = require('express');
const sessions = require('express-session');
const fs = require('fs');
const crypto = require('crypto');
const cookieParser = require('cookie-parser');
const session = require('express-session');
const res = require('express/lib/response');
const { fail } = require('assert');

//MySQL Connection
const conn = mysql.createConnection({
    host: "localhost",
    user: "service",
    password: "service_engine",
    database: "argonotes"
});

conn.connect(function(err){
    if (err) throw err;
    console.log("Connected to MySQL");
});


//Main Server App

// Build Pipe
const app = express();

//Session middle-ware
const oneDay = 1000*60*60*24; //milliseconds

app.use(sessions({
    secret: crypto.randomUUID(),
    saveUninitialized: false,
    cookie:{maxAge: oneDay},
    resave: false
}));

app.use(
    express.urlencoded({
        extended: true
    })
);

app.use(express.json());
app.use(express.static('web_content'));
app.use(cookieParser());


//Make Workflows


app.post("/login", async (req,res)=>{
    var session_info;
    var q = "select (\'"+req.body.pword+"\'=user_pass) as auth, user_acc from accounts where user_acc in  (select uid from users where alias=\'"+req.body.alias+"\' or email=\'"+req.body.alias+"\')";
    conn.query(q,async (err, result, fields)=>{
        if (err) console.log(err);
        else{
            //console.log(result);
            if(result.length == 0){
                res.response_code = 307;
                res.send("USER NOT FOUND");
            }
            else if(result.length > 1) res.redirect("index1.html");
            else{
                if(result[0].auth === 0){
                    res.response_code = 401;
                    res.send("NOT AUTH");
                }
                else {
                    
                    q = "update user_state set is_active=1 where uid=(select uid from users where alias=\'"+req.body.alias+"\')";
                    conn.query(q, (err, result)=>{
                        if(err) console.log(err);
                        else console.log("Login Update success" + req.body.user);
                    });
                    //console.log(result[0].user_acc);
                    session_info = req.session;
                    session_info.userActive = true;
                    session_info.user = result[0].user_acc;
                    //console.log(req.session);
                    var datagram = {
                        user: session_info.user,
                        notes: null
                    };
                    res.send(datagram);
                }
        }
    }
});
});


// on logout destroy session
app.post('/logout', (req, res)=>{
    //destroy session in db also
    console.log(req.body);
    q = "update user_state set is_active=0, last_active=current_date() where uid=\'"+req.body.user+"\'";
    conn.query(q, (err, result)=>{
        if(err) console.log(err);
        else console.log("Logout success" + req.body.user);
    });
    session_info = req.session;
    session_info.userActive = false;
    req.session.destroy((err)=>console.log(err));
    res.redirect('/');
    console.log("User is active: "+ session_info.userActive + " Visits: " + session_info.visits);
});

app.post("/signup", (req,res)=>{
    var q = ("select * from users where email=\'"+req.body.email+"\' or alias=\'"+req.body.alias+"\';");
    conn.query(
        q,
        function(err,result,fields){
            if(err){console.log(err)}
            else{            
                if (result.length > 0) {
                    return res.redirect('/index1.html');
                } else {
                    var q = "insert into users (alias, first_name, last_name, dob, email, role_id) values (\'"+req.body.alias+"\',\'"+req.body.first_name+"\',\'"+req.body.last_name+"\',\'"+req.body.dob+"\',\'"+req.body.email+"\',"+parseInt(req.body.role)+");";
                    conn.query(
                        q,
                        function(err, result, fields){
                            if(err) throw err;
                            else{
                                conn.query(" update accounts set user_pass=\'"+req.body.pword+"\' where user_acc = (select uid from users where email=\'"+req.body.email+"\' limit 1) limit 1", (err, result,fields)=>{
                                    if(err) throw err;
                                    else{
                                        conn.query("select uid from users where email=\'"+req.body.email+"\';",(err,result,fields)=>{
                                            if(err) throw err;
                                            else{
                                                //Do useful things here
                                                res.send({response_code: 110, response_def: "User Created", auth_id: result[0].uid});
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    );
                }
            }
        }
    );
});

app.post('/mynotes',  async (req,res)=>{
    //console.log(req.body.uid);
    var datagram = {
        user: req.body.uid,
        notes: null
    };
    var q = "select note_id from notes where user_id=\'"+req.body.uid+"\'";
    const dgram = await new Promise((success,fail)=>{
        conn.query(q, async function(err,result){
            if (err) fail(err);
            else{
                const datagram = result;
                success(datagram);
            }
        });
    });
    var notes = [];
    for(var note in dgram){
        //console.log(dgram[note]);
        notes.push(dgram[note].note_id);
    }
    datagram.notes = notes;
    console.log(datagram);
    res.send(datagram);
    // res.send("****");
});

app.post('/collectNote', async (req,res)=>{
    var datagram = {};
    console.log("USER: " + req.body.uid + "\t\t NOTE: " + req.body.nid);
    var q = "select * from notes where user_id=\'"+req.body.uid+"\' and note_id=\'"+req.body.nid+"\'";
    const dgram = await new Promise((success,fail)=>{
        conn.query(q, async function(err,result){
            if (err) fail(err);
            else{
                const datagram = result;
                success(datagram);
            }
        });
    });
    datagram = JSON.parse(JSON.stringify(dgram[0]));
    console.log(datagram);
    res.send(datagram);
});

app.post('/saveNote', (req,res)=>{
    var q = `insert into argonotes.notes (note_id, cluster_id, user_id, title, contents, updated_at, is_collab) values (\'${req.body.nid}\', \'${req.body.cid}\',\'${req.body.uid}\',\'${req.body.title}\',\'${req.body.content}\',current_time(),${req.body.is_collab}) on duplicate key update title = \'${req.body.title}\', contents=\'${req.body.content}\', updated_at=current_time()`;
    conn.query(q, async function(err,result){
        if (err) fail(err);
        else{
            console.log("note saved");
            res.send("note saved");
        }
    });
});

app.get('/',(req,res)=>{
    res.redirect("index.html");
});

app.get('/index', (req,res)=>{
    res.sendFile("web_content/index.html",{root:__dirname});
});

//Get Server moving
var server = app.listen(8080,()=>{
    var host = server.address().address
    var port = server.address().port
    console.log("Argo Notes Application listening at http://%s:%s",host,port);
});