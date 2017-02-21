<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mom and Dad
  Date: 11/2/2016
  Time: 8:26 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />


<!-- start the middle column -->

<section>
  <img src="<c:url value='/images/farm_fresh_logo.jpg'/>"
       alt="Farm Fresh Express Logo" width="700" height="350">
  <%--<p>Thanks for visiting. Make yourself at home. Feel free to browse through--%>
    <%--our Fresh Produce catalog.Our catalog contains some great fresh organic fresh farm produce , and we--%>
    <%--hope you like it as much as we do.</p>--%>
</section>
<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />