<%--
  File: invoices.jsp
  Purpose:  To display all unprocessed Invoices as part of Admin Functionality
            The user can click the "Click To View" Link beside any Invoice
            to View the Invoice in the Invoice Detail window.

            Window Actions:
            - Click of the "Click to View" Link sends form data to
            /displayInvoice in the AdminController.

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
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
        <td><b>Invoice #</b></td>
        <td><b>Customer Name</b></td>
        <td><b>Total</b></td>
        <td><b>Invoice Date</b></td>
      </tr>


      <c:forEach var="invoice" items="${unprocessedInvoices}">
        <tr>
          <td>
            <a href="/adminController/displayInvoice?invoiceNumber=${invoice.invoiceNumber}">Click to View</a>
          </td>
          <td class="center">${invoice.invoiceNumber}</td>
          <td>${invoice.user.firstName} ${invoice.user.lastName}</td>
          <td class="right">${invoice.totalCostInCurrencyFormat}</td>
          <td>${invoice.invoiceDateInSimpleDateFormat}</td>
        </tr>
      </c:forEach>

    </table>
  </c:if>


</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />