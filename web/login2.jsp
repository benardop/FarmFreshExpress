<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/21/2016
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- start the middle column -->

<section>

  <h1>Login Form</h1>
  <p>Please enter a username and password to continue.</p>
  <form action="<c:url value='/user/login' />" method=post>
    <label>Email</label>
    <input type="text" name="username"><br>
    <label>Password</label>
    <input type="password" name="password"><br>
    <label>&nbsp;</label>
    <input type="submit" value="Login">
  </form>
  <br>
  <p>If you don't have a user account yet, and would like to register,
    <a href="/register_user.jsp">please click here</a></p>
  <br>
  <p><a href="/forgotten_password.jsp">Forgot Your Password</a></p>

</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />
