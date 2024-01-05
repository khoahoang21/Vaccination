$(document).ready(function() {
    $('#confirmDeleteModal').on('show.bs.modal', function () {
        var deleteButton = $('#deleteButton');
        deleteButton.attr('onclick', 'deleteSelected()');
    });

    $('#makeInActive').on('click', function() {
        var selectedVaccinetypeIds = [];
        var isInactiveSelected = false;

        $('input[type=checkbox]:checked').each(function () {
            selectedVaccinetypeIds.push($(this).val());
        });

        if (selectedVaccinetypeIds.length === 0) {
            $('#DeleteModal .modal-title').text("ERROR");
            $('#DeleteModal .modal-body').text("No data to make inactive!");
            $('#DeleteModal').modal('show');

        } else {
            selectedVaccinetypeIds.forEach(function(vaccinetypeId) {
                var statusSpan = $("input[type=checkbox][value='" + vaccinetypeId + "']").closest("tr").find("td span");
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
function deleteSelected() {
    var selectedIds = [];
    var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');

    checkboxes.forEach(function(checkbox) {
        selectedIds.push(checkbox.value);
    });
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = '/delete';

    selectedIds.forEach(function (id) {
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'vaccineIds';
        input.value = id;
        form.appendChild(input);
    });
    document.body.appendChild(form);
    form.submit();
}