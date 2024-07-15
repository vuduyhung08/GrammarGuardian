<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register Package</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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

            body{
                background:#DCDCDC;
            }
            .pricing-content{
                position:relative;
            }
            .pricing_design{
                position: relative;
                margin: 0px 15px;
            }
            .pricing_design .single-pricing{
                background:#554c86;
                padding: 60px 40px;
                border-radius:30px;
                box-shadow: 0 10px 40px -10px rgba(0,64,128,.2);
                position: relative;
                z-index: 1;
            }
            .pricing_design .single-pricing:before{
                content: "";
                background-color: #fff;
                width: 100%;
                height: 100%;
                border-radius: 18px 18px 190px 18px;
                border: 1px solid #eee;
                position: absolute;
                bottom: 0;
                right: 0;
                z-index: -1;
            }
            .price-head{
            }
            .price-head h2 {
                margin-bottom: 20px;
                font-size: 26px;
                font-weight: 600;
            }
            .price-head h1 {
                font-weight: 600;
                margin-top: 30px;
                margin-bottom: 5px;
            }
            .price-head span{
            }

            .single-pricing ul{
                list-style:none;
                margin-top: 30px;
            }
            .single-pricing ul li {
                line-height: 36px;
            }
            .single-pricing ul li i {
                background: #554c86;
                color: #fff;
                width: 20px;
                height: 20px;
                border-radius: 30px;
                font-size: 11px;
                text-align: center;
                line-height: 20px;
                margin-right: 6px;
            }
            .pricing-price{
            }

            .price_btn {
                background: #554c86;
                padding: 10px 30px;
                color: #fff;
                display: inline-block;
                margin-top: 20px;
                border-radius: 2px;
                -webkit-transition: 0.3s;
                transition: 0.3s;
            }
            .price_btn:hover{
                background:#0aa1d6;
            }
            a{
                text-decoration:none;
            }

            .section-title {
                margin-bottom: 60px;
            }
            .text-center {
                text-align: center!important;
            }

            .section-title h2 {
                font-size: 45px;
                font-weight: 600;
                margin-top: 0;
                position: relative;
                text-transform: capitalize;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <section id="pricing" class="pricing-content section-padding">
            <div class="container">					
                <div class="section-title text-center">
                    <h2>Pricing Plans</h2>
                    <p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.</p>
                </div>		
                <div class="row text-center">									
                    <c:forEach items="${PACKAGE}" var="p">
                        <div class="col-lg-4 col-sm-6 col-xs-12 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.1s" data-wow-offset="0" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s; animation-name: fadeInUp;">
                            <div class="pricing_design">
                                <div class="single-pricing">
                                    <div class="price-head">		
                                        <h2>${p.title}</h2>
                                        <h1>Price: ${p.price}</h1>
                                        <span>/Monthly</span>
                                    </div>
                                    <ul>
                                        <li><b>Limit text</b>: ${p.limitText}</li>
                                        <li><b>Check time</b>:  ${p.checkTime}</li>
                                    </ul>
                                    <div class="pricing-price">

                                    </div>
                                    <a href="RegisterPackageController?packageId=${p.id}" class="price_btn">Order Now</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div><!--- END ROW -->
            </div><!--- END CONTAINER -->
        </section>
        <!--        <div class="container">
                    <h1>Register Package</h1>
                    <div class="row">
        <c:forEach items="${PACKAGE}" var="p">
            <div class="col-md-4">
                <section>
                    <div class="container py-5">
                        <div class="row justify-content-center">
                            <div class="card text-black">
                                <div class="card-body">
                                    <div class="text-center">
                                        <h4>${p.title}</h4>
                                        <p class="text-muted mb-4">${p.description}</p>
                                    </div>
                                    <div>
                                        <div class="d-flex justify-content-between">
                                            <span>Limit Text: ${p.limitText}</span>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <span>Check Time: ${p.checkTime}</span>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <span>Price: $${p.price}</span>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-between total font-weight-bold mt-4">
            <c:choose>
                <c:when test="${sessionScope.USER != null && !packageDAO.hasPackagePermission(sessionScope.USER.id, p.id)}">
                    <a href="RegisterPackageController?packageId=${p.id}" class="btn btn-primary">Register</a>
                </c:when>
                <c:otherwise>
                    <button class="btn btn-secondary" disabled>Registered</button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</div>
</div>
</section>
</div>
        </c:forEach>
    </div>
</div>-->
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
