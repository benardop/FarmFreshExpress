<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_all.jsp"/>

<!-- begin middle column -->
<section class="cart">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                        <td>${item.product.priceCurrencyFormat}</td>
                        <td>
                            <form action="<c:url value='/order/updateItem'/>" method="post">
                                <input type="hidden" name="productId"
                                       value="<c:out value='${item.product.productId}'/>">
                                <input type="number" name="updateQuantity" value="<c:out value='${item.quantity}'/>"
                                       min=0 onblur="this.form.submit()" id="updateQuantity" class="tc item-quantity">
                                <%--<button>Update</button>--%>
                                <%--<input type="submit" value="Update">--%>
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
                    <th>${cart.cartTotalCurrencyFormat}</th>
                </tr>
                </c:if>
            </table>
        </c:otherwise>
    </c:choose>
    <br />
    <br />

    <!-- Connection is NOT SECURE. For testing only. -->
<c:if test="${cart != null and !cart.isEmpty()}">
    <form action="<c:url value='/checkOut/checkOut'/>" method="post">
    <input type="submit" value="Checkout">
    </form>

    <%--<c:choose>--%>
        <%--<c:when test="${pageContext.request.isUserInRole('user')}">--%>
    <%--<form action="<c:url value='/checkOut/checkOut'/>" method="post">--%>
        <%--<input type="submit" value="Checkout">--%>
    <%--</form>--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
            <%--<button type="button" disabled>Checkout</button>--%>
            <%--<br>--%>
            <%--<p>In order to check out, you must have a User account,--%>
                <%--please click here to <a href="/register_user.jsp">Register</a></p>--%>
        <%--</c:otherwise>--%>
    <%--</c:choose>--%>
    <!-- Connection is SECURE.  Before you can use it, you need to configure
    a secure connection on your system, comment
    out the previous form, and remove the comments from the following form. -->
    <!--
    <%--<form action="${absolutePathSecure}/order/checkUser" method="post">--%>
    <input type="submit" value="Checkout">
    </form>
    -->
</c:if>
</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp"/>