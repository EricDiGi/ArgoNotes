var mysql = require('mysql');
const express = require('express');
const fs = require('fs');

//MySQL Connection
var conn = mysql.createConnection({
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

app.use(
    express.urlencoded({
        extended: true
    })
);

app.use(express.json());

app.use(express.static('web_content'));

//Make Workflows
app.get("/interactive",(req,res)=>{
    var qs = req.query;
    var cont = req.body;
    var mode = qs.mode;
    console.log("User requested mode: %s\nContent incoming:\n%s", mode, cont);
    
    conn.query(
        "select * from users limit 1",
        function (err, result, fields) {
            if (err) throw err;
            res.send(result);
    });
});

app.get("/login", (req,res)=>{
    res.send("<html><body><h3>This is a login page</h3></body></html>")
});

app.post("/signup", (req,res)=>{
    var q = ("select * from users where email=\'"+req.body.email+"\';");
    conn.query(
        q,
        function(err,result,fields){
            if(err){console.log(err)}
            else{            
                if (result.length > 0) {
                    res.send({response_code: 111, response_def: "User Exists", auth_id: result.uid})
                    //return res.redirect('/login');
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

app.post("/login",(req,res)=>{
    res.send("This is a login page");
});

app.get('/',(req,res)=>{
    res.send("<html><body><h3>This is Argo Notes</h3></body></html>");
});


//Get Server moving
var server = app.listen(8080,()=>{
    var host = server.address().address
    var port = server.address().port

    console.log("Argo Notes Application listening at http://%s:%s",host,port);
});