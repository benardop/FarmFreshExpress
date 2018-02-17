<%--
  Class:  column_left_admin.jsp
  Purpose:  To populate the Vertical Menu Bar with the Available Admin Options
            Such as:  Invoices and Reports
            NOTE:  Reports is disabled at this time as it in currently
            in Development.

  Author:  Amy Radtke
  Version  1.0  07/01/2017
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside id="sidebarA">
  <h1>Admin Functions:</h1>
  <nav>
    <ul>
      <li><a href="<c:url value='/adminController/displayInvoices/' />">Invoices</a></li>
      <%--<li><a href="<c:url value='/admin/reports.jsp' />">Reports</a></li>--%>
    </ul>
  </nav>
</aside>
