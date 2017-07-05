<%--
  File: login.jsp
  Purpose:  To display the Login Window

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<section>

  <h1>Login Form</h1>
  <p>Please enter your email and password to continue.</p>

  <jsp:include page="/includes/login_form.jsp" />

</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />
