var express = require('express');
var reload = require('reload');
const fileUpload = require('express-fileupload');
var app = express();

app.set('port', process.env.PORT || 3000);
app.set('view engine', 'ejs');
app.set('check', 'checking');
app.set('views', 'app/views');

app.use(fileUpload());
app.use(express.static('app/public'));
app.use(require('./routes/index'));
app.use(require('./routes/samplefileDownload'));
app.use(require('./routes/adpost'));
// app.use(require('./routes/selectFile'));

app.post('/upload', function (req, res) {
    console.log("Uploading");
    if (!req.files) {
        console.log('No files were uploaded.');
        return res.status(400).send('No files were uploaded.');
    }
    console.log(req.files);

    sampleFile = req.files.sampleFile;
	sampleFile.mv(__dirname+'/file/' + sampleFile.name, (err) => {
		if (err) {
            console.log("Error");
			return res.status(500).send(err);
		}
		res.status(400).send('file uploaded.');
	});
});

var server = app.listen(app.get('port'), function () {
    console.log('Listening on http://localhost:' + app.get('port'));
});

reload(server, app);