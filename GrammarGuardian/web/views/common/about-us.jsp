<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About us</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <style>

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

            .all-content{
                box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
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
        <!-- About 1 - Bootstrap Brain Component -->
        <section class="py-3 py-md-5">
            <div class="container">
                <div class="row gy-3 gy-md-4 gy-lg-0 align-items-lg-center">
                    <div class="col-12 col-lg-6 col-xl-5">
                        <img class="img-fluid rounded" loading="lazy" src="./assets/img/about-img-1.jpg" alt="About 1">
                    </div>
                    <div class="col-12 col-lg-6 col-xl-7">
                        <div class="row justify-content-xl-center">
                            <div class="col-12 col-xl-11">
                                <h2 class="mb-3">Who Are We?</h2>
                                <p class="lead fs-4 text-secondary mb-3">
                                    At GrammarGuardian, we understand the importance of clear and correct communication in English. Our state-of-the-art platform offers you the most reliable and user-friendly experience for checking grammar and syntax errors in your writing. 
                                </p>
                                <p class="mb-5">Whether you are a student aiming to perfect an essay, a professional crafting a business document, or someone learning English as a second language, GrammarGuardian is here to assist you..</p>
                                <div class="row gy-4 gy-md-0 gx-xxl-5X">
                                    <div class="col-12 col-md-6">
                                        <div class="d-flex">
                                            <div class="me-4 text-primary">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-gear-fill" viewBox="0 0 16 16">
                                                <path d="M9.405 1.05c-.413-1.4-2.397-1.4-2.81 0l-.1.34a1.464 1.464 0 0 1-2.105.872l-.31-.17c-1.283-.698-2.686.705-1.987 1.987l.169.311c.446.82.023 1.841-.872 2.105l-.34.1c-1.4.413-1.4 2.397 0 2.81l.34.1a1.464 1.464 0 0 1 .872 2.105l-.17.31c-.698 1.283.705 2.686 1.987 1.987l.311-.169a1.464 1.464 0 0 1 2.105.872l.1.34c.413 1.4 2.397 1.4 2.81 0l.1-.34a1.464 1.464 0 0 1 2.105-.872l.31.17c1.283.698 2.686-.705 1.987-1.987l-.169-.311a1.464 1.464 0 0 1 .872-2.105l.34-.1c1.4-.413 1.4-2.397 0-2.81l-.34-.1a1.464 1.464 0 0 1-.872-2.105l.17-.31c.698-1.283-.705-2.686-1.987-1.987l-.311.169a1.464 1.464 0 0 1-2.105-.872l-.1-.34zM8 10.93a2.929 2.929 0 1 1 0-5.86 2.929 2.929 0 0 1 0 5.858z" />
                                                </svg>
                                            </div>
                                            <div>
                                                <h2 class="h4 mb-3">Versatile Brand</h2>
                                                <p class="text-secondary mb-0">We are crafting a digital method that subsists life across all mediums.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-6">
                                        <div class="d-flex">
                                            <div class="me-4 text-primary">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-fire" viewBox="0 0 16 16">
                                                <path d="M8 16c3.314 0 6-2 6-5.5 0-1.5-.5-4-2.5-6 .25 1.5-1.25 2-1.25 2C11 4 9 .5 6 0c.357 2 .5 4-2 6-1.25 1-2 2.729-2 4.5C2 14 4.686 16 8 16Zm0-1c-1.657 0-3-1-3-2.75 0-.75.25-2 1.25-3C6.125 10 7 10.5 7 10.5c-.375-1.25.5-3.25 2-3.5-.179 1-.25 2 1 3 .625.5 1 1.364 1 2.25C11 14 9.657 15 8 15Z" />
                                                </svg>
                                            </div>
                                            <div>
                                                <h2 class="h4 mb-3">Digital Agency</h2>
                                                <p class="text-secondary mb-0">We believe in innovation by merging primary with elaborate ideas.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <c:forEach items="${LIST_FEEDBACK}" var="comment">
                        <div class="row p-3" id="faq">
                            <div class="col-md-11" style="display: flex">
                                <div>
                                    <c:choose>
                                        <c:when test="${comment.user.image != null}">
                                            <img src="data:image/png;base64,${comment.user.image}" alt="Profile picture" id="profile-picture"  style="width: 50px" >
                                        </c:when>
                                        <c:otherwise>
                                            <img src="https://placehold.co/100x100" alt="Profile picture" id="profile-picture" style="width: 50px">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div style="margin-left: 20px">
                                    <p class="heading-md">name: ${comment.user.userName}</p>
                                    <span style="color: gray">${comment.createAt}</span>
                                    <p style="padding-top: 10px; font-size: 18px">
                                        ${comment.content}
                                    </p>
                                </div>

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
                                    <li class="page-item"><a class="page-link" href="FeedbackController?index=${selectedPage-1}">«</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="i" begin="1" end="${endP}">
                                <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="FeedbackController?index=${i}">${i}</a> <li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test ="${selectedPage >= endP}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">»</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="FeedbackController?index=${selectedPage+1}">»</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
                    <div class=" p-3">
                        <form action="FeedbackController" method="POST">
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

        </section>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
