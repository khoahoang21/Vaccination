function handleFormSubmission() {
    var myForm = document.getElementById("myForm");
    var submitButton = document.getElementById("submitButton");

    myForm.addEventListener("submit", function(event) {
        // Prevent the form from submitting immediately
        event.preventDefault();

        // Disable the submit button
        submitButton.disabled = true;

        // Submit the form (optional)
        myForm.submit();
    });
}

// Call the handleFormSubmission() function at the end of the body to ensure that the HTML content is loaded
handleFormSubmission();