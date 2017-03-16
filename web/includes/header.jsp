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
    <h1>Welcome to Farm Fresh Express!</h1>
    <h2>FARM FRESH GOODS served up FAST!</h2>
</header>
<nav id="nav_bar">
    <ul>
        <%--HOME--%>
        <%--<li><a href="<c:url value='/' />">--%>
            <%--HOME</a></li>--%>

            <c:if test="${!pageContext.request.isUserInRole('super_user') and
                          !pageContext.request.isUserInRole('admin')}">
                <%--eNewsletter--%>
                <li><a href="<c:url value='/eNewsletter' />">
                    eNEWSLETTER</a></li>
            </c:if>

            <%--HELP--%>
            <li><a href="<c:url value='/help' />">
                HELP</a></li>
        <%--SIGNIN - if noone is signed-in;  SIGNOUT - if someone is signed in--%>
        <c:choose>
            <c:when test="${pageContext.request.remoteUser == null}">
                <li><a href="<c:url value='/login2.jsp' />">
                    LOG IN</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="<c:url value='/user/logout' />">
                    LOG OUT</a></li>
            </c:otherwise>
        </c:choose>

        <%--ADMIN - if person logged in has a role of administrator or super_user--%>
        <c:if test="${pageContext.request.isUserInRole('admin') or
                       pageContext.request.isUserInRole('super_user')}">
            <li><a href="<c:url value='/adminController/displayInvoices/' />">
                ADMIN</a></li>
        </c:if>

        <%--DELETE COOKIES - if person logged in has a role of super_user--%>
        <c:if test="${pageContext.request.isUserInRole('super_user')}">
            <li><a href="<c:url value='/user/deleteCookies' />">
                DELETE COOKIES</a></li>
        </c:if>

        <%--CART - displaying the number of items in the cart;  Zero if there is no cart--%>
            <c:if test="${!pageContext.request.isUserInRole('super_user') and
                          !pageContext.request.isUserInRole('admin')}">
                <c:choose>
                    <c:when test="${cart == null}">
                        <li><a href="<c:url value='/cart/cart.jsp' />"> CART: 0 </a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value='/cart/cart.jsp'/>">CART: ${cart.cartQuantity} </a></li>
                        <%--<li class="disabled"><a href="<c:url value='/cart/cart.jsp'/>">CART: ${cart.cartQuantity} </a></li>--%>
                    </c:otherwise>
                </c:choose>
            </c:if>
    </ul>
</nav>