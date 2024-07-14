<%@ page import="java.net.URLEncoder" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Grammar Guardian</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/post-detail.css">   
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <form action="UpdatePostController" method="POST" enctype="multipart/form-data">
            <div class="registersform">
                <div class="flexsForm">
                    <input type="hidden" name="postId" value="${POST.postId}"/>
                    <h1>Post Detail</h1>
                    <label style="color: green">${MESSAGE}</label>     
                    <label style="color: red">${ERROR}</label>

                    <div class="dividerSocial"></div>

                    <div class="textField">
                        <label>Title</label>
                        <input type="text" name="title" value="${POST.title}" />
                        <span class="error"></span>
                    </div>

                    <div class="textField">
                        <label>Description</label>

                        <textarea id="description" name="description" readonly style="height: 300px">
                            ${POST.description}
                        </textarea>
                        <div class="over-content">
                            <table class="styled-table">
                                <thead>
                                    <tr>
                                        <th width="50%">Error Message</th>
                                        <th width="20%">Error Word</th>
                                        <th width="10%">Position</th>
                                        <th width="20%">Suggested Correction</th>
                                    </tr>
                                </thead>
                                <tbody class="over-content">
                                    <c:forEach var="match" items="${POST_ERROR}">
                                        <tr>
                                            <td>${match.explain}</td>
                                            <td style="color: red; text-align: center">${match.errorText}</td>
                                            <td style="text-align: center">${match.start_Position}-${match.end_Position}</td>
                                            <td style="color: green; text-align: center">
                                                <c:out value="${match.suggestion}" />&nbsp;
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <span class="error"></span>
                    </div>
                    <button type="submit">Update</button>
                    <a href="UpdateAllSugestionController?postId=${POST.postId}" class="btn btn-success">Replace all error</a>
                </div>
                <span class="divider"></span>
                <div class="flexsForm">
                    <h1>Post Updated</h1>
                    <label style="color: green">${MESSAGE}</label>     
                    <label style="color: red">${ERROR}</label>

                    <div class="dividerSocial"></div>

                    <div class="textField">
                        <label>Title</label>
                        <input type="text" name="title" value="${POST_UPDATE.title}" />
                        <span class="error"></span>
                    </div>
                    <div class="textField">
                        <label>Post Updated</label>
                        <textarea id="description" name="description" readonly style="height: 600px">
                            ${POST_UPDATE.description}
                        </textarea>
                    </div>
<!--                    <a href="UpdateAllSugestionController?postId=${POST_UPDATE.id}" class="btn btn-success">Replace all error</a>-->


                    </form>
                </div>
            </div>
            <jsp:include page="footer.jsp" />
            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="${pageContext.request.contextPath}/lib/wow/wow.min.js"></script>
            <script src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
            <script src="${pageContext.request.contextPath}/lib/waypoints/waypoints.min.js"></script>
            <script src="${pageContext.request.contextPath}/lib/counterup/counterup.min.js"></script>
            <script src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>

            <!-- Template Javascript -->
            <script src="${pageContext.request.contextPath}/js/main.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </body>

    <script>
        const profilePicture = document.getElementById('profile-picture');
        const imageInput = document.getElementById('image-input');

        profilePicture.addEventListener('click', () => {
            imageInput.disabled = false;
            imageInput.click();
        });
        const updateAvatar = false;
        imageInput.addEventListener('change', () => {
            imageInput.disabled = false;
            const file = imageInput.files[0];
            const formData = new FormData();
            formData.append('image', file);

            const reader = new FileReader();
            reader.onload = () => {
                profilePicture.src = reader.result;
            };
            reader.readAsDataURL(file);
            updateAvatar = true;
        });
        if (!updateAvatar && imageInput.value != null) {
            imageInput.disabled = true;
        }
        document.addEventListener("DOMContentLoaded", function () {
            const phoneInput = document.getElementById("Phone");

            phoneInput.addEventListener("input", function () {
                const regex = /^\d{0,10}$/;
                if (!regex.test(phoneInput.value)) {
                    // If validation fails, show a custom error message
                    phoneInput.setCustomValidity("Số điện thoại phải gồm 10 chữ số và không chứa ký tự đặc biệt.");
                } else {
                    // Clear custom error message
                    phoneInput.setCustomValidity("");
                }
            });
        });
    </script>
</html>