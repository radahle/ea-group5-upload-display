// grab the packages we need
var util = require('util')
var express = require('express');
var app = express();
var bodyParser = require('body-parser');

var port = process.env.PORT || 8080;


app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: false }))

// POST http://localhost:8080/api/users
// parameters sent with 


app.get('/pdfobject.js',function(req,res){
  res.sendFile("pdfobject.js", { root: __dirname });
});

app.get('/sample-3pp.pdf',function(req,res){
  res.sendFile("sample-3pp.pdf", { root: __dirname });
});

app.get('/',function(req,res){
  res.sendFile("index.html", { root: __dirname });
});

// start the server
app.listen(port);
console.log('Server started! At http://localhost:' + port);
