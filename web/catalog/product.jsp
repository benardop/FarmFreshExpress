<%--
  File:  product.jsp
  Purpose:  To display the Product Detail window and enable the user to
            Add a given Quantity of that Product to the Cart.

            Window Actions:
            - Clicking the "Add to Cart" Button sends form data to
            /addItem in the OrderController

  Author:  Amy Radtke
  Version  1.0  07/01/2017
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- start the middle column -->
<section>
  <img src="<c:url value="${product.imageURL}"/>"
       alt="Unable to display Image" width="250" height="250">
  <h2>${product.name}</h2>
  <h3>${product.priceInCurrencyFormat}</h3>
  <h3>${product.description}</h3>

    <form action="<c:url value='/order/addItem'/>" method="post">
        <input type="hidden" name="productId"
               value="<c:out value='${product.productId}'/>">
      <input id="quantity" type="number" name="productQuantity" value="1"
             min=1 max="1000" class="tc item-quantity" required>
      <input type=submit value="Add to Cart">
    </form>
</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />