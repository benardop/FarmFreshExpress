<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 7:19 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">

  <h1>Reports List</h1>

  <form action="<c:url value='/adminController/displayReport'/>" method="post">
    <input type="hidden" name="reportName" value="userEmail">
    <input type="hidden" name="reportTitle" value="The User Email report">
    <input type="submit" value="User Email Report" class="left_margin">
  </form>

  <%--DOWNLOAD DETAIL REPORT - TO BE DEVELOPED FOR V2 OF FARM FRESH EXPRESS--%>
  <form action="parameters.jsp" method="post">
    <input type="hidden" name="reportName" value="downloadDetail">
    <input type="hidden" name="reportTitle" value="The Downloads report">
    <input type="submit" value="Downloads Report - Not Yet Available" class="left_margin">
  </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />
