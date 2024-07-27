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
        <jsp:include page="headeradmin.jsp"/>
        <div style="margin-left:10px" class="flex">
            <nav
                id="sidebarMenu"
                >
                <div class="position-sticky pt-3">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/admin/DashboardController">
                                Dashboard
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/GetAllUserController">
                                User Management
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/GetAllPostInController">
                                Post Management
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/UserWalletManageController">
                                Wallet Management
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/GetAllPackageController">
                                Package Management
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/GetAllFeedbackController">
                                Feedback Management
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/LogoutController">
                                Log out
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="w-9/12 p-8">
                <div class="flex justify-between mb-4">
                    <h2 class="text-2xl font-bold">Post Management</h2>
                    <form class="relative" action="GetAllPostInController" id="postForm">
                        <input
                            type="text"
                            name="search"
                            class="border border-gray-300 rounded pl-8 pr-4 py-2"
                            placeholder="Search with title..."
                            value="${search}"
                            />
                        <i
                            class="fas fa-search absolute top-1/2 transform -translate-y-1/2 left-2 text-gray-400"
                            ></i>
                        <button type="submit" class="btn btn-success">Search</button>
                        Select post:
                        <select id="postSelect" name="searchType" class="post-select">
                            <option value="all" ${"all".equals(searchType) ? "selected" : "selected"}>All</option>
                            <option value="0" ${"0".equals(searchType) ? "selected" : ""}>Save posts</option>
                            <option value="1" ${"1".equals(searchType) ? "selected" : ""}>Pending posts</option>
                            <option value="3" ${"3".equals(searchType) ? "selected" : ""}>Confirm posts</option>
                        </select>
                    </form>
                </div>
                <div class="grid grid-cols-4 gap-4">
                    <!-- Card 1 -->
                    <c:forEach items="${LIST_POST}" var="post">
                        <div style="display: grid; justify-content: space-between" class="bg-white shadow-md overflow-hidden">
                            <div class="p-4">
                                <h3>User: ${post.userName}</h3>
                                <h3 class="text-xl font-bold">Title: ${post.title}</h3>
                                <p class="text-gray-600" style="overflow: hidden;
                                   max-height: 50px; height: 50px">
                                    ${post.description}
                                </p>
                                <p class="text-gray-600" style="overflow: hidden;
                                   max-height: 50px; height: 50px">
                                    Create At: ${post.createAt}
                                </p> 
                            </div>
                            <div style="flex: 1" class="flex justify-between p-4">
                                <c:if test="${post.status == 1}">
                                    <a href="#" data-bs-toggle="modal" data-bs-target="#accepte-${post.postId}" aria-hidden="true"
                                       class="text-green-500">
                                        <i class="fas fa-check-circle text-2xl"></i>
                                    </a>
                                </c:if>
                                <!-- accpet form-->
                                <!-- Modal -->
                                <div class="modal fade" id="accepte-${post.postId}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" >Are you sure to allow this post appear in homepage</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                This action will allow PostId - ${post.postId} render in homepage.
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <a class="btn btn-success"   href="ChangeStatusPostController?action=accept&postId=${post.postId}">Confirm</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <c:if test="${post.status == 1}">
                                    <a  href="#" class="text-red-500" data-bs-toggle="modal" data-bs-target="#reject-${post.postId}" aria-hidden="true">
                                        <i class="fas fa-times-circle text-2xl"></i>
                                    </a>
                                </c:if>

                                <!-- REJECT -->
                                <!-- Modal -->
                                <div class="modal fade" id="reject-${post.postId}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" >Reject this post</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                This action will reject PostId - ${post.postId} and change this post to reject status
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <a class="btn btn-danger"   href="ChangeStatusPostController?action=reject&postId=${post.postId}">Confirm</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <a class="text-blue-500" data-bs-toggle="modal" data-bs-target="#post-detail-${post.postId}" aria-hidden="true">
                                    <i class="fas fa-info-circle text-2xl"></i>
                                </a>
                                <div class="modal fade" id="post-detail-${post.postId}" tabindex="-1" aria-labelledby="post-detail-${post.postId}" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content" style="width: 150%; transform: translateX(-100px)">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Post details</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div>
                                                    <b>Title: </b> ${post.title}
                                                </div>
                                                <div>
                                                    <b>Description: </b>
                                                    <textarea readonly style="width: 100%; height: 300px; text-align: left">
                                                        ${post.description}
                                                    </textarea>
                                                </div>
                                            </div>
                                            <div class="modal-footer" style="display: flex;">
                                                <c:if test="${post.status == 1 || post.status == 2}">
                                                    <a class="btn btn-success"  href="ChangeStatusPostController?action=accept&postId=${post.postId}">Appove</a>        
                                                    <a class="btn btn-danger"  href="ChangeStatusPostController?action=reject&postId=${post.postId}">Reject</a>
                                                </c:if>

                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

                                            </div>
                                        </div>
                                    </div>
                                </div>

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
                                    <li class="page-item"><a class="page-link" href="GetAllPostInController?search=${search}&index=${selectedPage-1}&searchType=${searchType}">«</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="i" begin="1" end="${endP}">
                                <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="GetAllPostInController?search=${search}&index=${i}&searchType=${searchType}">${i}</a> <li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test ="${selectedPage >= endP}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">»</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="GetAllPostInController?search=${search}&index=${selectedPage+1}&searchType=${searchType}">»</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </body>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"
    ></script>
</html>
