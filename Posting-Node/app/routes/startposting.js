var express = require('express');
var router = express.Router();

router.get('/startposting', function (req, res) {
    var webdriverio = require('webdriverio');
    var options = {
        desiredCapabilities: {
            browserName: 'chrome'
        }
    };
    var client = webdriverio.remote(options);


    client
        .init()
        .url('https://duckduckgo.com/')
        .setValue('#search_form_input_homepage', 'WebdriverIO')
        .click('#search_button_homepage')
        .getTitle().then(function (title) {
            console.log('Title is: ' + title);
            // outputs: "Title is: WebdriverIO (Software) at DuckDuckGo"
        })
        .end();
});



module.exports = router;