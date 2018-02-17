<%--
            [REPORT FUNCTIONALITY IS CURRENTLY IN DEVELOPMENT]
  File: reports.jsp
  Purpose:  To display a List of Reports Administrators are able to run.
            The user can click the "User Detail Report" Link to initiate
            creation of the User Detail Report.
            The user can click the "Processed Invoices Report" Link to
            initiate the display of the Report Parameters window which will
            be used to collect Report Start and End Dates.

            Window Actions:
            - Click of the "User Detail Report" Link sends form data to
              /displayReports in the AdminController
            - Click of the "Processed Invoices Report' Link sends form data
             to parameters.jsp in the Admin directory.


  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">

  <h1>Reports List</h1>

  <form action="<c:url value='/adminController/displayReport'/>" method="post">
    <input type="hidden" name="reportName" value="userDetail">
    <input type="hidden" name="reportTitle" value="The User Detail report">
    <input type="submit" value="User Detail Report" class="left_margin">
  </form>

  <form action="parameters.jsp" method="post">
    <input type="hidden" name="reportName" value="unprocessedInvoices">
    <input type="hidden" name="reportTitle" value="The Processed Invoices report">
    <input type="submit" value="Processed Invoices Report - Not Yet Available" class="left_margin">
  </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />
