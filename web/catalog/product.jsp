<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />

<!-- start the middle column -->
<section>
  <%--<jsp:include page="/includes/product_table.jsp" />--%>
  <img src="${product.imageURL}"
       alt="Unable to display Image"
       width="400"
       height="400"
  />
  <p>${product.name}</p>
  <p>${product.priceCurrencyFormat}</p>
  <p>${product.description}</p>
</section>

<!-- end the middle column -->

<%--<jsp:include page="/includes/column_right_buttons.jsp" />--%>
<jsp:include page="/includes/footer.jsp" />