<!doctype html>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.bs.constants.CommonConstants" %>
<html lang="en-US">
    <head>
        <meta charset="utf-8">
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge">-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tune Store</title>
        <%-- importing CSS stylesheet --%>
        <link href="styles/main.css" rel="stylesheet" type="text/css">
        <link href="styles/cssStyles.css" rel="stylesheet" type="text/css">
        <link href="styles/singlePageTemplate.css" rel="stylesheet" type="text/css">
        <%-- code to import java script file. --%>
        <script type="text/javascript" src="javascript/mainJavaScript.js"></script> 
    </head>
    <body>
        <!-- Main Container -->

        <section class="container"> 
            <!-- Navigation -->
            <header> <a href="#" onclick="javascript:submitForms('linksForm', 'home','PageFlowController')">
                    <h4 class="logo">Tune Store</h4>
                </a>
                <nav id="menubar">
                    <ul>
                        <li><a href="#" onclick="javascript:submitForms('linksForm', 'home','PageFlowController')" >Home</a></li>
                        <li><a href="#" onclick="javascript:submitForms('linksForm', 'about','PageFlowController')">About</a></li>
                        <li> <a href="#" onclick="javascript:submitForms('linksForm', 'contact','PageFlowController')">Contact</a></li>
                            <c:choose>
                                <c:when test="${sessionScope.theAdmin == null && sessionScope.theUser == null}">    
                                <li> <a href="#" onclick="javascript:submitForms('linksForm', 'goToLogin','PageFlowController')">Login</a></li>
                                <li> <a href="#" onclick="javascript:submitForms('linksForm', 'goToSignUp','PageFlowController')">Signup</a></li>
                                </c:when>
                                <c:when test="${sessionScope.theUser != null}">
                                <li> <span class="hdrUser">Welcome<c:out value="${sessionScope.theUser.name}"/> </span></li>
                                <li> <img class="cartIconClass"src="images/cart.png" onclick="javascript:submitForms('linksForm', 'cart','Cart')"></li>
                                <li> <a href="#" onclick="javascript:submitForms('linksForm', 'logout','PageFlowController')">Logout</a></li>
                                </c:when>
                                <c:otherwise>
                                <li> <span class="hdrUser">Welcome,<c:out value="${sessionScope.theAdmin.name}"/></span></li>
                                <li> <a href="#" onclick="javascript:submitForms('linksForm', 'logout','PageFlowController')">Logout</a></li>
                                </c:otherwise>
                            </c:choose>

                    </ul>
                </nav>
            </header>
            <!-- Hero Section -->
            <!--            <section class="hero" id="hero">
            
                        </section>-->

            <form id="linksForm" method="POST">
                <input type="hidden" name="actionParam" id="actionParam" value="" />
            </form>

        </section>


