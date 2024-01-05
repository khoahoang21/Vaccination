// Lắng nghe sự kiện khi người dùng chọn radio button
const radioButtons = document.querySelectorAll('input[type="radio"]');
radioButtons.forEach(radioButton => {
    radioButton.addEventListener('change', function() {
        if (this.value === 'report') {
            // Chuyển đến trang "report.html" khi chọn "Report"
            window.location.href = '/reportInjectionResult';
        } else if (this.value === 'chart') {
            // Chuyển đến trang "chart.html" khi chọn "Chart"
            window.location.href = '/reportInjectionResultChart';
        }
    });
});