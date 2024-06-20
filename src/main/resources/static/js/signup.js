document.addEventListener('DOMContentLoaded', function () {
    var dobInput = document.getElementById('dob');
    var dobLabel = document.getElementById('date-label');

    // Check if the input has a value on page load
    if (dobInput.value) {
        dobLabel.style.top = '0';
        dobLabel.style.transform = 'translateY(-100%)';
        dobLabel.style.fontSize = '16px';
        dobLabel.style.color = '#fff';
    }

    // Add event listeners
    dobInput.addEventListener('focus', function () {
        dobLabel.style.top = '0';
        dobLabel.style.transform = 'translateY(-100%)';
        dobLabel.style.fontSize = '16px';
        dobLabel.style.color = '#fff';
    });

    dobInput.addEventListener('blur', function () {
        if (dobInput.value) {
            dobLabel.style.top = '0';
            dobLabel.style.transform = 'translateY(-100%)';
            dobLabel.style.fontSize = '16px';
            dobLabel.style.color = '#fff';
        }
    });
});
