
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
            /*            .comment-box {
                            width: 400px;
                            margin: auto;
                            border: 1px solid #ccc;
                            padding: 20px;
                            box-shadow: 2px 2px 8px #aaa;
                        }*/

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
        </style>

    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <input type="hidden" id="success" name="MESSAGE" value="${MESSAGE}"/>     
        <input type="hidden" id="error" name="MESSAGE" value="${ERROR}"/>


        <div id="carouselExample" class="carousel slide">
            <!--            <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="${pageContext.request.contextPath}/images/1.jpg" class="d-block w-100" alt="..." />
                            </div>
                            <div class="carousel-item ">
                                <img src="${pageContext.request.contextPath}/images/1.jpg" class="d-block w-100" alt="..." />
                            </div>
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}/images/1.jpg" class="d-block w-100" alt="..." />
                            </div>
                        </div>-->
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
                    <div class="col d-flex">
                        <div class="main-content">
                            <c:choose>
                                <c:when test="${segments != null}">
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
                                        <h2 style="color: green">Errors and Suggestions:</h2>
                                        <ul>
                                            <c:forEach var="match" items="${matches}">
                                                <li>
                                                    ${match.message} at position ${match.fromPos}-${match.toPos}
                                                    <br>
                                                    Suggested correction: <c:forEach var="suggestion" items="${match.suggestedReplacements}">
                                                        <c:out value="${suggestion}" />&nbsp;
                                                    </c:forEach>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <a class="save-button"  data-bs-toggle="modal" data-bs-target="#save-post" >Save your essay</a>       
                                    <a class="edit-button" href="${pageContext.request.contextPath}/auth">Continue to check</a>
                                </c:when>
                                <c:otherwise>
                                    <textarea required id="textarea" name="text" oninput="countWords()" class="check-essay" placeholder="Start writing here."></textarea>
                                    <div id="wordCount">Word Count: 0</div>
                                    <input type="hidden" name="word-count" id="word-cout-input">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="sidebar">
                            <div class="suggestions">
                                <h2>Suggestions</h2>
                                <p>Let’s get started.</p>
                                <ul>
                                    <li>Step 1: Add your text, and we will hightlight any issues.</li>
                                    <li>Step 2: Check your text onemore time submit.</li>
                                    <li>Step 3: Click a button bellow.</li>
                                </ul>
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
                                            Note: This feature need to register to get more time to check your esssay. (3 times remain)
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
                        <button type="submit" class="btn btn-primary">Save it</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Category -->
    <div class="album  bg-light">
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
                            <img src="${pageContext.request.contextPath}/images/csd." alt="">
                            <div class="card-body">jpg
                                <div class="d-flex justify-content-center align-items-center" style="flex-direction: column">
                                    <div>
                                        <b>Title</b>: ${post.title}
                                    </div>
                                    <div>
                                        ${post.createAt}
                                    </div>
                                    <div class="btn-group">
                                        <!--<a  data-bs-toggle="modal" data-bs-target="#post-detail-${post.postId}">-->        
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





    <!-- news -->
    <div class="album py-3 bg-light">
        <h2 class="text-body-emphasis text-center py-3">
            Top Contribuitor
        </h2>
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

                <c:forEach var="lectures" items="${lectures}">
                    <div class="col-md-2">
                        <div class="card shadow-sm">
                            <img src="${pageContext.request.contextPath}/images/instructor.jpg" alt="">
                            <div class="card-body">
                                <div class="btn-group d-flex flex-column-reverse justify-content-center align-items-center">
                                    <a href="viewinstuctor?id=${lectures.id}">
                                        <button type="button" class="btn btn-sm btn-outline-secondary">
                                            ${lectures.fullName}
                                        </button>
                                    </a>
                                    <p>Software engineer</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>


    <jsp:include page="footer.jsp"/>

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
