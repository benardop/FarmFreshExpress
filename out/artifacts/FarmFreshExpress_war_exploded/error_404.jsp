<%--
  File: error_404.jsp
  Purpose:  To display a 404 Error Message.

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<%@ page isErrorPage="true" %>

<section>

  <h1>404 Error</h1>
  <p>The server was not able to find the file you requested.</p>
  <p>To continue, click the Back button or select a link from this page.</p>

  <h2>Details</h2>
  <p>Requested URI: ${pageContext.errorData.requestURI}</p>

  <p>Cause of Error:</p>
  <p>${pageContext.errorData.throwable.cause}</p>


</section>
<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />
