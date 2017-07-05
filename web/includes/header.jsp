<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
<head>
    <meta charset="utf-8">
    <title>Farm Fresh Express</title>
    <link rel="shortcut icon" href="<c:url value='/images/locally_grown.jpg'/>">
    <link rel="stylesheet" href="<c:url value='/styles/farmfresh.css'/> ">
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
</head>

<body>

<header>
    <img src="<c:url value='/images/locally_grown.jpg'/>"
         alt="Farm Fresh Express Logo" width="70" height="70">
    <h1>Welcome to Farm Fresh Express Beta V1.0 </h1>
    <h2>FARM FRESH GOODS served up FAST!</h2>
</header>
<nav id="nav_bar">
    <ul>
            <%--CATEGORIES--%>
            <c:if test="${pageContext.request.isUserInRole('super_user')}">
                <li><a href="<c:url value='/' />">
                CATEGORIES</a></li>
            </c:if>

            <%--eNEWSLETTER--%>
            <c:if test="${!pageContext.request.isUserInRole('admin')}">
                <li><a href="<c:url value='/eNewsletter' />">
                    eNEWSLETTER</a></li>
            </c:if>

            <%--HELP--%>
            <li><a href="<c:url value='/help' />">
                HELP</a></li>

        <%--LOG IN - if noone is logged in;  LOG OUT - if someone is logged in--%>
        <c:choose>
            <c:when test="${pageContext.request.remoteUser == null}">
                <li><a href="<c:url value='/user/login' />">
                    LOG IN</a></li>
                <li><a href="<c:url value='/register_user.jsp' />">
                    REGISTER</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="<c:url value='/user/logout' />">
                    LOG OUT</a></li>
            </c:otherwise>
        </c:choose>

        <%--ADMIN - if person logged in has a role of administrator or super_user--%>
        <c:if test="${pageContext.request.isUserInRole('super_user')}">
            <li><a href="<c:url value='/adminController/displayInvoices/' />">
                ADMIN</a></li>
        </c:if>

        <%--DELETE COOKIES - if person logged in has a role of super_user--%>
        <c:if test="${pageContext.request.isUserInRole('super_user')}">
            <li><a href="<c:url value='/user/deleteCookies' />">
                DELETE COOKIES</a></li>
        </c:if>

        <%--CART - displaying the number of items in the cart;  Zero if there is no cart--%>
            <c:if test="${!pageContext.request.isUserInRole('admin')}">
                <c:choose>
                    <c:when test="${cart == null}">
                        <li><a href="<c:url value='/cart/cart.jsp' />"> CART: 0 </a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value='/cart/cart.jsp'/>">CART: ${cart.cartQuantity} </a></li>
                    </c:otherwise>
                </c:choose>
            </c:if>
    </ul>
</nav>