

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <form action="${pageContext.request.contextPath}/grammar-checker" method="POST">
                <input hidden name="action" value="get-results"/>
                <textarea required id="text" name="text" class="check-essay" placeholder="Start writing here."></textarea>
                <button type="submit">Get results</button>
            </form>
        </div>
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
        <div class="album  bg-light">
            <h2 class="text-body-emphasis text-center py-3">
                You ready to learn new things ?
            </h2>
            <div class="container">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <c:forEach var="post" items="${LIST_POST}">
                        <div class="col-md-3">
                            <div class="card shadow-sm">
                                <img src="${pageContext.request.contextPath}/images/csd.jpg" alt="">
                                <div class="card-body">
                                    <div class="d-flex justify-content-center align-items-center">
                                        <div class="btn-group">
                                            <a href="#">
                                                <button type="button" class="btn btn-sm btn-outline-secondary">
                                                    ${post.title}
                                                </button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </body>
</html>
