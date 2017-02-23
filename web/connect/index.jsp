<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
  <h1>JOIN US!</h1>

  <%--<form action="<c:url value='/user/eNewsLetterSignUp'/>" method="post">--%>
  <form method="post">
      <h3> Sign up for our monthly eNewsLetter to hear what's happening on the farm - <br/>
           what's being harvested, recipes from our kitchen, and special offers.</h3>
    <label>Email</label>
    <input type="email" name="email" required><br>
    <label>&nbsp;</label>
    <%--<input type="submit" value="Submit" id="submit">--%>
  <button type="submit" formaction="/user/subscribeToNewsletter">Subscribe</button>
  <button type="submit" formaction="/user/unsubscribeFromNewsletter">Unsubscribe</button>
  </form>
  </section>

