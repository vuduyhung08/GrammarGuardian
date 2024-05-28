<%-- 
    Document   : message
    Created on : Mar 12, 2024, 6:53:18 PM
    Author     : nhatk
--%>

<%@ page import="java.net.URLEncoder" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verification Code Input</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-96">
        <div class="flex justify-center mb-4">

            <span class="text-orange-500 font-bold text-2xl" style="color: green;">GrammarChecker</span>
        </div>
        <c:if test="${not empty error}">
            <h2 class="text-center text-lg font-bold mb-2" style="color:red">${error}</h2>
            <p class="text-center text-sm text-gray-600 mb-4">Vui lòng kiểm tra lại hộp thư điện tử ${USER.email}!</p>
        </c:if>

        <c:if test="${empty error}">
            <h2 class="text-center text-lg font-bold mb-2">Đã gửi mã xác minh đến ${USER.email}!</h2>
            <p class="text-center text-sm text-gray-600 mb-4">Vui lòng kiểm tra hộp thư điện tử</p>
        </c:if>


        <form action="verify" method="post">
            <input type="hidden" name="action" value="check"/>
            <div class="flex justify-between gap-2 mb-4">
                <!-- Verification code input boxes -->
                <!--                <input name="code1" class="w-full py-2 text-center form-input border border-gray-300 rounded focus:outline-none focus:border-orange-500" type="text" maxlength="1">
                                <input name="code2" class="w-full py-2 text-center form-input border border-gray-300 rounded focus:outline-none focus:border-orange-500" type="text" maxlength="1">
                                <input name="code3" name="code2"class="w-full py-2 text-center form-input border border-gray-300 rounded focus:outline-none focus:border-orange-500" type="text" maxlength="1">
                                <input name="code4" class="w-full py-2 text-center form-input border border-gray-300 rounded focus:outline-none focus:border-orange-500" type="text" maxlength="1">
                                <input name="code5" class="w-full py-2 text-center form-input border border-gray-300 rounded focus:outline-none focus:border-orange-500" type="text" maxlength="1">
                                <input name="code6" class="w-full py-2 text-center form-input border border-gray-300 rounded focus:outline-none focus:border-orange-500" type="text" maxlength="1">
                                <input value="${email}" name="email" hidden="true" style="display:none"></div>-->

                <input name="otp" class="w-full py-2 text-center form-input border border-gray-300 rounded focus:outline-none focus:border-orange-500" type="text">
                <button type="submit" class="bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 w-full rounded focus:outline-none focus:shadow-outline" style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a">
                    XÁC NHẬN
                </button>
            </div>
            <div style=""class="text-center text-sm">
                <a  class="text-Black-500 hover:text-blue-600 transition duration-300 ease-in-out">Nhớ mật khẩu?</a>
                <a href="${pageContext.request.contextPath}/auth?action=login" class="text-green-500 hover:text-green-600 transition duration-300 ease-in-out">Đăng nhập ngay</a>
            </div>
        </form>


    </div>
</body>
<script>
    $(document).ready(function () {
        var inputs = $('input[type="text"]');
        var submitButton = $('button[type="submit"]');

        // Bắt sự kiện nhập vào mỗi ô input
        inputs.on('input', function () {
            // Kiểm tra xem đã nhập đủ 6 ký tự hay chưa
            if (inputs.filter(function () {
                return $(this).val().length === 1;
            }).length === 6) {
                // Gửi request lên ConfirmCodeServlet
                submitButton.click();
            }
        });
    });

</script>