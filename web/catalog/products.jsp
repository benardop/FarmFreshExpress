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

<!-- begin middle column -->
<section id="catalog">
    <h1>${productTypeName}</h1>

    <c:if test="${products.size() == 0}">
        <h3>There are no ${productTypeName} for sale.</h3>
    </c:if>


    <c:if test="${products.size() > 0}">
        <table>
            <tr></tr>
            <%--<tr>--%>
                <%--<td></td>                  &lt;%&ndash;Image&ndash;%&gt;--%>
                <%--<td><b>Name</b></td>        &lt;%&ndash;Name&ndash;%&gt;--%>
                <%--<td><b>Price</b></td>       &lt;%&ndash;Price&ndash;%&gt;--%>
                <%--<td></td>                   &lt;%&ndash;Add-to-Cart Button&ndash;%&gt;--%>
            <%--</tr>--%>

            <c:forEach var="product" items="${products}">
                <tr>
                    <td><a href="/catalogController/displayProduct?productId=${product.productId}">
                        <img src="${product.imageURL}"
                         alt="Unable to display Image"
                         width="50"
                         height="50"/>
                    </a></td>
                    <td>${product.name} </td>
                    <td>${product.priceCurrencyFormat} </td>
                    <td>
                        <form action="<c:url value='/order/addItem'/>" method="post">
                            <input type="hidden" name="productId"
                                   value="<c:out value='${product.productId}'/>">
                            <input type="hidden" name="productQuantity"
                                   value="<c:out value='1'/>">
                            <input type="submit" value="Add to Cart">
                        </form>
                        <%--<a href="='/order/addItem?ProductID=${product.productId} & ProductQuantity=1">Add to Cart</a>--%>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </c:if>
</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />
