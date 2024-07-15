<%@ page import="java.net.URLEncoder" %>
    <%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Password Change Form</title>
            <script src="https://cdn.tailwindcss.com"></script>
            <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
            <style>
                body {
                    background: linear-gradient(to right, #2F80ED, #56CCF2);
                    font-family: 'Inter', sans-serif;
                }

                .form-container {
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    border-radius: 8px;
                    overflow: hidden;
                }
            </style>
            </head>

            <body>
                <div class="max-w-lg mx-auto mt-10">
                    <div class="form-container">
                        <form action="verify" method="post" style="background: white">
                            <input type="hidden" name="action" value="sendOtpToMail" />
                            <div class="bg-green-600 text-white text-center py-4"
                                style="background: linear-gradient(to right, #2F80ED, #56CCF2);">
                                <h1 class="text-xl font-semibold">FORGOT PASSWORD</h1>
                            </div>
                            <div class="p-6">
                                <label class="block text-gray-700 text-sm font-bold mb-2" for="email">
                                    Email Address:
                                </label>
                                <input name="email"
                                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
                                    id="email" type="text" placeholder="Email">
                                <span>We will send OTP code to your email input!</span>
                            </div>
                            <div class="px-6 py-4">
                                <button type="submit" style="background: linear-gradient(to right, #2F80ED, #56CCF2);"
                                    class="bg-green-600 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full"
                                    type="button">
                                    Send
                                </button>
                            </div>
                        </form>

                    </div>
                </div>
            </body>

            </html>