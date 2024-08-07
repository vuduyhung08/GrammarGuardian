<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css" />
            <header class="p-3 text-bg-primary " style="background: linear-gradient(to right, #2F80ED, #56CCF2);">
                <div class="container">
                    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                        <ul class="nav col-lg-auto me-lg-auto justify-content-center mb-md-0"
                            style="margin-right: 30px !important">
                            <li><a href="auth" class="nav-link px-2 text-white">Home</a></li>
                            <li class="nav-item dropdown"></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle px-2 text-white" href="#" id="featuresDropdown"
                                    role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Category
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="featuresDropdown">
                                    <li><a class="dropdown-item" href="#">Software engineer</a></li>

                                    <li><a class="dropdown-item" href="#">Marketing</a></li>
                                    <li><a class="dropdown-item" href="#">Business</a></li>
                                    <li><a class="dropdown-item" href="#">Design</a></li>
                                </ul>
                            </li>
                            <li><a href="#" class="nav-link px-2 text-white">About Us</a></li>
                            <li><a href="#" class="nav-link px-2 text-white">Sale</a></li>

                        </ul>

                        <form class="col-5  mb-3 mb-lg-0 me-lg-3" role="search">
                            <input type="text" id="disabledTextInput" class="form-control"
                                placeholder="Find any content">

                        </form>

                        <div
                            class="text-end button-header nav col-3 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                            <c:if test="${sessionScope.USER != null}">
                            </c:if>

                            <a href="RegisterPackagePageController" class="btn btn-success">Packages</a>
                            <div>
                                <c:if test="${sessionScope.USER == null}">
                                    <!--<a href="${pageContext.request.contextPath}/auth?action=login" class="btn btn-outline-light me-2">Login!</a>-->
                                    <a href="${pageContext.request.contextPath}/LoginController"
                                        class="btn btn-outline-light me-2">Login!</a>
                                </c:if>
                                <c:if test="${sessionScope.USER != null }">
                                    <div class="dropdown">
                                        <button type="button" id="dropdownMenuButton1" class="btn dropdown-toggle "
                                            data-bs-toggle="dropdown" aria-expanded="false" style="color: #06A3DA;
                                    font-size: 20px; color: #fff">
                                            ${sessionScope.USER.firstName} ${sessionScope.USER.lastName}
                                        </button>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                            <a class="dropdown-item " href="UserProfileController">Profile</a>
                                            <a class="dropdown-item " href="TransitionHistoryController">Wallet:
                                                <fmt:formatNumber value="${WALLET}" pattern="#" /> Point
                                            </a>
                                            <a class="dropdown-item " href="LogoutController">Logout</a>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                    </div>
                </div>
            </header>