

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
    </body>
</html>
