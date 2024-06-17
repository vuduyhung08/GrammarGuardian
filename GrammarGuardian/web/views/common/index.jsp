
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
        <style>
            .category{
                justify-content: space-between;
            }
            /*                      .comment-box {
                                        width: 100%;
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
            .text-origin{
                width: 620px;
                height: 500px;
            }
            .post-list{
                margin-top: 100px;
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
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <input type="hidden" id="success" name="MESSAGE" value="${MESSAGE}"/>
        <!-- Slogan -->
        <div class="container" id="hanging-icons">
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
                                <button type="submit" class="btn btn-primary">Save it</button>
                            </div>
                        </form>
                    </div>

                </div>
                <!--END DIV MODAL-->
            </div>
        </div>
        <!-- Category -->
        <div class="post-list">
            <h2 class="text-body-emphasis text-center py-3">
                You ready to learn new things ?
            </h2>
            <div class="container">
                <form action="auth" class="d-flex" style="margin-bottom: 15px;">
                    <input type="hidden" name="action" />
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
                                            <a  data-bs-toggle="modal" data-bs-target="#post-detail-${post.postId}">
                                                <button type="button" class="btn btn-sm btn-outline-secondary">
                                                    Details
                                                </button>
                                            </a>
                                        </div>
                                        <!-- Modal -->
                                        <div class="modal fade" id="post-detail-${post.postId}" tabindex="-1" aria-labelledby="post-detail-${post.postId}" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Post details</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div>
                                                            <b>Title: </b> ${post.title}
                                                        </div>
                                                        <div>
                                                            <b>Description: </b>${post.description}
                                                        </div>
                                                    </div>

                                                    </br>
                                                    <div class="comment-box" style="border-top: 1px #ccc solid">

                                                        <h4>Comments</h4>
                                                        <div class="comment-input">
                                                            <textarea id="comment-content" placeholder="Write a comment..."></textarea>
                                                            <button onclick="postComment()">Post Comment</button>
                                                        </div>
                                                        <div class="comment-display">
                                                            <!-- Comments will be displayed here -->
                                                        </div>
                                                    </div>

                                                    </br>
                                                    <div class="modal-footer">
                                                        <a class="btn btn-danger" href="profile?action=add-favour&postId=${post.postId}">Add to favourite</a>
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>
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
        <%--<jsp:include page="footer.jsp"/>--%>


        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
                                                                var message = document.getElementById('success');
                                                                if (message.value) {
                                                                    Swal.fire({
                                                                        title: message.value,
                                                                        icon: "success",
                                                                        showCancelButton: true,
                                                                        confirmButtonText: "Confirm",
                                                                    })
                                                                }
        </script>
    </body>
</html>
