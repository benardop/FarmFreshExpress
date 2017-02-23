<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/21/2016
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<section>

  <h1>Login Form - Error</h1>
  <p>You did not log in successfully.</p>
  <p>Please check your username and password and try again.</p>

  <form action="j_security_check" method="get">
    <label>Username</label>
    <input type="text" name="j_username"><br>
    <label>Password</label>
    <input type="password" name="j_password"><br>
    <label>&nbsp;</label>
    <input type="submit" value="Login">
  </form>

</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />
