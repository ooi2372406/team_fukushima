document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('subjectForm').addEventListener('submit', function(event) {
        let subjectCode = document.querySelector('input[name="cd"]').value;

        let lengthError = document.getElementById('lengthError');
        let serverError = document.getElementById('serverError');

        let isAlphanumeric = /^[A-Za-z0-9]+$/.test(subjectCode);
        let isValidLength = subjectCode.length === 3;

        if (lengthError) {
            if (!isValidLength) {
                lengthError.textContent = '科目コードは3文字で入力してください。';
                lengthError.style.display = 'block';
            } else if (!isAlphanumeric) {
                lengthError.textContent = '科目コードは半角英字または数字で入力してください。';
                lengthError.style.display = 'block';
            } else {
                lengthError.style.display = 'none';
            }
        }

        if (!isAlphanumeric || !isValidLength) {
            event.preventDefault();
        }
    });

    // inputフィールドをクリックしたときにエラーメッセージを非表示にする
    document.querySelectorAll('input').forEach(function(input) {
        input.addEventListener('focus', function() {
            let lengthError = document.getElementById('lengthError');
            let serverError = document.getElementById('serverError');

            if (lengthError) {
                lengthError.style.display = 'none';
            }

            if (serverError) {
                serverError.style.display = 'none';
            }
        });
    });

    window.addEventListener('resize', () => {
        let vh = window.innerHeight * 0.01;
        document.documentElement.style.setProperty('--vh', `${vh}px`);
    });
});
