
    function makeTableScroll() {
    // Constant retrieved from server-side via JSP
    var maxRows = 4;

    var table = document.getElementById('myTable');
    var wrapper = table.parentNode;
    var rowsInTable = table.rows.length;
    var height = 0;
    if (rowsInTable > maxRows) {
    for (var i = 0; i < maxRows; i++) {
    height += table.rows[i].clientHeight;
}
    wrapper.style.height = height + "px";
}
}

    //Data from Controller
    var Data = [[${injectionResultList}]];  //[Month,Value]

    var allMonths = ["January", "February", "March", "April", "May",
    "June", "July", "August", "September", "October", "November", "December"];

    //Array for value
    var ValueData = new Array(12).fill(0);

    // Update data into ValueData
    Data.forEach(function(item) {
    var monthIndex = allMonths.indexOf(item.split(',')[0]);  //Split from [Month,Value] to only [Month]
    var count = parseInt(item.split(',')[1]);                //Parsing value into int
    if (monthIndex !== -1) {                                 //If Month from Data is match with
    ValueData[monthIndex] = count;                      //monthIndex then set the Value from Data into ValueData[monthIndex] by monthIndex
}
});

    // Generate barchart
    var chartData = {
    labels: allMonths,
    datasets: [{
    label: "Number Of Injection",
    data: ValueData,
    backgroundColor: 'rgba(75, 192, 192, 0.5)',
    borderColor: 'rgba(75, 192, 192, 3)',
    borderWidth: 1
}]
};

    var ctx = document.getElementById("bar").getContext("2d");
    new Chart(ctx, {
    type: 'bar',
    data: chartData
});


<!-- Chart JS  -->
    //Data from Controller
    var Data = [[${injectionResultList}]];  //[Month,Value]

    var allMonths = ["January", "February", "March", "April", "May",
    "June", "July", "August", "September", "October", "November", "December"];

    //Array for value
    var ValueData = new Array(12).fill(0);

    // Update data into ValueData
    Data.forEach(function(item) {
    var monthIndex = allMonths.indexOf(item.split(',')[0]);  //Split from [Month,Value] to only [Month]
    var count = parseInt(item.split(',')[1]);                //Parsing value into int
    if (monthIndex !== -1) {                                 //If Month from Data is match with
    ValueData[monthIndex] = count;                      //monthIndex then set the Value from Data into ValueData[monthIndex] by monthIndex
}
});

    // Generate barchart
    var chartData = {
    labels: allMonths,
    datasets: [{
    label: "Number Of Injection",
    data: ValueData,
    backgroundColor: 'rgba(75, 192, 192, 0.5)',
    borderColor: 'rgba(75, 192, 192, 3)',
    borderWidth: 1
}]
};

    var ctx = document.getElementById("bar").getContext("2d");
    new Chart(ctx, {
    type: 'bar',
    data: chartData
});