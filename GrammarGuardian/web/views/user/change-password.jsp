<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Change Form</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const togglePasswordVisibility = (toggleIconId, inputId) => {
                const toggleIcon = document.getElementById(toggleIconId);
                const passwordInput = document.getElementById(inputId);
                const isPasswordVisible = passwordInput.type === 'text';
                passwordInput.type = isPasswordVisible ? 'password' : 'text';
                toggleIcon.classList.toggle('fa-eye', isPasswordVisible);
                toggleIcon.classList.toggle('fa-eye-slash', !isPasswordVisible);
            };

            document.querySelectorAll('.password-toggle').forEach(toggleIcon => {
                toggleIcon.addEventListener('click', () => {
                    const inputId = toggleIcon.getAttribute('data-input');
                    togglePasswordVisibility(toggleIcon.id, inputId);
                });
            });
        });
    </script>
    <style>
        body {
            background: linear-gradient(to right, #2F80ED, #56CCF2);
        }
    </style>
</head>

<body>
    <div class="flex justify-center mt-10">
        <div class="w-full max-w-4xl bg-white shadow-lg rounded-lg">
            <div class="flex flex-col md:flex-row">
                <div class="w-full md:w-1/2 p-5 border-b md:border-b-0 md:border-r">
                    <div class="flex items-center mb-5">
                        <i class="fas fa-key text-lg text-gray-500" style="color: #3ba023;"></i>
                        <h2 class="text-xl font-bold pl-3" style="color: #176d07;">Change password</h2>
                    </div>
                    <form action="profile" method="post">
                        <input type="hidden" name="action" value="changePassword"/>
                        <h3 class="text-xl font-bold pl-3" style="color: green;">${successMessage}</h3>
                        <div class="mb-4 relative">
                            <label for="old-password" class="block text-gray-700 text-sm font-bold mb-2">Current Password</label>
                            <input name="oldpassword" type="password" id="old-password" placeholder="Your current password" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                            <i class="fas fa-eye-slash password-toggle cursor-pointer absolute right-3 top-9" id="toggle-old-password" data-input="old-password"></i>
                        </div>
                        <div class="mb-4 relative">
                            <label for="new-password" class="block text-gray-700 text-sm font-bold mb-2">New password</label>
                            <input name="newPassword" type="password" id="new-password" placeholder="Your new password" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                            <div id="password-message" style="color:red;font-size:11px"></div>
                            <i class="fas fa-eye-slash password-toggle cursor-pointer absolute right-3 top-9" id="toggle-new-password" data-input="new-password"></i>
                        </div>
                        <div class="mb-6 relative">
                            <label for="confirm-password" class="block text-gray-700 text-sm font-bold mb-2">Confirm new password</label>
                            <input name="confirmPassword" type="password" id="confirm-password" placeholder="Confirm new password" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                            <div id="password-confirm-message" style="color:red;font-size:11px"></div>
                            <i class="fas fa-eye-slash password-toggle cursor-pointer absolute right-3 top-9" id="toggle-confirm-password" data-input="confirm-password"></i>
                            <input name="email" value="${sessionScope.account.IDEmail}" hidden>
                        </div>
                        <h3 id="update-message" style="color:red">${ERRORMESSAGE}</h3>
                        <div class="flex items-center justify-between">
                            <button id="btn-submit" type="submit" class="inline-block align-baseline font-bold text-sm text-gray-500 hover:text-gray-800 border border-gray-300 py-2 px-4 rounded hover:border-gray-500 focus:outline-none focus:border-gray-500">
                                Save
                            </button>
                            <a href="profile?action=view">
                                <button class="inline-block align-baseline font-bold text-sm text-gray-500 hover:text-gray-800 border border-gray-300 py-2 px-4 rounded hover:border-gray-500 focus:outline-none focus:border-gray-500" type="button">
                                    Cancel
                                </button>
                            </a>
                        </div>
                    </form>
                </div>
                <div class="w-full md:w-1/2 p-5">
                    <div class="flex items-center mb-5">
                        <i class="fas fa-shield-alt text-lg text-gray-500" style="color: rgb(229, 69, 10);"></i>
                        <h2 class="text-xl font-bold pl-3" style="color: #cf6203;">Set a strong password to keep your account secure.</h2>
                    </div>
                    <div class="bg-orange-100 border-l-4 border-orange-500 text-orange-700 p-4" role="alert">
                        <p class="font-bold">Make sure it has 8 or more characters, at least 1 uppercase letter, 1 lowercase letter and 1 special character</p>
                        <p>Change password to ensure safety.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        const passwordInput = document.getElementById('new-password');
        const oldPassword = document.getElementById('old-password');
        const passwordConfirm = document.getElementById('confirm-password');
        const passwordConfirmMessage = document.getElementById('password-confirm-message');
        const updateMessage = document.getElementById('update-message');
        const passwordMessage = document.getElementById('password-message');
        const submitButton = document.getElementById('btn-submit');


        const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!"#$%&'*+,-./:;<=>?@[\\\]^_`{|}~])[^\s]{8,}$/;



        passwordInput.addEventListener('keyup', (event) => {
            const password = event.target.value;
            let isValid = true;
            passwordMessage.textContent = '';

            if (!passwordRegex.test(password)) {
                isValid = false;
                passwordMessage.textContent = 'Password must contain at least 8 characters and at least one uppercase letter, one lowercase letter, one number, and one special character.';
            } else if (!(password == passwordConfirm.value)) {
                isValid = false;
                passwordMessage.textContent = 'Password does not match the password you registered with.';
            }

            if (oldPassword.value.trim() === "") {
                isValid = false;
            }
            
            submitButton.disabled = !isValid;

        });

        passwordConfirm.addEventListener('keyup', (event) => {
            const password = event.target.value;
            let isValid = true;
            passwordMessage.textContent = '';

            if (!passwordRegex.test(password)) {
                isValid = false;
                passwordMessage.textContent = 'Password must contain at least 8 characters and at least one uppercase letter, one lowercase letter, one number, and one special character.';
            } else if (!(password == passwordInput.value)) {
                isValid = false;
                passwordMessage.textContent = 'Password does not match the password you registered with.';
            }
            if (oldPassword.value.trim() === "") {
                isValid = false;
            }

            submitButton.disabled = !isValid;
        });

    </script>
</body>
