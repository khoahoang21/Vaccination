$(document).ready(function() {
    $('#confirmDeleteModal').on('show.bs.modal', function () {
        var deleteButton = $('#deleteButton');
        deleteButton.attr('onclick', 'deleteSelected()');
    });

    $('#makeInActive').on('click', function() {
        var selectedinjectionResulltIds = [];
        var isInactiveSelected = false;

        $('input[type=checkbox]:checked').each(function () {
            selectedinjectionResulltIds.push($(this).val());
        });

        if (selectedinjectionResulltIds.length === 0) {
            $('#DeleteModal .modal-title').text("ERROR");
            $('#DeleteModal .modal-body').text("No data to make inactive!");
            $('#DeleteModal').modal('show');

        } else {
            selectedinjectionResulltIds.forEach(function(injectionResultId) {
                var statusSpan = $("input[type=checkbox][value='" + injectionResultId + "']").closest("tr").find("td span");
                if(statusSpan.text() === "In-Active"){
                    return isInactiveSelected = true;
                }
                return null;
            });
            if (isInactiveSelected) {
                $('#DeleteModal .modal-title').text("ERROR");
                $('#DeleteModal .modal-body').text("Invalid data - please recheck your selects!");
                $('#DeleteModal').modal('show');

            } else {
                $('#confirmDeleteModal').modal('show');
            }
        }
    });
});
function selectAllCheckboxes() {
    var selectAllCheckbox = document.getElementById("selectAll");
    var checkboxes = document.querySelectorAll('input[type=checkbox]');
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = selectAllCheckbox.checked;
    });
}
// $('#selectAll').on('click',function (){
//     $('input[type=checkbox]').prop('checked',this.checked);
// })
function deleteSelected() {
    var selectedIds = [];
    var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');

    checkboxes.forEach(function(checkbox) {
        selectedIds.push(checkbox.value);
    });
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = '/injectionResultDeleteWithCheckbox';
    selectedIds.forEach(function (id) {
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'injectionResultIds';
        input.value = id;
        form.appendChild(input);
    });
    document.body.appendChild(form);
    form.submit();
}
    $(document).ready(function() {
    $('#confirmDeleteModal_2').on('show.bs.modal', function () {
        var updateButton = $('#updateButton');
        updateButton.attr('onclick', 'updateSelected()');
    });



    $('#updateWithId').on('click', function() {
    var selectedinjectionResulltIds = [];
    var isInactiveSelected = false;

    $('input[type=checkbox]:checked').each(function () {
    selectedinjectionResulltIds.push($(this).val());
});


    if (selectedinjectionResulltIds.length === 0) {
    $('#UpdateModal .modal-title').text("ERROR");
    $('#UpdateModal .modal-body').text("No data to make inactive!");
    $('#UpdateModal').modal('show');

}
    else if (selectedinjectionResulltIds.length > 1){
    var checkboxes = document.getElementsByName("selectedResults");
    var checkedCount = 0;

    for(var i = 0; i < checkboxes.length; i++){
    if (checkboxes[i].checked){
    checkedCount++;
}
}
    if (checkedCount === 1){
    return true;
} else {
    $('#UpdateModal .modal-title').text("ERROR");
    $('#UpdateModal .modal-body').text("Please choose 1 only!");
    $('#UpdateModal').modal('show');
}
}
    else {
    selectedinjectionResulltIds.forEach(function(injectionResultId) {
    var statusSpan = $("input[type=checkbox][value='" + injectionResultId + "']").closest("tr").find("td span");
    if(statusSpan.text() === "In-Active"){
    return isInactiveSelected = true;
}
    return null;
});
    if (isInactiveSelected) {
    $('#UpdateModal .modal-title').text("ERROR");
    $('#UpdateModal .modal-body').text("Invalid data - please recheck your selects!");
    $('#UpdateModal').modal('show');

} else {
    $('#confirmDeleteModal_2').modal('show');
}
}
});
});
    function selectAllCheckboxes() {
    var selectAllCheckbox = document.getElementById("selectAll");
    var checkboxes = document.querySelectorAll('input[type=checkbox]');
    checkboxes.forEach(function (checkbox) {
    checkbox.checked = selectAllCheckbox.checked;
});
}
    // $('#selectAll').on('click',function (){
    //     $('input[type=checkbox]').prop('checked',this.checked);
    // })
    function updateSelected() {
        var selectedIds = [];
        var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');

        checkboxes.forEach(function(checkbox) {
            selectedIds.push(checkbox.value);
        });
        var form = document.createElement('form');
        form.method = 'GET';
        form.action = '/injectionResultEdit';
        selectedIds.forEach(function (id) {
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'injectionResultID';
            input.value = id;
            form.appendChild(input);
        });
        document.body.appendChild(form);
        form.submit();
    }