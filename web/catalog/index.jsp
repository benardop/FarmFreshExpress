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

  <p>  Made it to Products.jsp</p>

  <%--<h1>${productType}</h1>--%>

  <%--<c:if test="${products == null}">--%>
  <%--<p>There are no ${productType} to process.</p>--%>
  <%--</c:if>--%>


  <%--<c:if test="${products != null}">--%>
  <%--<table>--%>
  <%--<tr>--%>
  <%--<td></td>--%>
  <%--<td><b>Name</b></td>--%>
  <%--<td><b>Price</b></td>--%>
  <%--</tr>--%>

  <%--<c:forEach var="product" items="${products}">--%>
  <%--<tr>--%>
  <%--<td>--%>
  <%--<a href="displayProduct?productCode=${product.productCode}">Click to View</a>--%>
  <%--</td>--%>
  <%--<td>${product.name} </td>--%>
  <%--<td>${product.price}</td>--%>
  <%--</tr>--%>
  <%--</c:forEach>--%>

  <%--</table>--%>
  <%--</c:if>--%>

  <form action="<c:url value='/admin'/>" method="post">
    <input type=submit value="Go Back to Menu">
  </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />