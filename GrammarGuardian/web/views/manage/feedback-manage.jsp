<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Wallet Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="headeradmin.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navadmin.jsp"/>

                <div  class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <h1>Feedback Management</h1>


                    <form action="GetAllFeedbackController" class="d-flex" style="margin-bottom: 15px; width: 30%">
                        <select name="status" class="form-select" aria-label="Default select example">
                            <option  value="" selected>Tất cả</option>
                            <option   ${status == 4 ? 'selected' : ''} value="4">All</option>  
                            <option   ${status == 0 ? 'selected' : ''} value="0">Pending</option>
                            <option   ${status == 1 ? 'selected' : ''} value="1">Approve</option>
                            <option   ${status == 2 ? 'selected' : ''}  value="2">Rejected</option>
                        </select>
                        <button style="margin-left: 15px" type="submit" class="btn btn-success">Filter</button>
                    </form>

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Image</th>
                                <th>Content</th>
                                <th>CreateAt</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${LIST_FEEDBACK}" var="feed">
                                <tr>
                                    <td>${order.id}</td>      
                                    <td>  <c:choose>
                                            <c:when test="${feed.user.image != null}">
                                                <img src="data:image/png;base64,${feed.user.image}" style="width: 100px"  alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" >
                                            </c:when>
                                            <c:otherwise>
                                                <img src="https://placehold.co/100x100" style="width: 100px" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" >
                                            </c:otherwise>
                                        </c:choose></td>
                                    <td>${feed.content}</td>
                                    <td>${feed.createAt}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${feed.status == 0}"><p style="color: #ccc">Pending</p></c:when>
                                            <c:when test="${feed.status == 1}"><p style="color: green">Approved</p></c:when>
                                            <c:when test="${feed.status == 2}"><p style="color: red">Reject</p></c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${feed.status == 0}">
                                            <form action="GetAllFeedbackController" method="post">
                                                <input type="hidden" name="action" value="approve">
                                                <input type="hidden" name="feedbackId" value="${feed.id}">
                                                <button type="submit" class="btn btn-success">Approve</button>
                                            </form>
                                            <form action="GetAllFeedbackController" method="post">
                                                <input type="hidden" name="action" value="reject">
                                                <input type="hidden" name="feedbackId" value="${feed.id}">
                                                <button type="submit" class="btn btn-danger">Reject</button>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <nav aria-label="Page navigation example">
                        <ul class="pagination" style="display: flex">

                            <c:choose>
                                <c:when test ="${selectedPage - 1 < 1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">«</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="GetAllFeedbackController?status=${status}&index=${selectedPage-1}">«</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="i" begin="1" end="${endP}">
                                <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="GetAllFeedbackController?status=${status}&index=${i}">${i}</a> <li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test ="${selectedPage >= endP}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">»</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="GetAllFeedbackController?status=${status}&index=${selectedPage+1}">»</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>


                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
