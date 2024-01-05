function selectAllCheckboxes() {
    var selectAllCheckbox = document.getElementById("selectAll");
    var checkboxes = document.querySelectorAll('input[type=checkbox]');
    checkboxes.forEach(function (checkbox) {
        if (checkbox !== selectAllCheckbox) {
            checkbox.checked = selectAllCheckbox.checked;
        }
    });
}

const deleteSelected = () => {
    const selectedIds = [];
    const checkboxes = document.querySelectorAll('input[type=checkbox]:checked');

    checkboxes.forEach(function (checkbox) {
        // Kiểm tra xem ô kiểm có phải là "selectAll" hay không
        if (checkbox.id !== "selectAll") {
            selectedIds.push(checkbox.value);
        }
    });

    const form = document.createElement('form');
    form.method = 'POST';
    form.action = '/deleteCustomers';

    selectedIds.forEach(function (id) {
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'customer';
        input.value = id;
        form.appendChild(input);
    });

    document.body.appendChild(form);
    form.submit();
};

$(document).ready(function () {
    $('#confirmDeleteModal').on('show.bs.modal', function (event) {
        const deleteButton = $('#deleteButton');
        deleteButton.attr('onclick', 'deleteSelected()');
    });

    $('#makeInActive').on('click', function () {
        const selectedCustomerIds = [];

        $('input[type=checkbox]:checked').each(function () {
            selectedCustomerIds.push(parseInt($(this).val()));
        });

        if (selectedCustomerIds.length === 0) {
            $('#DeleteModal .modal-title').text("ERROR");
            $('#DeleteModal .modal-body').text("No data to delete!");
            $('#DeleteModal').modal('show');
        } else {
            $('#confirmDeleteModal').modal('show');
        }
    });
});

function updateSelectedCustomers() {
    var selectedCustomer = [];
    $("input[name='customer']:checked").each(function () {
        var customerID = $(this).val();
        selectedCustomer.push(customerID);
    });

    if (selectedCustomer.length !== 1) {
        $('#DeleteModal .modal-title').text("ERROR");
        $('#DeleteModal .modal-body').text("No data to update!");
        $('#DeleteModal').modal('show');
        return;
    }
    window.location.href = '/updateCustomer?id=' + selectedCustomer[0];
}