<%--
  File: header_absolute.jsp
  Purpose:  To display the Homepage's Header information - Using ABSOLUTE PATH logic.
            Header information includes:  Logo, Title and Horizontal Menu Bar.
            The items on the Menu are displayed/not displayed based on
            If the User has Logged In or Not and what the User's Role is.
            Menu Bar Items include:  Categories, eNewsletter, Help, Login,
            Logout, Register, Admin, Delete Cookies and Cart: <# items in cart>

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
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
<%--Prepare the Horizontal Menu Bar--%>
<nav id="nav_bar">
    <ul>

        <%--USER VIEW, ADMIN VIEW and DELETE COOKIES are displayed--%>
        <%-- Only for Super Users--%>
        <%--Admins are already in ADMIN VIEW and Users are already in USER VIEW--%>
        <%--But Super Users may be in Admin or User view--%>
        <%--and will need the ability to toggle between the two--%>
            <%--DELETE COOKIES is displayed for Super Users--%>
        <c:if test="${pageContext.request.isUserInRole('super_user')}">
            <li><a href="<c:url value='${absolutePath}' />">
                USER VIEW</a></li>
            <li><a href="<c:url value='${absolutePath}/adminController/displayInvoices/' />">
                ADMIN VIEW</a></li>
            <li><a href="<c:url value='${absolutePath}/user/deleteCookies' />">
                DELETE COOKIES</a></li>
        </c:if>

        <%--eNEWSLETTER is displayed for Users that are NOT Admins--%>
        <c:if test="${!pageContext.request.isUserInRole('admin')}">
            <li><a href="<c:url value='${absolutePath}/eNewsletter' />">
                eNEWSLETTER</a></li>
        </c:if>

        <%--HELP is always displayed--%>
        <li><a href="<c:url value='${absolutePath}/help' />">
            HELP</a></li>

        <%--LOG IN and REGISTER are displayed when noone is logged in--%>
        <%--LOG OUT is displayed when someone is logged in--%>
        <c:choose>
            <c:when test="${pageContext.request.remoteUser == null}">
                <li><a href="<c:url value='${absolutePath}/user/login' />">
                    LOG IN</a></li>
                <li><a href="<c:url value='${absolutePath}/register_user.jsp' />">
                    REGISTER</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="<c:url value='${absolutePath}/user/logout' />">
                    LOG OUT</a></li>
            </c:otherwise>
        </c:choose>

        <%--CART is always displayed--%>
        <%--It displays the Number of Items in the Cart or Zero if there is not Cart--%>
        <c:if test="${!pageContext.request.isUserInRole('admin')}">
            <c:choose>
                <c:when test="${cart == null}">
                    <li><a href="<c:url value='${absolutePath}/cart/cart.jsp' />"> CART: 0 </a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value='${absolutePath}/cart/cart.jsp'/>">CART: ${cart.cartQuantity} </a></li>
                </c:otherwise>
            </c:choose>
        </c:if>
    </ul>
</nav>