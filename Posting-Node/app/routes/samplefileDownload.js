var express = require('express');
var router = express.Router();

router.get('/samplefileDownload', function(req, res) {
    console.log("Downloading");
 res.download("../Posting-Node/app/public/data/sample.csv")
});

module.exports = router;
