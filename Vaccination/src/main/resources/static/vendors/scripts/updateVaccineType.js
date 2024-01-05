function previewImage(input) {
    var preview = document.getElementById('preview');
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function resetImage() {
    var preview = document.getElementById('preview');
    preview.src = "/vendors/images/banner-img.png";
    document.getElementById('image').value = ""; // Đặt giá trị của trường input file thành trống để xóa tệp đã chọn
}