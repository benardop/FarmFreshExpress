<%--
  File:  product.jsp
  Purpose:  To display all Products of a given Type/Category that are available
            for Sale and enable the user to add a given Quantity of a Product
            to the Cart.  Also, enable user to click a Product's Image to
            view the Product Detail information.

            Window Actions:
            - Clicking the "Add to Cart" Button sends form data to
            /addItem in the OrderController
            - Clicking a Product's Image sends form data to
            /displayProduct in the CatalogController

  Author:  Amy Radtke
  Version  1.0  07/01/2017
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->
<section id="catalog">
    <h1>${productTypeName}</h1>

    <c:if test="${availableProducts == null || availableProducts.size() == 0}">
        <h3>There are no ${productTypeName} for sale at this time.</h3>
    </c:if>


    <c:if test="${availableProducts.size() > 0}">
        <table>
            <tr></tr>
            <c:forEach var="product" items="${availableProducts}">
                <tr>
                    <td>
                        <a href="<c:url value='/catalogController/displayProduct?productId=${product.productId}'/>">
                        <img src="<c:url value="${product.imageURL}"/>"
                             alt="Unable to display Image" width="70" height="70">
                    </a>
                    </td>
                    <td>${product.name} </td>
                    <td>${product.priceInCurrencyFormat} </td>
                    <td>
                        <form action="<c:url value='/order/addItem'/>" method="post">
                            <input type="hidden" name="productId"
                                   value="<c:out value='${product.productId}'/>">
                            <input type="number" name="productQuantity"
                                   value="<c:out value='1'/>" min=1 max=1000
                                   class="tc item-quantity" required>
                            <input type="submit" value="Add to Cart">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />
