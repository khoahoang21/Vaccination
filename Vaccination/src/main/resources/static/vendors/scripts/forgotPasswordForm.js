function confirmCode() {
    var email = document.getElementById('email').value;
    var code = document.getElementById('code').value;

    fetch('/reset/confirm?email=' + email + '&code=' + code, { method: 'POST' })
        .then(response => response.json())
        .then(data => {
            document.getElementById('statusMessage').innerText = data.status;
            if (data.status === "Confirm Succesfully") {
                window.location.href = "/resetNew?email=" + email + "&code=" + code;
            }
        })
        .catch(error => console.error('Error:', error));
}

<!-- countdown -->

var countdownNumberEl = document.getElementById('countdown-number');
var countdown = 60;
countdownNumberEl.textContent = countdown;
function updateCountdown() {
    var seconds = countdown % 60;
    seconds = seconds < 60 ? "0" + seconds : seconds;
    document.getElementById("countdown-number").innerText = seconds;
    countdown-- ; //<= 0 ? 10 : countdown
    if(countdown === 0){
        document.getElementById('countdown-number').innerText = "0";
        document.querySelector("#resend-button").style.display = "inline-block";
        document.querySelector("#submit-button").style.display = "none";
    } else{
        setTimeout(updateCountdown, 1000);
        document.querySelector("#resend-button").style.display = "none";
        document.querySelector("#submit-button").style.display = "inline-block";
    }
    countdownNumberEl.textContent = countdown;
}
updateCountdown();
