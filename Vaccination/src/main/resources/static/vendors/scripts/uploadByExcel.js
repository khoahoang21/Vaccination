function checkFileExtension() {
    const fileInput = document.querySelector('input[type="file"]');
    const filePath = fileInput.value;
    const allowedExtensions = /(\.xlsx|\.xls)$/i;

    if (fileInput.files.length === 0) {
        const errorText = document.getElementById('errorText');
        errorText.innerText = 'Choose Excel file to import!!!';
        $('#errorModal').modal('show');
        return false;
    }

    if (!allowedExtensions.exec(filePath)) {
        const errorText = document.getElementById('errorText');
        errorText.innerText = 'Only Excel files allowed!!!';
        $('#errorModal').modal('show');
        return false;
    }
    return true;
}