$(document).ready(function() {
    // Load countries into the "origin" select
    $("#origin").load("options/countries.txt", function() {
        $(this).change();
    });

    // Change event for the "origin" select
    $("#origin").change(function() {
        // Load states into the "origin" select based on the selected country
        var selectedCountry = $(this).val();
        $("#origin").load("options/" + selectedCountry + ".txt", function(data) {
            // Convert data to options and append to the "origin" select
            var lines = data.split('\n');
            for (var i = 0; i < lines.length; i++) {
                var optionValue = lines[i].trim();
                if (optionValue !== "") {
                    $("#origin").append(optionValue);
                }
            }
        });
    });
});