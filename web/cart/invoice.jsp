<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_all.jsp"/>
<%-- Use the following left column instead after you configure a
     secure connection.
<jsp:include page="/includes/column_left_all_absolute.jsp" />
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section class="cart">

    <h1>Your invoice</h1>

    <table>
        <tr>
            <th>Date/Time</th>
            <td>${invoice.invoiceDateDefaultFormat}</td>
            <td></td>
        </tr>
        <tr>
            <th class="top">Ship To</th>
            <td>${user.shipToHTMLFormat}</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="3">
                <hr>
            </td>
        </tr>
        <tr>
            <th>Quantity</th>
            <th>Name</th>
            <th>Price</th>
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
            <td>${invoice.invoiceTotalCurrencyFormat}</td>
        </tr>
    </table>


    <form action="<c:url value='/order/displayUser' />" method="post" id="float_left">
        <input type="submit" value="Edit Address">
    </form>

    <form action="<c:url value='/order/displayCreditCard' />" method="post">
        <input type="submit" value="Continue">
    </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp"/>