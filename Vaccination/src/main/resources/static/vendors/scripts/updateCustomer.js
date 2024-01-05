document.addEventListener("DOMContentLoaded", () => {
    const passwordInput = document.getElementById("password");
    const confirmPasswordInput = document.getElementById("confirmPassword");
    const passwordMatchError = document.getElementById("passwordMatchError");
    const submitButton = document.querySelector(".customerForm button[type='submit']");

    submitButton.addEventListener("click", (e) => {
        const password = passwordInput.value;
        const confirmPassword = confirmPasswordInput.value;

        if (password !== confirmPassword) {
            e.preventDefault();
            passwordMatchError.style.display = "block";
        }
    });
});