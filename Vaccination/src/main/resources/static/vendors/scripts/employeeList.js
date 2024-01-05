
function selectAllCheckboxes() {
    var checkboxList = document.querySelectorAll("input[type='checkbox']");
    for (var j = 0; j < checkboxList.length; j++) {
        checkboxList[j].checked = document.getElementById("selectAll").checked;
    }
}

//     function checkCheckboxes() {
//     var checkboxList = document.querySelectorAll("input[type='checkbox']");
//     var values = [];
//     for (var j = 0; j < checkboxList.length; j++) {
//     if (checkboxList[j].checked) {
//     values.push(checkboxList[j].value);
// }
// }
//     var link = document.getElementById('deleteButton');
//     link.href = "/deleteemployee?ids=" + values.join(',');
// }
//     document.getElementById("deleteButton").addEventListener("click", checkCheckboxes);


function checkCheckboxes() {
    var checkboxList = document.querySelectorAll("input[type=checkbox]:checked");
    var values = [];

    checkboxList.forEach(function (checkbox) {
        // Kiểm tra xem ô kiểm có phải là "selectAll" hay không
        if (checkbox.id !== "selectAll") {
            values.push(checkbox.value);
        }
    });
    var link = document.getElementById('deleteButton');
    link.href = "/deleteemployee?ids=" + values.join(',');
}
document.getElementById("deleteButton").addEventListener("click", checkCheckboxes);

function disablePosition1() {
    // Ngăn chặn việc chọn giá trị cho position1
    document.getElementById("position1").disabled = true;
}

function validateUpdateForm() {
    // Lấy giá trị mới của position
    var newPosition = document.getElementById("position1").value;

    // Lấy giá trị hiện tại của position
    var currentPosition = document.getElementById("currentPosition").value;

    // Kiểm tra xem giá trị mới của position có khác với giá trị hiện tại không
    if (newPosition !== currentPosition) {
        alert("Không được chỉnh sửa giá trị của position.");
        return false; // Ngăn chặn việc submit form
    }

    // Nếu mọi thứ hợp lệ, cho phép submit form
    return true;
}