function cancelForm(){
    window.location = "[[@{/injectionScheduleList}]]";
}

var originalId = document.getElementById('injectionScheduleID').value;
function validateForm() {
    var currentId = document.getElementById('injectionScheduleID').value;
    if (currentId !== originalId) {
        var modal = document.getElementById("idError");
        modal.style.display = "block";
        document.getElementById('idError').innerText = 'InjectionScheduleID has changed!';
        return false;
    } else {
        document.getElementById('idError').innerText = '';
        return true;
    }
}