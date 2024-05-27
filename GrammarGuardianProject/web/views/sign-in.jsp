</head>
<div class="container" id="container">
    <h2>??ng Nh?p/??ng Ký</h2>
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
                                    <option value="" selected="true" disabled="true">Gi?i tính</option>
                                    <option value="1">Nam</option>
                                    <option value="0">N?</option>
                                </select>-->
                <button type="submit" disabled="true">??ng ký</button>
            </form>
        </div>
        <div class="form-container sign-in-container">
            <form action="${pageContext.request.contextPath}/auth" method="post">

                <c:if test="${not empty successMessage}">
                    <h5 style="color:green">${successMessage}</h5>
                </c:if>
                <h1>??ng nh?p</h1>

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
                <span>S? d?ng tài kho?n m?ng xã h?i</span>
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
                <a href="verify">Quên M?t Kh?u?</a>
                <button type="submit">??ng nh?p</button>
            </form>
        </div>
        <h5 style="color:red">${errorMessage}</h5>

        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <h1>GrammarChecker- Giúp b?n c?i thi?n k? n?ng vi?t</h1>
                    <p>Nh?p thông tin cá nhân c?a b?n và b?t ??u hành trình v?i chúng tôi</p>
                    <button class="ghost" id="signIn">??ng Nh?p</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <h1>GrammarChecker</h1>
                    <p>Nh?p thông tin cá nhân c?a b?n và b?t ??u hành trình v?i chúng tôi</p>
                    <button class="ghost" id="signUp">??ng Ký</button>
                </div>
            </div>
        </div>
    </div>
</div>