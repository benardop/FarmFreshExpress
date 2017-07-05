<%--
  File: cart.jsp
  Purpose:  To display the User's Cart containing any Products they may
            wish to purchase.  The user can click the "Proceed To Checkout" Button
            to move forward in Purchasing the items.  They also have
            the ability to change the Quantity of any Product in the cart.
            If they change the Quantity to Zero or click the "Remove" Button
            beside that Product - the Product will be removed from the Cart.

            Window Actions:
            - Clicking the "Proceed to Checkout" Button sends form data to
            /CheckUser in the CheckoutController
            - Moving out of the "Quantity" input field sends form data to
            /UpdateItem in the OrderController
            - Clicking the "Remove" Button sends form data to
            /RemoveItem in the OrderController

  Author: Amy Radtke
  Version: 1.0    Dated: 06/01/2017
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_all.jsp"/>

<!-- begin middle column -->
<section class="cart">

    <c:choose>
        <c:when test="${cart == null or cart.isEmpty()}">
            <h1>Your cart </h1>
            <h3>Your cart is empty.</h3>
        </c:when>
        <c:otherwise>
            <h1>Your cart </h1>
            <table>
                <tr>
                    <th></th>
                    <th>Item</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>&nbsp;</th>
                </tr>
                <c:forEach var="item" items="${cart.lineItems}">
                    <tr class="cart_row">
                        <td><img src="${item.product.imageURL}"
                                 alt="Unable to display Image"
                                 width="50"
                                 height="50"/>
                        </td>
                        <td>${item.product.name}</td>
                        <td>${item.product.priceInCurrencyFormat}</td>
                        <td>
                            <form action="<c:url value='/order/updateItem'/>" method="post">
                                <input type="hidden" name="productId"
                                       value="<c:out value='${item.product.productId}'/>">
                                <input type="number" name="updateQuantity"
                                       value="<c:out value='${item.quantity}'/>"
                                       min=0 max="10000" onblur="this.form.submit()"
                                       id="updateQuantity" class="tc item-quantity">
                            </form>
                        </td>
                        <td>
                            <form action="<c:url value='/order/removeItem'/>" method="post">
                                <input type="hidden" name="productId"
                                       value="<c:out value='${item.product.productId}'/>">
                                <input type="submit" value="Remove">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${cart != null and !cart.isEmpty()}">
                    <tr>
                        <td></td>
                        <th>Total (${cart.cartQuantity} items):</th>
                        <th>${cart.totalCostInCurrencyFormat}</th>
                    </tr>
                </c:if>
            </table>
        </c:otherwise>
    </c:choose>
    <br/>
    <br/>

    <c:if test="${cart != null and !cart.isEmpty()}">
        <form action="<c:url value='/checkOut/checkUser'/>" method="post">
            <input type="submit" value="Proceed To Checkout">
        </form>
    </c:if>
</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp"/>