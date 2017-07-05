<%--
  File: index.jsp
  Purpose:  To display the Home Page - which includes a Header, Footer,
            Picture in the Middle Panel and the Vertical Menu.
            Vertical Menu has two views:  Admin and User
            The Admin view is only displayed if an Admin is logged in
            The User view is displayed in all other scenarios.

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
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