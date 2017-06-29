<%--
  Class:  index.jsp in FarmFreshExpress/web/eNewsletter directory
  Purpose:  Screen used to subscribe or unsubscribe from the Farm's eNewsletter
  Specifics:  If user is already signed up - they will be told they are.
              If user is not signed up yet - a row will be added in the user table
              with email and SubscribedToNewsletter flag set.
              Email entered must have an @ and be 254 characters or less.

  @version 1.0  Dated:  05/01/2017
  @author Amy Radtke    Initial Creation             05/01/2017
  @since ??? version of jdk

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
  <button type="submit" formaction="/user/subscribeToNewsletter">Subscribe</button>
  <button type="submit" formaction="/user/unsubscribeFromNewsletter">Unsubscribe</button>
  </form>
  </section>
<!-- end the middle panel -->

<jsp:include page="/includes/footer.jsp" />