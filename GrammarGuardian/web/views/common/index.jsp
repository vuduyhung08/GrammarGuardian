
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Home</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css" />
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
        <input type="hidden" id="success" name="MESSAGE" value="${MESSAGE}"/>     
        <input type="hidden" id="error" name="MESSAGE" value="${ERROR}"/>
        <!-- Slogan -->
        <div class="container all-content" id="hanging-icons">
            <div class="row">
                <div class="header">
                    <h3>Write Better Papers and Essays with Grammar Guardian</h3>
                    <p>Grammar Guardian’s free essay-checking tool will help you review your papers for grammatical mistakes, unclear
                        sentences, and misused words. Save time and be confident your work will make the grade!</p>
                </div>
            </div>
            <div class="row form-input">
                <form action="${pageContext.request.contextPath}/grammar-checker" method="POST">
                    <input type="hidden" name="action" value="get-result"/>
                    <div class="col-md-12 d-flex">
                        <div class="text-origin">
                            <div style="width: 100%; height: 100%; margin-right: 10px">
                                <textarea required id="text" name="text" class="check-essay" oninput="countWords()" placeholder="Start writing here." >${text != null ? text : ''}</textarea>
                            </div>
                            <div>
                                <div>
                                    <c:choose>
                                        <c:when test="${wordCount != null}">
                                            <span> Word count: </span><span id="wordCount">${wordCount}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span> Word count: </span><span id="wordCount">0</span>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="hidden" name="word-count" id="word-count-input">
                                </div>
                                <div>
                                    <label for="check-type">Select Check Type:</label>
                                    <select id="check-type" name="check-type" class="styled-select">
                                        <option value="all" ${checkType == 'all' ? 'selected' : ''}>Check All</option>
                                        <option value="spell" ${checkType == 'spell' ? 'selected' : ''}>Check Spelling</option>
                                        <option value="grammar" ${checkType == 'grammar' ? 'selected' : ''}>Check Grammar</option>
                                    </select>
                                </div>
                                <div>
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
                                    <input type="file" id="fileInput" accept=".txt,.docx">
                                    <c:if test="${segments != null}">
                                        <a class="edit-button" id="updateResult">Aply suggesstion</a>
                                    </c:if>

                                    <c:choose>
                                        <c:when test="${USER != null}">
                                            <p class="login">
                                                Note: This feature requires registration to get more time to check your essay. (<b>${USER.checkFreeTime} times</b> remaining)
                                            </p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="login">Already have an account?
                                                <a href="${pageContext.request.contextPath}/LoginController">Log in</a>
                                            </p>
                                        </c:otherwise>
                                    </c:choose>



                                </div>
                            </div>
                        </div>
                        <div class="main-content" style=" margin: 0;
                             width: 620px;
                             height: fit-content;
                             padding: 0;">
                            <c:if test="${segments != null}">
                                <div class="check-essay" id="textResult">
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
                                </div>
                                <h4 style="color: green">Errors and Suggestions:</h4>
                                <div class="over-content">
                                    <table class="styled-table">
                                        <thead>
                                            <tr>
                                                <th>Error Message</th>
                                                <th>Error Word</th>
                                                <th>Position</th>
                                                <th>Suggested Correction</th>
                                            </tr>
                                        </thead>
                                        <tbody class="over-content">
                                            <c:forEach var="match" items="${matches}">
                                                <tr>
                                                    <td>${match.message}</td>
                                                    <td  style="color: red">${text.substring(match.fromPos, match.toPos)}</td>
                                                    <td>${match.fromPos}-${match.toPos}</td>
                                                    <td style="color: green">
                                                        <c:forEach var="suggestion" items="${match.suggestedReplacements}">
                                                            <c:out value="${suggestion}" />&nbsp;
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <a class="save-button" data-bs-toggle="modal" data-bs-target="#save-post">Save your essay</a>
                                <a class="edit-button" href="${pageContext.request.contextPath}/HomeController">Continue to check</a>
                            </c:if>
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
                                <button type="submit" class="btn btn-primary">Save essay</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Category -->
            <div style="margin-top: 170px"  class="album  bg-light">
                <h2 class="text-body-emphasis text-center py-3">
                    You ready to learn new things ?
                </h2>
                <div class="ext-body-emphasis text-center py-3">
                    Current user using our website ${VIEWS}</div>

                <div class="container">
                    <form action="HomeSearchController" class="d-flex" style="margin-bottom: 15px;">
                        <!--<input type="hidden" name="action" />-->
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search" value="${search}">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        <c:forEach var="post" items="${LIST_POST}">
                            <div class="col-md-3">
                                <div class="card shadow-sm">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-center align-items-center" style="flex-direction: column">
                                            <div>
                                                <b>Title</b>: ${post.title}
                                            </div>
                                            <div>
                                                ${post.createAt}
                                            </div>
                                            <div class="btn-group">

                                                <a  href="PostDetailController?postId=${post.postId}">
                                                    <button type="button" class="btn btn-sm btn-outline-secondary">
                                                        Details
                                                    </button>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <nav aria-label="Page navigation example" style="display: flex; justify-content:center;margin-top: 15px;">
                        <ul class="pagination">
                            <c:choose>
                                <c:when test ="${selectedPage - 1 < 1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">«</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="HomeSearchController?search=${search}&index=${selectedPage-1}">«</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="i" begin="1" end="${endP}">
                                <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="HomeSearchController?search=${search}&index=${i}">${i}</a> <li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test ="${selectedPage >= endP}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">»</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="HomeSearchController?search=${search}&index=${selectedPage+1}">»</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

        <textarea style="display: none" id="originText" >${ESSAY_INPUT}</textarea>   
        <textarea style="display: none" id="sugestion" >${TEXT_SOLUTION}</textarea>

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.5.0/jszip.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/mammoth/1.8.0/mammoth.browser.min.js" integrity="sha512-wuWo/cLB9W5BsZeyTYLuiTwr+FDlvjQC7C6atr+To7Jk92XHWI7WsImJZiruw7C9bnc8Zg7N0ncQI2Q/B4PQYw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script>
            var error = document.getElementById('error');
            var message = document.getElementById('success');

            if (message.value) {
                Swal.fire({
                    title: message.value,
                    icon: "success",
                    showCancelButton: true,
                    confirmButtonText: "Confirm",
                })
            }
            if (error.value) {
                Swal.fire({
                    title: error.value,
                    icon: "info",
                    showCancelButton: true,
                    confirmButtonText: "Confirm",
                });
            }
            ;

            // update result 
            var suggestionText = document.getElementById('sugestion').value;
            var originText = document.getElementById('originText').value;
            var textInput = document.getElementById('textResult');
            var isUpdated = false;

            var updateBtn = document.getElementById('updateResult');
            if (updateBtn != null) {
                updateBtn.onclick = function () {
                    if (!isUpdated) {
                        textInput.textContent = suggestionText;
                        updateBtn.textContent = 'Restore';
                        isUpdated = true;
                    } else {
                        textInput.textContent = originText;
                        updateBtn.textContent = 'Apply suggestion';
                        isUpdated = false;
                    }
                }
            }

            function countWords() {
                var text = document.getElementById("text").value;
                var words = text.trim().split(/\s+/);
                var wordCount = words.filter(function (word) {
                    return word.length > 0;
                }).length;
                document.getElementById("wordCount").innerHTML = wordCount;
                document.getElementById("word-count-input").value = wordCount;
            }
            countWords();
                      

            document.getElementById('fileInput').addEventListener('change', function (event) {
                const file = event.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        document.getElementById('text').value = e.target.result;
                    };
                    if (file.type === "text/plain") {
                        reader.readAsText(file);
                    } else if (file.name.endsWith('.docx')) {
                        readDocxFile(file);
                    }
                }
                countWords();

            });

            function readDocxFile(file) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    var arrayBuffer = e.target.result;

                    mammoth.extractRawText({arrayBuffer: arrayBuffer})
                            .then(function (result) {
                                document.getElementById('text').value = result.value;
                            })
                            .catch(function (err) {
                                console.error('Error reading DOCX:', err);
                            });
                };
                reader.readAsArrayBuffer(file);
                countWords();

            }


        </script>
    </body>
</html>
