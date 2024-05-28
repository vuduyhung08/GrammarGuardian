
<<<<<<< HEAD
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Home - Grammar Guardian</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css" />
        <style>
            .category{
                justify-content: space-between;
            }
        </style>


    </head>
    <body>

        <header class="p-3 text-bg-primary ">
            <div class="container">
                <div
                    class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
                    >
                    <a
                        href="#"
                        class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"
                        >
                        <img src="${pageContext.request.contextPath}/images/logo.png" alt="" width="70" height="32" />
                    </a>

                    <ul
                        class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0"
                        >
                        <li><a href="index.html" class="nav-link px-2 text-white">Home</a></li>
                        <li class="nav-item dropdown"></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle px-2 text-white" href="#" id="featuresDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
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

                    <form class="col-12 col-lg-6 mb-3 mb-lg-0 me-lg-3" role="search">
                        <input type="text" id="disabledTextInput" class="form-control" placeholder="Find any content">

                    </form>

                    <div class="text-end button-header">



                        <a href="cart.html" class="btn btn-outline-light">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"></path>
                            </svg>
                            Cart
                        </a>
                        <c:if test="${sessionScope.USER == null}">
                            <a href="${pageContext.request.contextPath}/auth?action=login" class="btn btn-outline-light me-2">Login | Register</a>
                        </c:if>  
                        <c:if test="${sessionScope.USER != null }">
                            <div class="dropdown">
                                <button type="button" id="dropdownMenuButton1" class="btn dropdown-toggle " data-bs-toggle="dropdown" aria-expanded="false"style="color: #06A3DA;
                                        font-size: 20px;">
                                    ${sessionScope.USER.firstName} ${sessionScope.USER.lastName}
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <a class="dropdown-item " href="profile?action=view">Profile</a>
                                    <a class="dropdown-item " href="#">Update</a>
                                    <a class="dropdown-item " href="auth?action=logout">Logout</a>
                                </div>
                            </div>
                        </c:if> 



                    </div>


                </div>
            </div>
        </header>
        <!-- banner -->
        <div id="carouselExample" class="carousel slide">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="${pageContext.request.contextPath}/images/1.jpg" class="d-block w-100" alt="..." />
                </div>
                <div class="carousel-item ">
                    <img src="${pageContext.request.contextPath}/images/1.jpg" class="d-block w-100" alt="..." />
                </div>
                <div class="carousel-item">
                    <img src="${pageContext.request.contextPath}/images/1.jpg" class="d-block w-100" alt="..." />
                </div>
            </div>
            <button
                class="carousel-control-prev btn btn-light"
                type="button"
                data-bs-target="#carouselExample"
                data-bs-slide="prev"
                >
                <span class="carousel-control-prev-icon " aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button
                class="carousel-control-next btn btn-light"
                type="button"
                data-bs-target="#carouselExample"
                data-bs-slide="next"
                >
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>


        <!-- Slogan -->

        <div class="container px-4 py-5" id="hanging-icons">
            <h2 class="pb-2 border-bottom">Hanging icons</h2> 
            <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
                <div class="col d-flex align-items-start">
                    <div
                        class="icon-square text-body-emphasis bg-body-secondary d-inline-flex align-items-center justify-content-center fs-4 flex-shrink-0 me-3"
                        >
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            height="30"
                            fill="currentColor"
                            class="bi bi-check2-circle"
                            viewBox="0 0 16 16"
                            >
                        <path
                            d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0z"
                            ></path>
                        <path
                            d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l7-7z"
                            ></path>
                        </svg>
                    </div>
                    <div>
                        <h3 class="fs-2 text-body-emphasis">Easy to learn.</h3>
                        <p>So easy to use, even you haven't learn before.</p>
                    </div>
                </div>
                <div class="col d-flex align-items-start">
                    <div
                        class="icon-square text-body-emphasis bg-body-secondary d-inline-flex align-items-center justify-content-center fs-4 flex-shrink-0 me-3"
                        >
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            height="30"
                            fill="currentColor"
                            class="bi bi-mortarboard"
                            viewBox="0 0 16 16"
                            >
                        <path
                            d="M8.211 2.047a.5.5 0 0 0-.422 0l-7.5 3.5a.5.5 0 0 0 .025.917l7.5 3a.5.5 0 0 0 .372 0L14 7.14V13a1 1 0 0 0-1 1v2h3v-2a1 1 0 0 0-1-1V6.739l.686-.275a.5.5 0 0 0 .025-.917l-7.5-3.5ZM8 8.46 1.758 5.965 8 3.052l6.242 2.913L8 8.46Z"
                            ></path>
                        <path
                            d="M4.176 9.032a.5.5 0 0 0-.656.327l-.5 1.7a.5.5 0 0 0 .294.605l4.5 1.8a.5.5 0 0 0 .372 0l4.5-1.8a.5.5 0 0 0 .294-.605l-.5-1.7a.5.5 0 0 0-.656-.327L8 10.466 4.176 9.032Zm-.068 1.873.22-.748 3.496 1.311a.5.5 0 0 0 .352 0l3.496-1.311.22.748L8 12.46l-3.892-1.556Z"
                            ></path>
                        </svg>
                    </div>
                    <div>
                        <h3 class="fs-2 text-body-emphasis">Many multiform Post.</h3>
                        <p>Range of post and lessons that span multiple disciplines.</p>
                    </div>
                </div>
                <div class="col d-flex align-items-start">
                    <div
                        class="icon-square text-body-emphasis bg-body-secondary d-inline-flex align-items-center justify-content-center fs-4 flex-shrink-0 me-3"
                        >
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            height="30"
                            fill="currentColor"
                            class="bi bi-arrow-through-heart"
                            viewBox="0 0 16 16"
                            >
                        <path
                            fill-rule="evenodd"
                            d="M2.854 15.854A.5.5 0 0 1 2 15.5V14H.5a.5.5 0 0 1-.354-.854l1.5-1.5A.5.5 0 0 1 2 11.5h1.793l.53-.53c-.771-.802-1.328-1.58-1.704-2.32-.798-1.575-.775-2.996-.213-4.092C3.426 2.565 6.18 1.809 8 3.233c1.25-.98 2.944-.928 4.212-.152L13.292 2 12.147.854A.5.5 0 0 1 12.5 0h3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-.854.354L14 2.707l-1.006 1.006c.236.248.44.531.6.845.562 1.096.585 2.517-.213 4.092-.793 1.563-2.395 3.288-5.105 5.08L8 13.912l-.276-.182a21.86 21.86 0 0 1-2.685-2.062l-.539.54V14a.5.5 0 0 1-.146.354l-1.5 1.5Zm2.893-4.894A20.419 20.419 0 0 0 8 12.71c2.456-1.666 3.827-3.207 4.489-4.512.679-1.34.607-2.42.215-3.185-.817-1.595-3.087-2.054-4.346-.761L8 4.62l-.358-.368c-1.259-1.293-3.53-.834-4.346.761-.392.766-.464 1.845.215 3.185.323.636.815 1.33 1.519 2.065l1.866-1.867a.5.5 0 1 1 .708.708L5.747 10.96Z"
                            ></path>
                        </svg>
                    </div>
                    <div>
                        <h3 class="fs-2 text-body-emphasis">Quality assurance.</h3>
                        <p>The lessons come from many people with high expertise and experience in many fields.</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="header">
                    <h1>Write Better Papers and Essays with GrammarGuardian</h1>
                    <p>GrammarGuardian’s free essay-checking tool will help you review your papers for grammatical mistakes, unclear
                        sentences, and misused words. Save time and be confident your work will make the grade!</p>
                </div>
            </div>
            <div class="row">
                <form action="${pageContext.request.contextPath}/grammar-checker" method="POST">
                    <input type="hidden" name="action" value="get-result"/>
                    <div class="col d-flex">
                        <div class="main-content">
                            <c:choose>
                                <c:when test="${segments != null}">
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
                                    <a class="save-button"  data-bs-toggle="modal" data-bs-target="#save-post" >Save your essay</a>       
                                    <a class="edit-button" href="${pageContext.request.contextPath}/auth">Continue to check</a>
                                </c:when>
                                <c:otherwise>
                                    <textarea required id="text" name="text" class="check-essay" placeholder="Start writing here."></textarea>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="sidebar">
                            <div class="suggestions">
                                <h2>Suggestions</h2>
                                <p>Let’s get started.</p>
                                <ul>
                                    <li>Step 1: Add your text, and we will hightlight any issues.</li>
                                    <li>Step 2: Check your text onemore time submit.</li>
                                    <li>Step 3: Click a button bellow.</li>
                                </ul>
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
                                            Note: This feature need to register to get more time to check your esssay. (3 times remain)
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
    </div>

    <!-- Category -->
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




    <!-- Top courses 2024 -->
    <div class="album py-3 bg-light">
        <h2 class="text-body-emphasis text-center py-3">Top Post in 2024</h2>
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <!-- Iterate over courses -->
                <c:forEach var="courses" items="${courses}">
                    <div class="col-md-3">
                        <div class="card shadow-sm">
                            <img src="${pageContext.request.contextPath}/images/c++.jpg" alt="">
                            <div class="card-body">
                                <p class="card-text"><strong>${courses.name}</strong></p>
                                <p>Jame Smith</p>
                                <p class="d-inline text-decoration-line-through">${courses.price}</p>
                                <p class="d-inline">${courses.getPriceDiscount()}$</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="course-detail?id=${courses.id}" type="button" class="btn btn-sm btn-outline-secondary">View</a>
                                        <button type="button" class="btn btn-sm btn-outline-secondary"><a href="cart.jsp" style="text-decoration: none; color: black">Add to cart</a> </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach> <!-- End of forEach -->
            </div>
        </div>
    </div>



    <!-- news -->
    <div class="album py-3 bg-light">
        <h2 class="text-body-emphasis text-center py-3">
            Top Contribuitor
        </h2>
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

                <c:forEach var="lectures" items="${lectures}">
                    <div class="col-md-2">
                        <div class="card shadow-sm">
                            <img src="${pageContext.request.contextPath}/images/instructor.jpg" alt="">
                            <div class="card-body">
                                <div class="btn-group d-flex flex-column-reverse justify-content-center align-items-center">
                                    <a href="viewinstuctor?id=${lectures.id}">
                                        <button type="button" class="btn btn-sm btn-outline-secondary">
                                            ${lectures.fullName}
                                        </button>
                                    </a>
                                    <p>Software engineer</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>




    <!-- footer -->

    <footer class="bg-dark text-white">
        <div class="container ">
            <!-- Grid row -->
            <div class="row mt-3 py-5">
                <!-- Grid column -->
                <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                    <!-- Content -->
                    <h6 class="text-uppercase fw-bold mb-4">
                        <i class="fas fa-gem me-3"></i>FLearn
                    </h6>
                    <p>
                        High quality education maintained by an open source community.
                    </p>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.5062169040193!2d105.52271427476879!3d21.012421688340503!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2sFPT%20University!5e0!3m2!1sen!2s!4v1715670647321!5m2!1sen!2s" width=300" height="150" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>

                </div>

                <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                    <!-- Links -->
                    <h6 class="text-uppercase fw-bold mb-4">
                        About us
                    </h6>
                    <p>
                        <a href="#!" class="text-reset">About</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Team</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Blog</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Success Stories</a>
                    </p>
                </div>
                <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        Support
                    </h6>
                    <p>
                        <a href="#!" class="text-reset">FAQ</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Contribute</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Contact us</a>
                    </p>
                </div>
                <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                    <!-- Links -->
                    <h6 class="text-uppercase fw-bold mb-4">Contact</h6>
                    <p><i class="fas fa-home me-3"></i>Hoa Lac Hi-tech Park, km 29, Đại lộ, Thăng Long, Hà Nội, Vietnam</p>
                    <p>
                        <i class="fas fa-envelope me-3"></i>
                        FLearn@gmail.com
                    </p>
                    <p><i class="fas fa-phone me-3"></i>+1900 9090</p>
                </div>
            </div>
        </div>
    </footer>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"
    ></script>
</body>
=======

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
>>>>>>> a40837fe3337e1e76bb532cef8193d3c4bd035c5
</html>
