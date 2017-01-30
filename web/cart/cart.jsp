<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />

<!-- begin middle column -->

<section class="cart">
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <h1>Your cart</h1>
  <c:choose>
    <c:when test="${cart.isEmpty()}">
      <p>Your cart is empty.</p>
    </c:when>
    <c:otherwise>
      <table>
        <tr>
          <th></th>
          <th>Item</th>
          <th>Price</th>
          <th>Qty</th>
          <th>Amount</th>
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
                <input type=text name="updateQuantity"
                       value="<c:out value='${item.quantity}'/>" id="updateQuantity">
                <input type="submit" value="Update">
              </form>
            </td>
            <%--<td>${item.totalCurrencyFormat}</td>--%>
            <td>
              <form action="<c:url value='/order/removeItem'/>" method="post">
                <input type="hidden" name="productId"
                       value="<c:out value='${item.product.productId}'/>">
                <input type="submit" value="Remove">
              </form>
            </td>
          </tr>
        </c:forEach>
        <%--<tr>--%>
          <%--<td colspan="2">--%>
            <%--<p><b>To change the quantity for an item</b>, enter the new quantity--%>
              <%--and click on the Update button.</p>--%>
            <%--<p><b>To remove an item</b>, click on the Remove button.</p>--%>
          <%--</td>--%>
          <%--<td colspan="3">&nbsp;</td>--%>
        <%--</tr>--%>
      </table>
    </c:otherwise>
  </c:choose>

  <%--<form action="<c:url value='/'/>" method="get" id="float_left">--%>
    <%--<input type="submit" value="Continue Shopping">--%>
  <%--</form>--%>

  <c:if test="${!cart.isEmpty()}">
    <!-- Connection is NOT SECURE.  For testing only. -->
    <form action="<c:url value='/order/checkUser'/>" method="post">
      <input type="submit" value="Checkout">
    </form>
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

<jsp:include page="/includes/footer.jsp" />