<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 7:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebarA">
  <nav>
    <ul>
      <li><a href="<c:url value='/' />">HOME</a></li>
      <li><a href="<c:url value='/adminController/displayInvoices/' />">Display Invoices</a></li>
      <li><a href="<c:url value='/admin/reports.jsp' />">Reports</a></li>
    </ul>
  </nav>
</aside>
