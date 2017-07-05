<%--
  File: login_error.jsp
  Purpose:  To display the Login Error Window.  Displayed when
            the there is an Error with the User Login Information.

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
--%>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_all.jsp"/>

<!-- start the middle column -->

<section>

    <h1>Login Error </h1>
    <h4>Please check your email and password and try again.</h4>

    <jsp:include page="/includes/login_form.jsp"/>

</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp"/>
