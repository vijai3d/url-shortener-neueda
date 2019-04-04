var express = require('express');
var app = express();
var request = require('request');

app.use(express.static(__dirname +'./../../')); //serves the index.html

app.get("/:short", function (req, res, next) {
    request.post({url:'http://localhost:8085/rest/v1/url/short', body: req.params.short}, function(err,httpResponse,body){
        res.redirect(body)
    })
});
app.listen(3000);
