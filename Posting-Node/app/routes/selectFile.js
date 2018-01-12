var express = require('express');
var router = express.Router();

router.post('/selectFile', function(req,res) {
    // Create a new connection
    var fs = require('fs');
    var multiparty = require('multiparty');
    var util = require('util');
    var Connection = require('ssh2');
    var c = new Connection();
    var form = new multiparty.Form();
    form.parse(req, function(err, fields, files) {
        // Each element of the object is an array
        console.log("form parsed");
        // yamlfiles is an array anyway
        var yamlfiles = files.yamlfiles;
        // username is just a text field, so the 0th element is username
        var uname = fields.username[0];
        // files is a single file, so the 0th element is my key
        var key = files.keySelect[0];
       
        // access key with key.path (will be a fakepath)
        fs.readFileSync(key.path);
        // access name with key.originalFilename
    });
});
module.exports = router;
