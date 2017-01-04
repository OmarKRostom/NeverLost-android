var express = require('express');
var app = express();
var bodyParser = require('body-parser');

// parse application/json
app.use(bodyParser.json())

var sampleResponse = {
  "username": "test_user",
  "data": [
    {
      "provider": "provider_1",
      "username": "name_1",
      "password": "provider_1_pass"
    },
    {
      "provider": "anghami",
      "username": "name_anghami",
      "password": "anghami_password"
    },
    {
      "provider": "facebook",
      "username": "name_facebook",
      "password": "facebook_password"
    },
    {
      "provider": "gmail",
      "username": "name_gmail",
      "password": "gmail_password"
    },
    {
      "provider": "google",
      "username": "name_google",
      "password": "google_password"
    },
    {
      "provider": "instagram",
      "username": "name_instagram",
      "password": "instagram_password"
    },
    {
      "provider": "medium",
      "username": "name_medium",
      "password": "medium_password"
    },
    {
      "provider": "quora",
      "username": "name_quora",
      "password": "quora_password"
    },
    {
      "provider": "soundcloud",
      "username": "name_soundcloud",
      "password": "soundcloud_password"
    },
    {
      "provider": "spotify",
      "username": "name_spotify",
      "password": "spotify_password"
    },
    {
      "provider": "twitter",
      "username": "name_twitter",
      "password": "twitter_password"
    },
    {
      "provider": "provider_2",
      "username": "name_2",
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
  console.log(req.body);
  res.status(200).end();
})

var server = app.listen(7575, function() {
  var host = server.address().address
  var port = server.address().port
  console.log("Server Simulator app listening on port %s", port)
})
