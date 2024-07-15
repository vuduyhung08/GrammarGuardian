<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />
        <link rel="stylesheet" href="main.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
        <style>
            .checked {
                color: orange;
            }

            .middle {
                margin-top: 10px;
                float: left;
                width: 70%;
            }

            .bar {
                height: 13px;
                background-color: gray;
            }

            .bar-5 {
                width: 60%;
            }

            .bar-4 {
                width: 30%;
            }

            .bar-3 {
                width: 10%;
            }

            .bar-2 {
                width: 4%;
            }

            .bar-1 {
                width: 15%;
            }

        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>


        <div class="container">
            <div class="col-md-12 p-5 ">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                </ul>
                <a class="btn btn-danger" href="AddToFavouriteList?postId=${POST.postId}">Add to favourite</a>
                <h2 id="course-intro" class="p-3">Title:  <span>
                        ${POST.title}
                    </span></h2>
                <div style="display: flex; justify-content: space-between">
                    <div>
                        <h3 class="p-3">Content </h3>
                        <p>
                            ${POST.description} 
                        </p>
                    </div>
                </div>


                <hr>
                <c:forEach items="${COMMENTS}" var="comment">
                    <div class="row p-3" id="faq">
                        <div class="col-md-11">
                            <p class="heading-md">name: ${comment.userName}</p>
                            <span style="color: gray">${comment.createAt}</span>
                            <p style="padding-top: 10px; font-size: 18px">
                                ${comment.content}
                            </p>
                        </div>
                    </div>
                </c:forEach>

                <nav aria-label="Page navigation example" style="display: flex; justify-content:center;margin-top: 15px;">
                    <ul class="pagination">
                        <c:choose>
                            <c:when test ="${selectedPage - 1 < 1}">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#">«</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="PostDetailController?postId=${POST.postId}&index=${selectedPage-1}">«</a></li>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="i" begin="1" end="${endP}">
                            <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="PostDetailController?postId=${POST.postId}&index=${i}">${i}</a> <li>
                            </c:forEach>
                            <c:choose>
                                <c:when test ="${selectedPage >= endP}">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#">»</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="PostDetailController?postId=${POST.postId}&index=${selectedPage+1}">»</a></li>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                </nav>
                <div class=" p-3">
                    <form action="PostCommentController" method="POST">
                        <div class="form-group">
                            <input type="hidden" name="postId" value="${POST.postId}"/>
                            <label for="exampleFormControlTextarea1">Your Feedback</label >
                            <textarea
                                class="form-control"
                                id="feedback" name="feedback"
                                rows="3"
                                ></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary mt-2">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
                            <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576zm6.787-8.201L1.591 6.602l4.339 2.76z"></path>
                            </svg>
                            Send
                        </button>
                    </form>

                </div>

            </div>
        </div>


        <jsp:include page="footer.jsp"/>

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
