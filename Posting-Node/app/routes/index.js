var express = require('express');
var router = express.Router();

router.get('/', function(req, res) {
  res.render('index', {
    pageTitle: 'Gumtree Posting',
    pageID: 'home'
  });
});

module.exports = router;
