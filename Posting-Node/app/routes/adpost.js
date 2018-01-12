var express = require('express');
var router = express.Router();

router.get('/adposting', function(req, res) {
  res.render('post', {
    pageTitle: 'App',
        pageID: 'Posting'
  });
});

module.exports = router;
