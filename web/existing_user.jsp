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

  <h1>Registration Error</h1>
  <h3>A User already exists with the email address ${email}</h3>
  <p>Please re-enter your email and password to Log In.</p>

  <jsp:include page="/includes/login_form.jsp" />

</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />