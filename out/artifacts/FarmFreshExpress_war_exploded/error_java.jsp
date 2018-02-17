<%--
  File: error_java.jsp
  Purpose:  To display a System Error Message.

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<%@ page isErrorPage="true" %>

<section>

  <h1>System Error</h1>
  <p>A System Error has occurred.</p>
  <p>To continue, click the Back button or select a link from this page.</p>

  <h2>Details</h2>
  <p>Requested URI: ${pageContext.errorData.requestURI}</p>

  <p>Cause of Error:</p>
  <p>${pageContext.errorData.throwable.cause}</p>

</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />