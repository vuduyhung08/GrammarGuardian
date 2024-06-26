<%@ page import="java.net.URLEncoder" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Password Confirmation Form</title>
<script src="https://cdn.tailwindcss.com"></script>
<!-- Add your FontAwesome kit here -->
<script src="https://kit.fontawesome.com/your-fontawesome-kit.js" crossorigin="anonymous"></script>
<script>
    // Function to toggle password visibility
    function togglePassword() {
        var passwordInput = document.getElementById('password');
        var togglePasswordIcon = document.getElementById('togglePasswordIcon');
        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            togglePasswordIcon.classList.remove('fa-eye-slash');
            togglePasswordIcon.classList.add('fa-eye');
        } else {
            passwordInput.type = 'password';
            togglePasswordIcon.classList.remove('fa-eye');
            togglePasswordIcon.classList.add('fa-eye-slash');
        }
    }
</script>
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
    <form action="verify" method="post">
        <input type="hidden" name="action" value="setNewPassword"/>
        <div class="bg-white p-6 rounded-lg shadow-md w-96">
            <div class="flex justify-center mb-4">
                <!-- Replace with your actual logo if available -->
                <span class="text-orange-500 font-bold text-2xl" style="color: green;">Grammar Guardian</span>
            </div>

            <h2 class="text-center text-lg font-bold text-gray-700 mb-2">Xác minh thành công</h2>

            <p class="text-center text-sm text-gray-600 mb-4">Hãy nhập mật khẩu mới của bạn!</p>

            <div id="password-message" style="color:red;font-size:11px"></div>

            <input name="email" id="email" hidden value="${email}"> 

            <div class="mb-4 relative">
                <label for="new-password" class="block text-gray-700 text-sm font-bold mb-2">Mật khẩu mới</label>
                <input name="newwPassword" type="password" id="newwpassword" placeholder="Mật khẩu mới" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                <div id="password-message" style="color:red;font-size:11px"></div>
                <i class="fas fa-eye-slash password-toggle cursor-pointer absolute right-3 top-9" id="toggle-new-password" data-input="new-password"></i>
            </div>
            
            
            <div class="mb-6 relative">
                <label for="confirm-password" class="block text-gray-700 text-sm font-bold mb-2">Xác nhận mật khẩu mới</label>
                <input name="newPassword" type="password" id="newPassword" placeholder="Xác nhận mật khẩu mới" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                <i class="fas fa-eye-slash password-toggle cursor-pointer absolute right-3 top-9" id="toggle-confirm-password" data-input="confirm-password"></i>
                <input name="email" value="${sessionScope.account.IDEmail}" hidden>
            </div>
            
            <h5 style="color:red">${errorMessage}</h5>

            <div class="mb-4">
                <button type="submit" class="bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 w-full rounded focus:outline-none focus:shadow-outline" style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a">
                    XÁC NHẬN
                </button>
            </div>
            <div class="text-center text-sm">
                <a href="${pageContext.request.contextPath}/auth?action=login" class="btn btn-outline-light me-2">Login | Sign up</a>
            </div>
        </div>
    </form>

</body>
</html>

<script>
     const passwordInput = document.getElementById('newwpassword');
    const passwordConfirmInput = document.getElementById('newPassword');
    const passwordMessage = document.getElementById('password-message');
    const submitButton = document.getElementById('btn-submit');

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
    
     passwordConfirmInput.addEventListener('keyup', (event) => {
        const password = event.target.value;
        let isValid = true;
        passwordMessage.textContent = '';

        if (!(password == passwordInput.value)) {
            isValid = false;
            passwordMessage.textContent = 'Mật khẩu không khớp với mật khẩu bạn đăng kí';
        }
        submitButton.disabled = !isValid;
    });
</script>