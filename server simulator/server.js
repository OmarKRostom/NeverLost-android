var express = require('express');
var app = express();
var sampleResponse = {
  "username": "test_user",
  "data": [
    {
      "provider": "provider_1",
      "password": "provider_1_pass"
    },
    {
      "provider": "provider_2",
      "password": "provider_2_pass"
    }
  ]
};

// This responds a GET request for the homepage /
app.get('/', function(req, res) {
  console.log("Got a GET request for the homepage");
  res.send('Welcome to Borio !!!');
})

// This responds a GET request for /sample
app.get('/sample', function(req, res) {
  console.log("Got a GET request for the sample page");
  res.json(sampleResponse);
  // res.send(JSON.stringify(sampleResponse));
  // res.status(500).send('Something broke!');
  // res.status(404).end();
})

// This responds a POST request for /sample
app.post('/sample', function(req, res) {
  console.log("Got a POST request for the sample page");
  console.log(req);
  res.send('Done');
})

var server = app.listen(7575, function() {
  var host = server.address().address
  var port = server.address().port
  console.log("Server Simulator app listening on port %s", port)
})
