<%--
  File:  order.jsp
  Purpose:  To display the Order's Detail information prior to being placed.
  Future Enhancements:  The order.jsp window will pull information from the Invoice
  only - since the Invoice will be a picture of the Cart at the time of the Order
  e.g.  Price of product, User information at the time of the order.
  Author:  Amy Radtke
  Version  1.0  07/01/2017
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

    <h1>Your Order</h1>

    <table>
        <tr>
            <th class="top">Ship To</th>
            <td>${user.shipToAddressInHTMLFormat}</td>
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
            <td>${invoice.totalCostInCurrencyFormat}</td>
        </tr>
    </table>

    <br>
    <br>
    <form action="<c:url value='/checkOut/editUser' />" method="post" id="float_left">
        <input type="submit" value="Edit Address">
    </form>

    <form action="<c:url value='/checkOut/displayCreditCard' />" method="post">
        <input type="submit" value="Continue">
    </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp"/>