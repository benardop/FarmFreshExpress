<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
  <h1>JOIN US!</h1>

  <p><i>${message}</i></p>

  <form action="<c:url value='/user/eNewsLetterSignUp'/>" method="post">
    <%--<h3> If you would only like to join our eNewsLetter - simply enter your email address.</h3>--%>
      <h3> Sign up for our monthly eNewsLetter to hear what's happening on the farm -
           what's being harvested, recipes from our kitchen to yours,
           and special offers.</h3>
      <label>First Name</label>
      <input type="text" name="firstName"><br>
      <label>Last Name</label>
      <input type="text" name="lastName"><br>
    <label>Email</label>
    <input type="email" name="email" required><br>

    <%--<p></p>--%>
    <%--<h3> To begin purchasing from Loudoun Farmers and Artisans, fill out the rest.</h3>--%>
    <%--<label>First Name</label>--%>
    <%--<input type="text" name="firstName"><br>--%>
    <%--<label>Last Name</label>--%>
    <%--<input type="text" name="lastName"><br>--%>
    <%--<label>Address 1</label>--%>
    <%--<input type="text" name="address1"><br>--%>
    <%--<label>Address 2</label>--%>
    <%--<input type="text" name="address2"><br>--%>
    <%--<label>City</label>--%>
    <%--<input type="text" name="city"><br>--%>
    <%--<label>State</label>--%>
    <%--<input type="text" name="state"><br>--%>
    <%--<label>Zip</label>--%>
    <%--<input type="text" name="zip"><br>--%>
    <%--<p></p>--%>
    <%--<h3>Credit Card </h3>--%>
    <%--<label>Type</label>--%>
    <%--<input type="text" name="creditCardType"><br>--%>
    <%--<label>Number</label>--%>
    <%--<input type="text" name="creditCardNumber"><br>--%>
    <%--<label>Exp Date</label>--%>
    <%--<input type="text" name="creditCardExpirationDate"><br>--%>
    <label>&nbsp;</label>
    <input type="submit" value="Submit" id="submit">
  </form>
  </section>

<%--<jsp:include page="/includes/column_right_news.jsp" />--%>
<jsp:include page="/includes/footer.jsp" />
