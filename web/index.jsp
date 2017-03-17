<%--
  Created by IntelliJ IDEA.
  User: Mom and Dad
  Date: 11/2/2016
  Time: 8:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/includes/header.jsp" />
<c:choose>
    <c:when test="${pageContext.request.isUserInRole('admin')}">
        <jsp:include page="/includes/column_left_admin.jsp" />
        </c:when>
    <c:otherwise>
        <jsp:include page="/includes/column_left_all.jsp" />
    </c:otherwise>
</c:choose>

<!-- start the middle column -->
<section>
  <img src="<c:url value='/images/farm_fresh_logo.jpg'/>"
       alt="Farm Fresh Express Logo" width="780" height="385">
</section>
<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />