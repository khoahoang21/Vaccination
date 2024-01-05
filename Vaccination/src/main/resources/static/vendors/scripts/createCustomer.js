//check password
document.addEventListener("DOMContentLoaded", () => {
    const passwordInput = document.getElementById("password");
    const confirmPasswordInput = document.getElementById("confirmPassword");
    const passwordMatchError = document.getElementById("passwordMatchError");
    const submitButton = document.querySelector(".customerForm button[type='button']");

    submitButton.addEventListener("click", (e) => {
        const password = passwordInput.value;
        const confirmPassword = confirmPasswordInput.value;

        if (password !== confirmPassword) {
            e.preventDefault();
            passwordMatchError.style.display = "block";
        }
    });
});

function showRecaptcha() {
    document.getElementById('recaptchaPopup').style.display = 'block';
}

function verifyRecaptcha() {
    // Kiểm tra xem Recaptcha đã được xác nhận chưa
    var recaptchaResponse = grecaptcha.getResponse();
    if (recaptchaResponse === '') {
        openErrorModal();
        return;
    }// window.location.href = '/saveCustomer';
}
function openErrorModal() {
    var modal = document.getElementById("errorModal");
    modal.style.display = "block";
}

function openErrorModal2() {
    var modal = document.getElementById("errorModal");
    modal.style.display = "none";
    var modal1 = document.getElementById("errorModal2");
    modal1.style.display = "block";
}

function closeErrorModal() {
    var modal = document.getElementById("errorModal");
    modal.style.display = "none";
}
function closeErrorModal2() {
    var modal1 = document.getElementById("errorModal2");
    modal1.style.display = "none";
    var modal = document.getElementById("errorModal");
    modal.style.display = "block";
}

function openCheckModalSubmit() {
    var recaptchaResponse = grecaptcha.getResponse();
    if (recaptchaResponse === '') {
        openErrorModal2();
        // alert('Please complete the Recaptcha verification.');
        return;
    }else{
        document.getElementById('myForm').submit();
        document.getElementById('recaptchaPopup').style.display = 'none';
    }
}