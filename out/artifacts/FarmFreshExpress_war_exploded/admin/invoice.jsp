<%--
  File: invoice.jsp
  Purpose:  To display a single Invoice as part of Admin Functionality
            The user can click the "Process" Button to process the Invoice
            when the purchase associated with the Invoice is shipped.

            Window Actions:
            -  Click of the "Process" Button sends form data to
            /processInvoice in the AdminController

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
--%>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_admin.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">

    <h1>Invoice#  ${invoice.invoiceNumber}</h1>

    <table>
        <tr>
            <td><b>Date/Time</b></td>
            <td>${invoice.invoiceDateInSimpleDateFormat}</td>
            <td></td>
        </tr>
        <tr>
            <th class="top">Ship To</th>
            <td>${invoice.user.shipToAddressInHTMLFormat}</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="3">
                <hr>
            </td>
        </tr>
        <tr>
            <td><b>Quantity</b></td>
            <td><b>Name</b></td>
            <td><b>Price</b></td>
        </tr>

        <c:forEach var="item" items="${invoice.lineItems}">
            <tr>
                <td>${item.quantity}</td>
                <td>${item.product.name}</td>
                <td>${item.product.priceInCurrencyFormat}</td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="3">
                <hr>
            </td>
        </tr>
        <tr>
            <td></td>
            <th>Total:</th>
            <td><p><b>${invoice.totalCostInCurrencyFormat}</b></td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td><b>Payment Information</b></td>
            <td>Under Development - Not Currently Available</td>
            <%--TO BE ADDED ONCE THE CREDIT CARD FUNCTIONALITY HAS BEEN DEVELOPED ***************--%>
            <%--<span>${invoice.user.creditCardType}: ${invoice.user.creditCardNumber}           --%>
            <%--(${invoice.user.creditCardExpMonth}/${invoice.user.creditCardExpYear})</span><br>--%>
            <%--*********************************************************************************--%>
            <td></td>
            <td></td>
        </tr>
        <tr></tr>
        <tr>
            <td><b>User Email</b></td>
            <td>${invoice.user.email}</td>
            <td></td>
            <td></td>
        </tr>

    </table>

    <form action="processInvoice" method="post">
        <input type="submit" value="Process Invoice">
    </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp"/>