var express = require('express');
var router = express.Router();

router.get('/selectFile/:filename', function(req,res) {
    console.log("I am here");
    console.log(req.params.filename);
});
module.exports = router;
