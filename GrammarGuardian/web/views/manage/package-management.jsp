<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Package Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="headeradmin.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navadmin.jsp"/>
                <div  class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <h1>Package Management</h1>
                    <a href="${pageContext.request.contextPath}/admin/AddPackagePageController" class="btn btn-primary mb-3">Add Package</a>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Limit Text</th>
                                <th>Check Time</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${PACKAGES}" var="p">
                                <tr>
                                    <td>${p.id}</td>
                                    <td>${p.title}</td>
                                    <td>${p.description}</td>
                                    <td>${p.price}</td>
                                    <td>${p.limitText}</td>
                                    <td>${p.checkTime}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${!p.status}">Inactive</c:when>
                                            <c:when test="${p.status}">Active</c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="ChangeStatusPackageController?packageId=${p.id}" class="btn btn-danger">Change status</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
