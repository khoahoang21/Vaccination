// js for popup and delete function
function prepareModal() {
    const checkboxes = document.querySelectorAll('#table-view input[type="checkbox"]:checked');

    if (checkboxes.length > 0) {
        // Set up the modal for deletion confirmation
        $('#notificationModalLabel').text('Confirm Delete');
        $('#notificationModalBody').text('Are you sure you want to delete this news?');
        $('#confirmDeleteButton').show();
        $('#notificationModal').modal('show');
    } else {
        // Set up the modal to notify the user
        $('#notificationModalLabel').text('Notification');
        $('#notificationModalBody').text('No news has been selected.');
        $('#confirmDeleteButton').hide(); // hide the delete button
        $('#notificationModal').modal('show');
    }
}
