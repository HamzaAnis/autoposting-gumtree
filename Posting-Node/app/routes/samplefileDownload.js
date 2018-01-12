var express = require('express');
var router = express.Router();

router.get('/samplefileDownload', function(req, res) {
    var file=

 res.download("../Posting-Node/app/public/data/sample.csv")
});

module.exports = router;
