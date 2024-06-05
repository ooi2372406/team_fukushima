document.getElementById('show-password').addEventListener('change', function() {
    var passwordInput = document.getElementById('password');
    if (this.checked) {
        passwordInput.type = 'text';
    } else {
        passwordInput.type = 'password';
    }
});

document.addEventListener('DOMContentLoaded', function() {
	var input = document.querySelector('.exampleInputEmail1');
    var formGroup = document.querySelector('.form-group');

    input.addEventListener('focus', function() {
        formGroup.classList.add('highlight');
    });

    input.addEventListener('blur', function() {
        formGroup.classList.remove('highlight');
    });
});
