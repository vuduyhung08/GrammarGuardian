
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Home</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css" />
        <style>
            .category{
                justify-content: space-between;
            }
            .comment-input textarea {
                width: 100%;
                height: 60px;
                margin-bottom: 10px;
                resize: none;
            }

            .comment-input button {
                float: right;
                padding: 8px 15px;
                background-color: #4267B2;
                color: white;
                border: none;
                cursor: pointer;
            }

            .comment-input button:hover {
                background-color: #365899;
            }

            .comment-display {
                margin-top: 20px;
            }

            .comment {
                padding: 10px;
                border-bottom: 1px solid #eee;
            }

            .comment:last-child {
                border-bottom: none;
            }
            .text-origin{
                width: 620px;
                height: 400px;
            }
            .post-list{
                margin-top: 150px;
            }

            .over-content{
                height: 450px;
                overflow-y: scroll;
            }

            .styled-table {
                width: 100%;
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 18px;
                font-family: 'Arial', sans-serif;
                text-align: left;
            }
            .styled-table thead tr {
                background-color: #009879;
                color: #ffffff;
                text-align: left;
            }
            .styled-table th,
            .styled-table td {
                padding: 12px 15px;
                border: 1px solid #dddddd;
            }
            .styled-table tbody tr {
                border-bottom: 1px solid #dddddd;
            }
            .styled-table tbody tr:nth-of-type(even) {
                background-color: #f3f3f3;
            }
            .styled-table tbody tr:last-of-type {
                border-bottom: 2px solid #009879;
            }
            .styled-table tbody tr:hover {
                background-color: #f1f1f1;
                cursor: pointer;
            }
            .styled-select {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                background: linear-gradient(to bottom, #ffffff 0%, #e5e5e5 100%);
                box-shadow: 0 3px 5px rgba(0, 0, 0, 0.2);
                font-size: 16px;
                color: #333;
                appearance: none; /* Remove default arrow */
                -webkit-appearance: none; /* Remove default arrow for Safari */
                -moz-appearance: none; /* Remove default arrow for Firefox */
                background-position: right 10px center;
                background-repeat: no-repeat;
                background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="gray" class="bi bi-chevron-down" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/></svg>');
            }

            .styled-select:focus {
                outline: none;
                border-color: #007bff;
                background: linear-gradient(to bottom, #ffffff 0%, #f0f0f0 100%);
            }

            /* For Webkit Browsers */
            .styled-select::-webkit-scrollbar {
                width: 10px;
            }

            .styled-select::-webkit-scrollbar-thumb {
                background: #888;
                border-radius: 10px;
            }

            .styled-select::-webkit-scrollbar-thumb:hover {
                background: #555;
            }

            /* Save Button */
            .save-button {
                margin-top: 10px;
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            }

            .save-button:hover {
                background-color: #0056b3;
            }
        </style>

    </head>
    <body>
        <jsp:include page="header.jsp"/>

<<<<<<< HEAD
        <header class="p-3 text-bg-primary ">
            <div class="container">
                <div
                    class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
                    >
                    <a
                        href="#"
                        class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"
                        >
                        <img src="${pageContext.request.contextPath}/images/logo.png" alt="" width="70" height="32" />
                    </a>

                    <ul
                        class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0"
                        >
                        <li><a href="index.html" class="nav-link px-2 text-white">Home</a></li>
                        <li class="nav-item dropdown"></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle px-2 text-white" href="#" id="featuresDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Category
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="featuresDropdown">
                                <li><a class="dropdown-item" href="#">Software engineer</a></li>


                                <li><a class="dropdown-item" href="#">Marketing</a></li>
                                <li><a class="dropdown-item" href="#">Business</a></li>
                                <li><a class="dropdown-item" href="#">Design</a></li>
                            </ul>
                        </li>
                        <li><a href="#" class="nav-link px-2 text-white">About Us</a></li>
                        <li><a href="#" class="nav-link px-2 text-white">Sale</a></li>

                    </ul>

                    <form class="col-12 col-lg-6 mb-3 mb-lg-0 me-lg-3" role="search">
                        <input type="text" id="disabledTextInput" class="form-control" placeholder="Find any content">

                    </form>

                    <div class="text-end button-header">



                        <a href="cart.html" class="btn btn-outline-light">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"></path>
                            </svg>
                            Cart
                        </a>
                        <c:if test="${sessionScope.USER == null}">
                            <a href="${pageContext.request.contextPath}/auth?action=login" class="btn btn-outline-light me-2">Login | Sign up</a>
                        </c:if>  
                        <c:if test="${sessionScope.USER != null }">
                            <div class="dropdown">
                                <button type="button" id="dropdownMenuButton1" class="btn dropdown-toggle " data-bs-toggle="dropdown" aria-expanded="false"style="color: white;
                                        font-size: 20px;">
                                    ${sessionScope.USER.firstName} ${sessionScope.USER.lastName}
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <a class="dropdown-item " href="profile?action=view">Profile</a>
                                    <a class="dropdown-item " href="#">Update</a>
                                    <a class="dropdown-item " href="auth?action=logout">Logout</a>
                                </div>
                            </div>
                        </c:if> 



                    </div>


                </div>
            </div>
        </header>
        <!-- banner -->
        <div id="carouselExample" class="carousel slide">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="${pageContext.request.contextPath}/images/1.jpg" class="d-block w-100" alt="..." />
                </div>
                <div class="carousel-item ">
                    <img src="${pageContext.request.contextPath}/images/1.jpg" class="d-block w-100" alt="..." />
                </div>
                <div class="carousel-item">
                    <img src="${pageContext.request.contextPath}/images/1.jpg" class="d-block w-100" alt="..." />
                </div>
            </div>
            <button
                class="carousel-control-prev btn btn-light"
                type="button"
                data-bs-target="#carouselExample"
                data-bs-slide="prev"
                >
                <span class="carousel-control-prev-icon " aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button
                class="carousel-control-next btn btn-light"
                type="button"
                data-bs-target="#carouselExample"
                data-bs-slide="next"
                >
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

=======
        <input type="hidden" id="success" name="MESSAGE" value="${MESSAGE}"/>     
        <input type="hidden" id="error" name="MESSAGE" value="${ERROR}"/>
>>>>>>> hainvt

        <!-- Slogan -->

        <div class="container px-4 py-5" id="hanging-icons">
            <div class="row">
                <div class="header">
                    <h1>Write Better Papers and Essays with Grammar Guardian</h1>
                    <p>Grammar Guardian’s free essay-checking tool will help you review your papers for grammatical mistakes, unclear
                        sentences, and misused words. Save time and be confident your work will make the grade!</p>
                </div>
            </div>
            <div class="row">
                <form action="${pageContext.request.contextPath}/grammar-checker" method="POST">
                    <input type="hidden" name="action" value="get-result"/>
                    <div class="col d-flex justify-content-sm-between">
                        <div class="text-origin">
                            <div style="width: 100%; height: 100%">
                                <textarea required id="text" name="text" class="check-essay" placeholder="Start writing here." >${text != null ? text : ''}</textarea>
                            </div>
                            <div>
                                <div>
                                    <label for="check-type">Select Check Type:</label>
                                    <select id="check-type" name="check-type" class="styled-select">
                                        <option value="all" ${checkType == 'all' ? 'selected' : ''}>Check All</option>
                                        <option value="spell" ${checkType == 'spell' ? 'selected' : ''}>Check Spelling</option>
                                        <option value="grammar" ${checkType == 'grammar' ? 'selected' : ''}>Check Grammar</option>
                                    </select>
                                </div>
                                <div>
                                    <button class="save-button" type="submit">
                                        <c:choose>
                                            <c:when test="${USER != null}">
                                                Get Result
                                            </c:when>
                                            <c:otherwise>
                                                Get Result! Required Login
                                            </c:otherwise>
                                        </c:choose>
                                    </button>
                                    <c:choose>
                                        <c:when test="${USER != null}">
                                            <p class="login">
                                                Note: This feature requires registration to get more time to check your essay. (3 times remaining)
                                            </p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="login">Already have an account?
                                                <a href="${pageContext.request.contextPath}/auth?action=login">Log in</a>
                                            </p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="main-content" style=" margin: 0;
                             width: 620px;
                             height: fit-content;
                             padding: 0;">
                            <c:if test="${segments != null}">
                                <div class="check-essay">
                                    <c:forEach var="segment" items="${segments}">
                                        <c:choose>
                                            <c:when test="${segment.error}">
                                                <span class="error" style="color: red">${segment.text}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span>${segment.text}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </div>
                                <h4 style="color: green">Errors and Suggestions:</h4>
                                <div class="over-content">
                                    <table class="styled-table">
                                        <thead>
                                            <tr>
                                                <th>Error Message</th>
                                                <th>Error Word</th>
                                                <th>Position</th>
                                                <th>Suggested Correction</th>
                                            </tr>
                                        </thead>
                                        <tbody class="over-content">
                                            <c:forEach var="match" items="${matches}">
                                                <tr>
                                                    <td>${match.message}</td>
                                                    <td  style="color: red">${text.substring(match.fromPos, match.toPos)}</td>
                                                    <td>${match.fromPos}-${match.toPos}</td>
                                                    <td style="color: green">
                                                        <c:forEach var="suggestion" items="${match.suggestedReplacements}">
                                                            <c:out value="${suggestion}" />&nbsp;
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <a class="save-button" data-bs-toggle="modal" data-bs-target="#save-post">Save your essay</a>
                                <a class="edit-button" href="${pageContext.request.contextPath}/auth">Continue to check</a>
                            </c:if>
                        </div>

                    </div>
                    <div>

                    </div>
                </form>
                <!-- Modal -->
                <div class="modal fade" id="save-post" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <form class="modal-content" action="${pageContext.request.contextPath}/grammar-checker" method="POST">
                            <input type="hidden" name="action" value="save-post"/>
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Save your essay</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Please enter your essay's title:
                                <input name="title" type="text"/>
                                </br>
                                This action will store your essay in your post history.
                                You can view it in your user profile at "Post History".
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Save essay</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Category -->
            <div style="margin-top: 170px"  class="album  bg-light">
                <h2 class="text-body-emphasis text-center py-3">
                    You ready to learn new things ?
                </h2>
                <div class="container">
                    <form action="auth" class="d-flex" style="margin-bottom: 15px;">
                        <!--<input type="hidden" name="action" />-->
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search" value="${search}">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        <c:forEach var="post" items="${LIST_POST}">
                            <div class="col-md-3">
                                <div class="card shadow-sm">
                                    <img src="${pageContext.request.contextPath}/images/csd.jpg" alt="">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-center align-items-center" style="flex-direction: column">
                                            <div>
                                                <b>Title</b>: ${post.title}
                                            </div>
                                            <div>
                                                ${post.createAt}
                                            </div>
                                            <div class="btn-group">

                                                <a  href="PostDetailController?postId=${post.postId}">
                                                    <button type="button" class="btn btn-sm btn-outline-secondary">
                                                        Details
                                                    </button>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <nav aria-label="Page navigation example" style="display: flex; justify-content:center;margin-top: 15px;">
                        <ul class="pagination">
                            <c:choose>
                                <c:when test ="${selectedPage - 1 < 1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">«</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="auth?search=${search}&index=${selectedPage-1}">«</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="i" begin="1" end="${endP}">
                                <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="auth?search=${search}&index=${i}">${i}</a> <li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test ="${selectedPage >= endP}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">»</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="auth?search=${search}&index=${selectedPage+1}">»</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>

            <script
                src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"
            ></script>

            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script>
                var error = document.getElementById('error');
                var message = document.getElementById('success');
                if (message.value) {
                    Swal.fire({
                        title: message.value,
                        icon: "success",
                        showCancelButton: true,
                        confirmButtonText: "Confirm",
                    })
                }
                if (error.value) {
                    Swal.fire({
                        title: error.value,
                        icon: "info",
                        showCancelButton: true,
                        confirmButtonText: "Confirm",
                    })
                }


                function countWords() {
                    var text = document.getElementById("textarea").value;
                    var words = text.trim().split(/\s+/);
                    var wordCount = words.filter(function (word) {
                        return word.length > 0;
                    }).length;
                    document.getElementById("wordCount").innerHTML = "Word Count: " + wordCount;
                    document.getElementById("word-cout-input").value = wordCount;
                }
            </script>
    </body>
</html>
