function checkPasswordMatch() {
    const passwordInput = document.getElementById("password");
    const confirmPasswordInput = document.getElementById("passwordConfirm");
    const passwordMatchError = document.getElementById("passwordMatchError");
    const nullValue = document.getElementById("nullEnter");

    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    if(password === "" || confirmPassword === ""){
        nullValue.style.display = "block";
        return false;
    }else if (password !== null && confirmPassword !== null) {
        if (password !== confirmPassword) {
            passwordMatchError.style.display = "block";
            nullValue.style.display = "none";
            return false; // Prevent form submission
        } else {
            passwordMatchError.style.display = "none";
            return true; // Allow form submission
        }

    }
}