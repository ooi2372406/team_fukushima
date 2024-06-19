document.getElementById('show-password').addEventListener('change', function() {
    let passwordInput = document.getElementById('password');
    if (this.checked) {
        passwordInput.type = 'text';
    } else {
        passwordInput.type = 'password';
    }
});

document.addEventListener('DOMContentLoaded', function() {
	let input = document.querySelector('.exampleInputEmail1');
    let formGroup = document.querySelector('.form-group');

    input.addEventListener('focus', function() {
        formGroup.classList.add('highlight');
    });

    input.addEventListener('blur', function() {
        formGroup.classList.remove('highlight');
    });
});

document.getElementById('subjectForm').addEventListener('submit', function(event) {
    // 入力フィールドを取得
    let subjectCode = document.querySelector('input[name="cd"]').value;
    let codeError = document.getElementById('codeError');

    // 科目コードの検証
    let isValid = /^[A-Za-z0-9]+$/.test(subjectCode);
    if (!isValid) {
        codeError.textContent = '科目コードには英文字と数字のみを入力してください。'; // エラーメッセージを設定
        codeError.style.display = 'block'; // エラーメッセージを表示
        event.preventDefault(); // フォームの送信を止める
    } else {
        codeError.style.display = 'none'; // エラーメッセージを非表示
    }
});
/*
window.addEventListener('resize', () => {
	  let vh = window.innerHeight * 0.01;
	  document.documentElement.style.setProperty('--vh', `${vh}px`);
	}); */
