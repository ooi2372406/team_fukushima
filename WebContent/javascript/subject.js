document.getElementById('subjectForm').addEventListener('submit', function(event) {
    let subjectCode = document.querySelector('input[name="cd"]').value;

    let lengthError = document.getElementById('lengthError');

    let isAlphanumeric = /^[A-Za-z0-9]+$/.test(subjectCode);
    let isValidLength = subjectCode.length === 3;


    if (!isValidLength) {
        lengthError.textContent = '科目コードは3文字で入力してください。';
        lengthError.style.display = 'block';
    } else {
        lengthError.style.display = 'none';
    }

    if (!isAlphanumeric || !isValidLength) {
        event.preventDefault();
    }
});


//inputフィールドをクリックしたときにエラーメッセージを非表示にする
document.querySelectorAll('input').forEach(function(input) {
    input.addEventListener('focus', function() {
        document.getElementById('lengthError').style.display = 'none';
        document.getElementById('serverError').style.display = 'none';
    });
});