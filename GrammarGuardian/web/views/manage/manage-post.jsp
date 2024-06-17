<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Timeshare</title>
        <link
            href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
            rel="stylesheet"
            />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
            />
    </head>
    <body>
        <div class="flex">
            <div class="w-2/12 bg-gray-800 text-white min-h-screen p-4">
                <div class="mb-4">
                    <h1 class="text-2xl font-bold flex items-center p-4">
                        <i class="fas fa-building mr-2"></i>Timeshare
                    </h1>
                </div>
                <nav class="space-y-2">

                    <a
                        href="GetAllUserController"
                        class="block py-2 px-4 rounded text-sm flex items-center hover:bg-gray-700"
                        >
                        <i class="fas fa-user mr-2"></i>User Management
                    </a>
                    <a
                        href="GetAllPostConfirmController"
                        class="block py-2 px-4 rounded text-sm text-white flex items-center bg-blue-600"
                        >
                        <i class="fas fa-clipboard mr-2"></i>Post Management
                    </a>

                    <a
                        href="${pageContext.request.contextPath}/auth?action=logout"
                        class="block py-2 px-4 rounded text-sm hover:bg-gray-700 flex items-center"
                        >
                        <i class="fas fa-sign-out-alt mr-2"></i>Logout
                    </a>
                </nav>
            </div>
            <div class="w-9/12 p-8">
                <div class="flex justify-between mb-4">
                    <h2 class="text-2xl font-bold">Post Management</h2>
                    <form class="relative" action="GetAllPostConfirmController">
                        <input
                            type="text" name="search"
                            class="border border-gray-300 rounded pl-8 pr-4 py-2"
                            placeholder="Search with title..." value="${search}"
                            />
                        <i
                            class="fas fa-search absolute top-1/2 transform -translate-y-1/2 left-2 text-gray-400"
                            ></i>
                        <button type="submit" class="btn btn-success">Search</button>
                    </form>
                </div>
                <div class="grid grid-cols-4 gap-4">
                    <!-- Card 1 -->
                    <c:forEach items="${LIST_POST}" var="post">
                        <div class="bg-white rounded-lg shadow-md overflow-hidden">
                            <img
                                src="https://placehold.co/100x100"
                                alt="Image"
                                class="w-full"
                                />
                            <div class="p-4">
                                <h3 class="text-xl font-bold">${post.title}</h3>
                                <p class="text-gray-600" style="overflow: hidden;
                                   max-height: 50px; height: 50px">
                                    ${post.description}
                                </p>
                            </div>
                            <div class="flex justify-between p-4">
                                <a href="ChangeStatusPostController?action=accept&postId=${post.postId}" class="text-green-500">
                                    <i class="fas fa-check-circle text-2xl"></i>
                                </a>
                                <a  href="ChangeStatusPostController?action=reject&postId=${post.postId}" class="text-red-500">
                                    <i class="fas fa-times-circle text-2xl"></i>
                                </a>
                                <a class="text-blue-500">
                                    <i class="fas fa-info-circle text-2xl"></i>
                                </a>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <div class="mt-4 flex justify-end">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination" style="display: flex">
                            <c:choose>
                                <c:when test ="${selectedPage - 1 < 1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">«</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="GetAllPostConfirmController?search=${search}&index=${selectedPage-1}">«</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="i" begin="1" end="${endP}">
                                <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="GetAllPostConfirmController?search=${search}&index=${i}">${i}</a> <li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test ="${selectedPage >= endP}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">»</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="GetAllPostConfirmController?search=${search}&index=${selectedPage+1}">»</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </body>
</html>
