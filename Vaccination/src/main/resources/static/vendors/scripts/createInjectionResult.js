

document.getElementById("submitButton").addEventListener("click", function() {
    // Lấy giá trị từ input
    var numberOfInjectionInput = document.getElementById("numberOfInjection");
    var numberOfInjectionValue = numberOfInjectionInput.value;

    // Kiểm tra nếu giá trị rỗng hoặc không hợp lệ (ví dụ: không phải số), thì gán giá trị mặc định là 0
    if (numberOfInjectionValue === "" || isNaN(numberOfInjectionValue)) {
        numberOfInjectionInput.value = "0";
    }

    // Gửi dữ liệu lên máy chủ
    // document.getElementById("injectionResultForm").submit();
});


var originalId = document.getElementById('injectionResultID').value;
function validateForm() {
    var currentId = document.getElementById('injectionResultID').value;
    if (currentId !== originalId) {
        var modal = document.getElementById("idError");
        modal.style.display = "block";
        document.getElementById('idError').innerText = 'injectionResultID has changed!';
        return false;
    } else {
        document.getElementById('idError').innerText = '';
        return true;
    }
}