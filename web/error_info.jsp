<%--
  File: error_info.jsp
  Purpose:  To display an Error Message when information expected by a method
            does not exist.

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<%@ page isErrorPage="true" %>

<section>

  <h1>Data Error</h1>
  <p>Information expected by the System to Perform your Request is not Available.</p>
  <%--Enhance this to tell them what information is missing...--%>

</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />