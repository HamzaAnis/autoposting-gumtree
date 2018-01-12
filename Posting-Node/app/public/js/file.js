function showMessages() {
    var uploadform = document.forms.uploadform;
    if (uploadform) {
        uploadform.addEventListener('submit', function (e) {
            e.preventDefault();
            console.log("Check is " + e.res);
        }); //chatform event
        console.log("Now I am here");
        var file = document.querySelector('#file-select');
        console.log("it is " + file.files[0].name);

        var input = document.getElementById("file-select");
        var fReader = new FileReader();
        fReader.readAsDataURL(input.files[0]);
        fReader.onloadend = function (event) {
            var img = document.getElementById("yourImgTag");
            img.src = event.target.result;
        }
        window.location.href = "/selectFile/check";
    }
}