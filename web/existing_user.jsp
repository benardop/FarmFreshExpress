<%--
  File: existing_user.jsp
  Purpose:  To display a message indicating that the Email of the
            User attempting to register already exists in the System.
            NOT CURRENTLY USED

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<section>

  <h1>Registration Error</h1>
  <h3>A User already exists with that email address ${email}</h3>
  <p>Please re-enter your email and password.</p>

  <jsp:include page="/includes/login_form.jsp" />

</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />