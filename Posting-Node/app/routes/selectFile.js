var express = require('express');
var router = express.Router();

router.post('/upload', function (req, res) {
    console.log("I am here");
    let sampleFile = req.files.sampleFile;

    console.log("it is" + sampleFile);
});
module.exports = router;