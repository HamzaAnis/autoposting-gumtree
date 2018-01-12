function showMessages() {
    var uploadform = document.forms.uploadform;
    if (uploadform) {
        uploadform.addEventListener('submit', function (e) {
            e.preventDefault();
            console.log("Check is "+e.res);
        }); //chatform event
        console.log("Now I am here");
        var file = document.querySelector('#file-select ');
        console.log("it is " + file.value);
        window.location.href="/selectFile";
    }
}