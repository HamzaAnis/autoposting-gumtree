var express = require('express');
var router = express.Router();

router.get('/startposting', function(req, res) {
    console.log("Posting");
});

module.exports = router;
