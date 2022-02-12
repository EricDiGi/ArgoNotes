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
    var cont = req.end;
    var mode = qs.mode;
    console.log("User requested mode: %s\nContent incoming:\n%s", mode, cont);
    
    conn.query(
        "select * from users limit 1",
        function (err, result, fields) {
            if (err) throw err;
            res.send(result);
    });
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