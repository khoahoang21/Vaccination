function createForm(){
    window.location = "[[@{/createInjectionSchedule}]]";
}

function validateForm() {
    var checkboxes = document.getElementsByName("injectrionScheduleID");
    var checkedCount = 0;

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            checkedCount++;
        }
    }

    if (checkedCount === 1) {
        return true;
    } else {
        openErrorModal();
        return false;
    }
}

function openErrorModal() {
    var modal = document.getElementById("errorModal");
    modal.style.display = "block";
}

function closeErrorModal() {
    var modal = document.getElementById("errorModal");
    modal.style.display = "none";
}