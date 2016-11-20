<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
<head>
  <meta charset="utf-8">
  <title>Fresh Corn Records</title>
  <%--<link rel="shortcut icon" href="<c:url value='/images/favicon.ico'/>">
  <link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">--%>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
</head>

<body>

<header>
  <%--<img src="<c:url value='/images/logo.jpg'/>"--%>
       alt="Farm Fresh Express Logo" width="58">
  <h1>Farm Fresh Express</h1>
  <h2>Quality Farm Produce Served Up Fresh!</h2>
</header>
<nav id="nav_bar">
  <ul>
    <li><a href="<c:url value='/admin'/>">
      Admin</a></li>
    <%--<li><a href="<c:url value='/user/deleteCookies'/>">
      Delete Cookies</a></li>
    <li><a href="<c:url value='/order/showCart'/>">--%>
      Show Cart</a></li>
  </ul>
</nav>