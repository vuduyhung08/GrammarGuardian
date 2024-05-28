<%@ page import="java.net.URLEncoder" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Sign in</title>
    <!-- <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css"> -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <style>
        @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

        * {
            box-sizing: border-box;
        }

        body {
            background: #f6f5f7;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            font-family: 'Montserrat', sans-serif;
            height: 100vh;
            margin: -20px 0 50px;
        }

        h1 {
            font-weight: bold;
            margin: 0;
        }

        h2 {
            text-align: center;
        }

        p {
            font-size: 14px;
            font-weight: 100;
            line-height: 20px;
            letter-spacing: 0.5px;
            margin: 20px 0 30px;
        }

        span {
            font-size: 12px;
        }

        a {
            color: #333;
            font-size: 14px;
            text-decoration: none;
            margin: 15px 0;
        }

        button {
            border-radius: 20px;
            border: 1px solid #039a21;
            background-color: #039a21;
            color: #FFFFFF;
            font-size: 12px;
            font-weight: bold;
            padding: 12px 45px;
            letter-spacing: 1px;
            text-transform: uppercase;
            transition: transform 80ms ease-in;
        }

        button:active {
            transform: scale(0.95);
        }

        button:focus {
            outline: none;
        }

        button.ghost {
            background-color: #039a21;
            border-color: #FFFFFF;
        }

        form {
            background-color: #FFFFFF;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            padding: 0 50px;
            height: 100%;
            text-align: center;
        }

        input {
            background-color: #eee;
            border: none;
            padding: 12px 15px;
            margin: 8px 0;
            width: 100%;
        }

        .container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25),
                0 10px 10px rgba(0, 0, 0, 0.22);
            position: relative;
            overflow: hidden;
            width: 768px;
            max-width: 100%;
            min-height: 480px;
        }

        .form-container {
            position: absolute;
            top: 0;
            height: 100%;
            transition: all 0.6s ease-in-out;
        }

        .sign-in-container {
            left: 0;
            width: 50%;
            z-index: 2;
        }

        .container.right-panel-active .sign-in-container {
            transform: translateX(100%);
        }

        .sign-up-container {
            left: 0;
            width: 50%;
            opacity: 0;
            z-index: 1;
        }

        .container.right-panel-active .sign-up-container {
            transform: translateX(100%);
            opacity: 1;
            z-index: 5;
            animation: show 0.6s;
        }

        @keyframes show {

            0%,
            49.99% {
                opacity: 0;
                z-index: 1;
            }

            50%,
            100% {
                opacity: 1;
                z-index: 5;
            }
        }

        .overlay-container {
            position: absolute;
            top: 0;
            left: 50%;
            width: 50%;
            height: 100%;
            overflow: hidden;
            transition: transform 0.6s ease-in-out;
            z-index: 100;
        }

        .container.right-panel-active .overlay-container {
            transform: translateX(-100%);
        }

        .overlay {
            background: #027513;
            background: -webkit-linear-gradient(to right, #027513, #2eef1d);
            background: linear-gradient(to right, #026010, #3bbe27);
            background-repeat: no-repeat;
            background-size: cover;
            background-position: 0 0;
            color: #FFFFFF;
            position: relative;
            left: -100%;
            height: 100%;
            width: 200%;
            transform: translateX(0);
            transition: transform 0.6s ease-in-out;
        }

        .container.right-panel-active .overlay {
            transform: translateX(50%);
        }

        .overlay-panel {
            position: absolute;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            padding: 0 40px;
            text-align: center;
            top: 0;
            height: 100%;
            width: 50%;
            transform: translateX(0);
            transition: transform 0.6s ease-in-out;
        }

        .overlay-left {
            transform: translateX(-20%);
        }

        .container.right-panel-active .overlay-left {
            transform: translateX(0);
        }

        .overlay-right {
            right: 0;
            transform: translateX(0);
        }

        .container.right-panel-active .overlay-right {
            transform: translateX(20%);
        }

        .social-container {
            margin: 20px 0;
        }

        .social-container a {

            border: 1px solid #DDDDDD;
            border-radius: 50%;
            display: inline-flex;
            justify-content: center;
            align-items: center;
            margin: 0 5px;
            height: 40px;
            width: 40px;
        }

        footer {
            background-color: #222;
            color: #fff;
            font-size: 14px;
            bottom: 0;
            position: fixed;
            left: 0;
            right: 0;
            text-align: center;
            z-index: 999;
        }

        footer p {
            margin: 10px 0;
        }

        footer i {
            color: red;
        }

        footer a {
            color: #3c97bf;
            text-decoration: none;
        }
    </style>
</head>
<div class="container" id="container">
    <h2>Đăng Nhập/Đăng Ký</h2>
    <div class="container" id="container">
        <div class="form-container sign-up-container">

            <form action="auth" style="" method="post">
                <input type="hidden" name="action" value="register"/>
                <div style="display: flex" class="form-control">
                    <input class="form-control" type="text" placeholder="FirstName" name="firstName" required/>
                    <div id="name-message" style="color:red;font-size:11px"></div>
                    <input style="margin-left: 5px" class="form-control" type="text" placeholder="LastName" name="lastName" required/>
                    <div id="name-message" style="color:red;font-size:11px"></div>

                </div>
                <input type="text" placeholder="Username" name="username" required/>
                <input type="email" placeholder="Email" name="email" required/>

                <input type="text" placeholder="Phone" name="phone" required/>
                <div id="phone-message" style="color:red;font-size:11px"></div>
                <input type="password" placeholder="Password" name="password" id="password" required/>
                <div id="password-message" style="color:red;font-size:11px"></div>
                <input type="password" placeholder="Confirm password" name="repass" id="confirmpassword" required/>

                <!--                <select name="gender" style="width: 100%; padding: 12px 15px; margin: 8px 0; background-color: #eee; border: none;">
                                    <option value="" selected="true" disabled="true">Giới tính</option>
                                    <option value="1">Nam</option>
                                    <option value="0">Nữ</option>
                                </select>-->
                <button type="submit" disabled="true">Đăng ký</button>
            </form>
        </div>
        <div class="form-container sign-in-container">
            <form action="${pageContext.request.contextPath}/auth" method="post">

                <c:if test="${not empty successMessage}">
                    <h5 style="color:green">${successMessage}</h5>
                </c:if>
                <h1>Đăng nhập</h1>

                <div class="social-container">
                    <a href="https://www.facebook.com/dialog/oauth?client_id=1509092289871952&redirect_uri=http://localhost:8080/FBK74/login-facebook" class="social"
                       style="
                       background-color: navy;
                       color: white;
                       "><i class="fab fa-facebook-f"></i></a>
                    <a href="#" class="social"  style="
                       background-color: red;
                       color: white;
                       "><i class="fab fa-google-plus-g"></i></a>
                </div>
                <input type="hidden" placeholder="UserName" name="action" value="login"/>
                <span>Sử dụng tài khoản mạng xã hội</span>
                <input type="UserName" placeholder="UserName" name="userName" required/>

                <input type="password" placeholder="Password" name="password" required/>
                <c:if test="${not empty ERRORMESSAGE}">
                    <h5 style="color:red">${ERRORMESSAGE}</h5>
                </c:if>
                <c:if test="${not empty SUCCESSMESSAGE}">
                    <h5 style="green">${SUCCESSMESSAGE}</h5>
                </c:if>
                <c:if test="${not empty EMAIL_URL}">
                   <a href="${EMAIL_URL}">Go to your mail</a>
                </c:if>
                <a href="verify">Quên Mật Khẩu?</a>
                <button type="submit">Đăng nhập</button>
            </form>
        </div>
        <h5 style="color:red">${errorMessage}</h5>

        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <h1>Grammar Guardian- Giúp bạn cải thiện kỉ năng viết</h1>
                    <p>Nhập thông tin cá nhân của bạn và bắt đầu hành trình với chúng tôi</p>
                    <button class="ghost" id="signIn">Đăng Nhập</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <h1>Grammar Guardian</h1>
                    <p>Nhập thông tin cá nhân của bạn và bắt đầu hành trình với chúng tôi</p>
                    <button class="ghost" id="signUp">Đăng Ký</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script>
    const signUpButton = document.getElementById('signUp');
    const signInButton = document.getElementById('signIn');
    const container = document.getElementById('container');

    signUpButton.addEventListener('click', () => {
        container.classList.add('right-panel-active');
    });

    signInButton.addEventListener('click', () => {
        container.classList.remove('right-panel-active');
    });
    const passwordInput = document.getElementById('password');
    const passwordConfirm = document.getElementById('confirmpassword');
    const passwordMessage = document.getElementById('password-message');
    const submitButton = document.querySelector('form button');

    const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!"#$%&'*+,-\./:;<=>?@\[\]^_`{|}~])[^\s]{8,}$/;

    passwordInput.addEventListener('keyup', (event) => {
        const password = event.target.value;
        let isValid = true;
        passwordMessage.textContent = '';

        if (!passwordRegex.test(password)) {
            isValid = false;
            passwordMessage.textContent = 'Mật khẩu phải chứa ít nhất 8 kí tự và ít nhất một kí tự hoa,1 kí tự thường, 1 số, và 1 kí tự đặc biệt.';
        }

        submitButton.disabled = !isValid;
    });
    
    
     passwordConfirm.addEventListener('keyup', (event) => {
        const password = event.target.value;
        let isValid = true;
        passwordMessage.textContent = '';

        if (!(password == passwordInput.value)) {
            isValid = false;
            passwordMessage.textContent = 'Mật khẩu không khớp với mật khẩu bạn đăng kí';
        }

        submitButton.disabled = !isValid;
    });
    

//
    document.addEventListener('DOMContentLoaded', function () {
        const nameInput = document.querySelector('input[name="name"]');
        const phoneInput = document.querySelector('input[name="phone"]');
        const nameMessage = document.getElementById('name-message');
        const phoneMessage = document.getElementById('phone-message');
        const submitButton = document.querySelector('form button');

        const nameRegex = /^[A-Za-z\s]+$/;
        const phoneRegex = /^\d{1,10}$/;

        // Validate Name
        function validateName() {
            const nameIsValid = nameRegex.test(nameInput.value);
            nameMessage.textContent = nameIsValid ? '' : 'Tên chỉ được chứa chữ cái và khoảng trắng, không chứa số hoặc ký tự đặc biệt.';
            checkFormValidity();
        }

        // Validate Phone
        function validatePhone() {
            const phoneIsValid = phoneRegex.test(phoneInput.value);
            phoneMessage.textContent = phoneIsValid ? '' : 'Số điện thoại chỉ chứa số và không quá 10 chữ số.';
            checkFormValidity();
        }

        // Check overall form validity
        function checkFormValidity() {
            const nameIsValid = nameRegex.test(nameInput.value);
            const phoneIsValid = phoneRegex.test(phoneInput.value);
            submitButton.disabled = !(nameIsValid && phoneIsValid);
        }

        nameInput.addEventListener('keyup', validateName);
        phoneInput.addEventListener('keyup', validatePhone);
    });

//moi

    document.addEventListener('DOMContentLoaded', function () {
        const form = document.querySelector('form[action="register"]');
        const inputs = form.querySelectorAll('input');
        const select = form.querySelector('select[name="gender"]');
        const submitButton = form.querySelector('button[type="submit"]');
        const formMessage = document.createElement('div');
        formMessage.style.color = 'red';
        formMessage.style.fontSize = '14px';
        formMessage.style.textAlign = 'center';
        formMessage.style.marginTop = '10px';
        formMessage.textContent = 'Vui lòng điền đủ thông tin để đăng ký.';
        formMessage.hidden = true; // Ẩn thông báo này mặc định
        form.appendChild(formMessage);

        function validateInputs() {
            let isValid = true;
            inputs.forEach(input => {
                if (input.value.trim() === '') {
                    isValid = false;
                }
            });

            if (select.value === '') {
                isValid = false;
            }

            // Kiểm tra định dạng của các trường như email và password nếu cần
            // Ví dụ, kiểm tra định dạng email
            const emailInput = form.querySelector('input[name="email"]');
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(emailInput.value)) {
                isValid = false;
            }

            // Kiểm tra mật khẩu phức tạp
            const passwordInput = form.querySelector('input[name="password"]');
            const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!"#$%&'*+,-\./:;<=>?@\[\]^_`{|}~])[^\s]{8,}$/;
            if (!passwordRegex.test(passwordInput.value)) {
                isValid = false;
            }

            submitButton.disabled = !isValid;
            formMessage.hidden = isValid; // Hiển thị thông báo nếu form không hợp lệ
        }

        inputs.forEach(input => input.addEventListener('input', validateInputs));
        select.addEventListener('change', validateInputs);
    });

</script>