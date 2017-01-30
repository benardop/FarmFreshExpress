<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 6:45 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">

  <h1>Invoices to be processed:</h1>

  <c:if test="${unprocessedInvoices == null}">
    <p>There are no invoices to process.</p>
  </c:if>


  <c:if test="${unprocessedInvoices != null}">
    <table>

      <tr>
        <td></td>
        <td><b>Customer Name</b></td>
        <td><b>Invoice Date</b></td>
      </tr>


      <c:forEach var="invoice" items="${unprocessedInvoices}">
        <tr>
          <td>
            <a href="displayInvoice?invoiceNumber=${invoice.invoiceNumber}">Click to View</a>
          </td>
          <td>${invoice.user.firstName} ${invoice.user.lastName}</td>
          <td>${invoice.invoiceDateDefaultFormat}</td>
        </tr>
      </c:forEach>

    </table>
  </c:if>

  <form action="<c:url value='/admin'/>" method="post">
    <input type=submit value="Go Back to Menu">
  </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />




<%--OLD CODE--%>
<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: benard--%>
  <%--Date: 11/20/2016--%>
  <%--Time: 6:35 AM--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<jsp:include page="/includes/header.jsp" />--%>
<%--<jsp:include page="/includes/column_left_admin.jsp" />--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<!-- begin middle column -->--%>

<%--<section id="admin">--%>

  <%--<h1>Admin Menu</h1>--%>

  <%--<!-- these Form tags don't force a secure connection -->--%>
  <%--<form action="/adminController/displayInvoices" method="post">--%>
    <%--<input type="submit" value="View Unprocessed Invoices">--%>
  <%--</form>--%>
  <%--<form action="<c:url value='/adminController/displayInvoices'/>" method="post">--%>
    <%--<input type="submit" value="Process Invoices" class="left_margin">--%>
  <%--</form>--%>
  <%--<form action="reports.jsp" method="post">--%>
    <%--<input type="submit" value="Display Reports" class="left_margin">--%>
  <%--</form>--%>

<%--</section>--%>

<%--<!-- end middle column -->--%>

<%--<jsp:include page="/includes/footer.jsp" />--%>