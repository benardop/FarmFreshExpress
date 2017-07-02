<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 6:44 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_admin.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">

    <h1>Your Invoice</h1>

    <table>
        <tr>
            <td><b>Date/Time</b></td>
            <td>${invoice.invoiceDateDefaultFormat}</td>
            <td></td>
        </tr>
        <tr>
            <th class="top">Ship To</th>
            <td>${invoice.user.shipToHTMLFormat}</td>
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
                <td>${item.product.priceCurrencyFormat}</td>
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
            <td><p><b>${invoice.invoiceTotalCurrencyFormat}</b></td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td><b>Payment Information</b></td>
            <td>Under Development - Not Currently Available</td>
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

    <%--<label>Payment information</label>--%>
    <%--<p>Under Development - Not currently available</p><br>--%>

    <%--TO BE ADDED ONCE THE CREDIT CARD FUNCTIONALITY HAS BEEN DEVELOPED ***************--%>
    <%--<span>${invoice.user.creditCardType}: ${invoice.user.creditCardNumber}           --%>
    <%--(${invoice.user.creditCardExpMonth}/${invoice.user.creditCardExpYear})</span><br>--%>
    <%--*********************************************************************************--%>

    <%--<label>Email Address</label>--%>
    <%--<span>${invoice.user.email}</span><br>--%>

    <form action="processInvoice" method="post">
        <input type="submit" value="Process Invoice">
    </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp"/>