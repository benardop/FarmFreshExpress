<%--
  Class:  index.jsp
  Purpose:  Screen used to subscribe or unsubscribe from the eNewsletter
  Specifics:  Email entered must be 254 characters or less.

            Window Actions:
            - Clicking the "Subscribe" Button sends form data to
            /subscribeToNewsletter in the UserController
            - Clicking the "UnSubscribe" Button sends form data to
            /unsubscribeFromNewsletter in the UserController

  Author:  Amy Radtke
  Version  1.0  07/01/2017
--%>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- start of middle panel -->
<section>
  <h1>JOIN US!</h1>

  <form method="post">
    <h3> Sign up for our monthly eNewsLetter to hear what's happening on the farm - <br/>
      what's being harvested, recipes from our kitchen, and special offers.</h3>
    <label>Email</label>
    <input type="email" name="email" maxlength="254" required><br>
    <label>&nbsp;</label>
    <button type="submit" formaction="<c:url value='/user/subscribeToNewsletter'/>">Subscribe</button>
    <button type="submit" formaction="<c:url value='/user/unsubscribeFromNewsletter'/>">Unsubscribe</button>
  </form>
</section>
<!-- end the middle panel -->

<jsp:include page="/includes/footer.jsp" />