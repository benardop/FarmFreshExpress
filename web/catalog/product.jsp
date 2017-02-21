<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- start the middle column -->
<section>
  <img src="${product.imageURL}"
       alt="Unable to display Image"
       width="250"
       height="250"
  />
  <h2>${product.name}</h2>
  <h3>${product.priceCurrencyFormat}</h3>
  <h4>${product.description}</h4>

    <form action="<c:url value='/order/addItem'/>" method="post">
        <input type="hidden" name="productId"
               value="<c:out value='${product.productId}'/>">
      <input id="quantity" type="number" name="productQuantity" value="1" min=0 class="tc item-quantity">
      <input type=submit value="Add to Cart">
    </form>
</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />