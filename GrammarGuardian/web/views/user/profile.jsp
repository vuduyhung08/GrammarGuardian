<%@ page import="java.net.URLEncoder" %>
    <%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>

        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <html>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Thông tin tài khoản</title>
                <script src="https://cdn.tailwindcss.com"></script>
                <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap"
                    rel="stylesheet">
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

                <!-- Favicon -->
                <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="icon">
                <!-- Google Web Fonts -->
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link
                    href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700;800&family=Rubik:wght@400;500;600;700&display=swap"
                    rel="stylesheet">

                <!-- Icon Font Stylesheet -->
                <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
                    rel="stylesheet">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">

                <!-- Libraries Stylesheet -->
                <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css"
                    rel="stylesheet">
                <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">

                <!-- Customized Bootstrap Stylesheet -->
                <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

                <!-- Template Stylesheet -->
                <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">


                <!-- Template Stylesheet -->
                <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
            </head>
            <style>
                .input-bordered {
                    border: 1px solid #cccccc;
                    /* Màu viền xám nhạt */
                    padding: 12px 15px;
                    /* Đệm để nhập liệu không bị sát viền */
                    margin: 8px 0;
                    /* Khoảng cách giữa các trường nhập */
                    width: 100%;
                    /* Chiều rộng tối đa */
                    background-color: #eee;
                    /* Màu nền nhạt cho trường nhập */
                    border-radius: 4px;
                    /* Bán kính bo góc cho viền */
                }

                body {
                    font-family: 'Inter', sans-serif;
                }

                .radio-button:checked+.radio-label {
                    background-color: #f97316;
                    border-color: #f97316;
                }
            </style>

            <body>
                <jsp:include page="header.jsp" />
                <input type="hidden" id="success" name="MESSAGE" value="${MESSAGE}" />
                <div class="min-h-screen  flex items-center justify-center px-4"
                    style="background: linear-gradient(to right, #2F80ED, #56CCF2);">
                    <div class="bg-white shadow-lg rounded-lg p-8 max-w-4xl w-full">
                        <div class="flex flex-col md:flex-row justify-between items-start">
                            <div class="flex flex-col items-center text-center md:text-left md:items-start">
                                <h2 class="text-2xl text-gray-800 font-semibold mb-4">
                                    <i class="fas fa-user-circle mr-2"></i>User profile
                                </h2>

                                <c:choose>
                                    <c:when test="${USER.image != null}">
                                        <img src="data:image/png;base64,${USER.image}" alt="Profile picture"
                                            id="profile-picture" class=" border-3 border-green-500 p-1 mb-3"
                                            style="width: 170px; cursor: pointer; margin: 10px auto;border-radius:50%;border: 2px solid #1b730d">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="https://placehold.co/100x100" alt="Profile picture"
                                            id="profile-picture" class=" border-3 border-green-500 p-1 mb-3"
                                            style="width: 170px; cursor: pointer; margin: 10px auto;border-radius:50%;border: 2px solid #1b730d">
                                    </c:otherwise>
                                </c:choose>


                                <p class="text-gray-700 mb-1" style="margin: 0 auto">${sessionScope.account.userName}
                                </p>
                                <a href="profile?action=changePassword">
                                    <button
                                        class="bg-green-500 text-white text-lg px-6 py-2 rounded-full shadow-md hover:shadow-lg transition-shadow duration-300 ease-in-out focus:outline-none focus:ring-2 focus:ring-green-700 focus:ring-opacity-50"
                                        style="      background: linear-gradient(to right, #2F80ED, #56CCF2); margin:10px auto">
                                        Change password
                                    </button>
                                </a>
                                <a href="TransitionHistoryController">
                                    <button
                                        class="bg-green-500 text-white text-lg px-6 py-2 rounded-full shadow-md hover:shadow-lg transition-shadow duration-300 ease-in-out focus:outline-none focus:ring-2 focus:ring-green-700 focus:ring-opacity-50"
                                        style="background: linear-gradient(to right, #2F80ED, #56CCF2); margin-bottom: 10px">
                                        Transition history
                                    </button>
                                </a>
                                <a href="GetUserPermission">
                                    <button
                                        class="bg-green-500 text-white text-lg px-6 py-2 rounded-full shadow-md hover:shadow-lg transition-shadow duration-300 ease-in-out focus:outline-none focus:ring-2 focus:ring-green-700 focus:ring-opacity-50"
                                        style="background: linear-gradient(to right, #2F80ED, #56CCF2); margin-bottom: 10px">
                                        User package
                                    </button>
                                </a>
                                </a>
                                <a href="HomeController">
                                    <button
                                        class="bg-green-500 text-white text-lg px-6 py-2 rounded-full shadow-md hover:shadow-lg transition-shadow duration-300 ease-in-out focus:outline-none focus:ring-2 focus:ring-green-700 focus:ring-opacity-50"
                                        style="  background: linear-gradient(to right, #2F80ED, #56CCF2);">
                                        Back to homepage
                                    </button>
                                </a>

                                <div style="color: green; margin-top: 10px">${MESSAGE}</div>


                            </div>
                            <div class="mt-8 md:mt-0 md:ml-10 w-full max-w-lg">
                                <form class="space-y-4" action="profile" method="POST" enctype="multipart/form-data">
                                    <input type="hidden" name="action" value="updateProfile" />
                                    <input type="file" id="image-input" name="image" style="display: none;"
                                        value="${USER.image}">
                                    <input type="hidden" name="accountId" value="${user.IDAccount}" />

                                    <div>
                                        <label for="surname" class="text-gray-700">FirstName</label>
                                        <input name="firstName" value="${USER.firstName}" type="text" id="surname"
                                            placeholder="FirstName"
                                            class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent">
                                    </div>
                                    <div>
                                        <label for="surname" class="text-gray-700">LastName</label>
                                        <input name="lastName" value="${USER.lastName}" type="text" id="surname"
                                            placeholder="LastName"
                                            class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent">
                                    </div>
                                    <div>
                                        <label for="Phone" class="text-gray-700">Tel</label>
                                        <input name="phone" value="${USER.phone}" type="text" id="Phone"
                                            placeholder="Tel"
                                            class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"
                                            pattern="^\d{1,10}$" title="Phone number required number only" required>
                                    </div>
                                    <div>
                                        <label for="Email" class="text-gray-700">Email</label>
                                        <input readonly name="email" value="${USER.email}" type="email" id="Email"
                                            placeholder="Email"
                                            class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"
                                            pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
                                            title="Your email not validate" required>
                                    </div>
                                    <button type="submit"
                                        class="w-full bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors"
                                        style="background: linear-gradient(to right, #2F80ED, #56CCF2);">Save</button>
                                </form>
                            </div>


                        </div>
                        <div class="album  bg-light">
                            <h2 class="text-body-emphasis text-center py-3">
                                Your document history
                            </h2>
                            <div class="container">
                                <form action="FilterPostController" class="d-flex" style="margin-bottom: 15px;">
                                    <input class="form-control me-2" type="search" placeholder="Search"
                                        aria-label="Search" name="search" value="${search}">
                                    <button class="btn btn-outline-success" type="submit">Search</button>
                                    <select name="status" class="form-select" aria-label="Default select example">
                                        <option value="" selected>All</option>
                                        <option ${status==0 ? 'selected' : '' } value="0">Saved</option>
                                        <option ${status==1 ? 'selected' : '' } value="1">Pending</option>
                                        <option ${status==3 ? 'selected' : '' } value="3">Confirmed</option>
                                        <option ${status==2 ? 'selected' : '' } value="2">Rejected</option>
                                        <option ${status==4 ? 'selected' : '' } value="4">Deleted</option>
                                        <option ${status==5 ? 'selected' : '' } value="5">Favourite</option>
                                    </select>
                                </form>
                                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                                    <c:if test="${LIST_POST ==  null}">
                                        <h3>You did not have any saved post!!</h3>
                                    </c:if>
                                    <c:forEach var="post" items="${LIST_POST}">
                                        <div class="col-md-3">
                                            <div class="card shadow-sm">
                                                <div class="card-body"
                                                    style="display: flex; justify-content: space-between">
                                                    <div class="d-flex justify-content-center align-items-center"
                                                        style="flex-direction: column">
                                                        <div>
                                                            <b>Title</b>: ${post.title}
                                                        </div>
                                                        <div>
                                                            ${post.createAt}
                                                        </div>
                                                        <div class="btn-group">
                                                            <a data-bs-toggle="modal"
                                                                data-bs-target="#post-detail-${post.postId}">
                                                                <button type="button"
                                                                    class="btn btn-sm btn-outline-secondary">
                                                                    Details
                                                                </button>
                                                            </a>
                                                        </div>
                                                        <!-- Modal -->
                                                        <div class="modal fade" id="post-detail-${post.postId}"
                                                            tabindex="-1" aria-labelledby="post-detail-${post.postId}"
                                                            aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content" style="width: 150%">
                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="exampleModalLabel">
                                                                            Post details</h5>
                                                                        <button type="button" class="btn-close"
                                                                            data-bs-dismiss="modal"
                                                                            aria-label="Close"></button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <div>
                                                                            <b>Title: </b> ${post.title}
                                                                        </div>
                                                                        <div>
                                                                            <b>Description: </b>${post.description}
                                                                        </div>
                                                                    </div>
                                                                    <div class="modal-footer"
                                                                        style="display: flex; justify-content: space-between">
                                                                        <div>
                                                                            <a class="btn btn-warning"
                                                                                href="UpdatePostController?postId=${post.postId}">Update
                                                                                Post</a>
                                                                        </div>
                                                                        <div>
                                                                            <c:if test="${status != 5}">
                                                                                <c:if test="${post.status == 0}">
                                                                                    <a class="btn btn-primary"
                                                                                        href="SendPostToConfirm?postId=${post.postId}">Submit
                                                                                        Post</a>
                                                                                </c:if>
                                                                                <c:if test="${post.status == 1}">
                                                                                    <a class="btn btn-danger"
                                                                                        href="CanclePostToConfirm?postId=${post.postId}">Cancle
                                                                                        pending</a>
                                                                                </c:if>
                                                                                <c:if test="${post.status == 2}">
                                                                                    <a class="btn btn-danger"
                                                                                        href="SendPostToConfirm?postId=${post.postId}">Resend
                                                                                        Post</a>
                                                                                </c:if>
                                                                                <c:if test="${post.status == 3}">
                                                                                    <a class="btn btn-danger"
                                                                                        href="DeletePost?postId=${post.postId}">Delete
                                                                                        Post</a>
                                                                                </c:if>
                                                                                <c:if test="${post.status == 4}">
                                                                                    <a class="btn btn-danger"
                                                                                        href="RestorePost?postId=${post.postId}">Restore
                                                                                        Post</a>
                                                                                </c:if>
                                                                            </c:if>
                                                                            <c:if test="${status == 5}">
                                                                                <a class="btn btn-danger"
                                                                                    href="RemovePostFromFavourite?postId=${post.postId}">Remove
                                                                                    from favourite</a>
                                                                            </c:if>
                                                                            <button type="button"
                                                                                class="btn btn-secondary"
                                                                                data-bs-dismiss="modal">Close</button>
                                                                        </div>

                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <c:if test="${post.status == 0}">
                                                            <span style="color: blue">Saved</span>
                                                        </c:if>
                                                        <c:if test="${post.status == 1}">
                                                            <span style="color: #cccccc">Pending</span>
                                                        </c:if>
                                                        <c:if test="${post.status == 2}">
                                                            <span style="color: red">Rejected</span>
                                                        </c:if>
                                                        <c:if test="${post.status == 3}">
                                                            <span style="color: green">Confirmed</span>
                                                        </c:if>
                                                        <c:if test="${post.status == 4}">
                                                            <span style="color: #f97316">Deleted</span>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                                <nav aria-label="Page navigation example"
                                    style="display: flex; justify-content:center;margin-top: 15px;">
                                    <ul class="pagination">

                                        <c:choose>
                                            <c:when test="${selectedPage - 1 < 1}">
                                                <li class="page-item disabled">
                                                    <a class="page-link" href="#">«</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item"><a class="page-link"
                                                        href="profile?action=view&type=${type}&search=${search}&index=${selectedPage-1}">«</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach var="i" begin="1" end="${endP}">
                                            <li class="page-item ${i == selectedPage ? " active" : "" }"> <a
                                                    class="page-link"
                                                    href="profile?action=view&type=${type}search=${search}&index=${i}">${i}</a>
                                            <li>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${selectedPage >= endP}">
                                                <li class="page-item disabled">
                                                    <a class="page-link" href="#">»</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item"><a class="page-link"
                                                        href="profile?action=view&type=${type}search=${search}&index=${selectedPage+1}">»</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </nav>
                            </div>

                        </div>
                    </div>
                </div>



                <jsp:include page="footer.jsp" />

            </body>


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
            <script>
                const profilePicture = document.getElementById('profile-picture');
                const imageInput = document.getElementById('image-input');
                var message = document.getElementById('success');
                if (message.value) {
                    Swal.fire({
                        title: message.value,
                        icon: "success",
                        showCancelButton: true,
                        confirmButtonText: "Confirm",
                    });
                }
                function deletePost() {
                    Swal.fire({
                        title: errorTeam.value,
                        icon: "info",
                        showCancelButton: true,
                        confirmButtonText: "Are your sure to remove this post",
                    }).then((result) => {
                        /* Read more about isConfirmed, isDenied below */
                        if (result.isConfirmed) {
                            window.location.href = 'profile?action=delete-post&postId=${post.postId}';
                        }
                    });
                }


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
                            phoneInput.setCustomValidity("Phone required 10 number");
                        } else {
                            // Clear custom error message
                            phoneInput.setCustomValidity("");
                        }
                    });
                });

            </script>

            </html>