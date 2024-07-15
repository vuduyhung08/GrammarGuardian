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

            <body class="flex items-center justify-center h-screen"
                style="background: linear-gradient(to right, #2F80ED, #56CCF2);">
                <form action="verify" method="post">
                    <input type="hidden" name="action" value="setNewPassword" />
                    <div class="bg-white p-6 rounded-lg shadow-md w-96" style="width: 500px">
                        <div class="flex justify-center mb-4">
                            <!-- Replace with your actual logo if available -->
                            <span class="text-orange-500 font-bold text-2xl" style="color: green;">Grammar
                                Guardian</span>
                        </div>
                        <h2 class="text-center text-lg font-bold text-gray-700 mb-2">Verification sucessfully</h2>
                        <p class="text-center text-sm text-gray-600 mb-4">Please type new password!</p>
                        <div id="password-message" style="color:red;font-size:11px"></div>
                        <input name="email" id="email" hidden value="${email}">
                        <div class="mb-4 relative">
                            <input name="newPassword" type="password" id="password" placeholder="New password"
                                class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                            <i class="fas fa-eye-slash absolute right-3 top-3 text-gray-400 cursor-pointer"
                                onclick="togglePassword()" id="togglePasswordIcon"></i>
                            <div id="password-confirm-message" style="color:red;font-size:11px"></div>
                        </div>
                        <div class="mb-4 relative">
                            <input type="password" id="confirmpassword" placeholder="Confirm password"
                                class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                            <i class="fas fa-eye-slash absolute right-3 top-3 text-gray-400 cursor-pointer"
                                onclick="togglePassword()" id="togglePasswordIcon"></i>
                        </div>
                        <div class="mb-4">
                            <button disabled="true" type="submit"
                                class="bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 w-full rounded focus:outline-none focus:shadow-outline"
                                style="background: linear-gradient(to right, #2F80ED, #56CCF2)">
                                CONFIRM
                            </button>
                        </div>
                        <div class="text-center text-sm">
                            <a href="#"
                                class="text-black-500 hover:text-blue-600 transition duration-300 ease-in-out">Remember
                                password?</a>
                            <a href="#"
                                class="text-green-500 hover:text-green-600 transition duration-300 ease-in-out ml-2">Login
                                now?</a>
                        </div>
                    </div>
                </form>

            </body>

            </html>

            <script>
                const passwordInput = document.getElementById('password');
                const passwordMessage = document.getElementById('password-message');
                const passwordMessageConfirm = document.getElementById('password-confirm-message');
                const submitButton = document.querySelector('form button');
                const passwordConfirm = document.getElementById('confirmpassword');

                const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!"#$%&'*+,-\./:;<=>?@\[\]^_`{|}~])[^\s]{8,}$/;

                passwordInput.addEventListener('keyup', (event) => {
                    const password = event.target.value;
                    let isValid = true;
                    passwordMessage.textContent = '';

                    if (!passwordRegex.test(password)) {
                        isValid = false;
                        passwordMessage.textContent = 'Password must be at least 8 characters, include at least 1 uppercase letter, 1 lowercase letter, and 1 special character.';
                    }

                    submitButton.disabled = !isValid;
                });

                passwordConfirm.addEventListener('keyup', (event) => {
                    const password = event.target.value;
                    let isValid = true;
                    passwordMessageConfirm.textContent = '';

                    if (!(password == passwordInput.value)) {
                        isValid = false;
                        passwordMessageConfirm.textContent = 'Password not matching';
                    }

                    submitButton.disabled = !isValid;
                });
            </script>