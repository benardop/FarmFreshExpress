<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 7:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="farmfresh.data.ProductTypeDB" %>
<%@ page import="farmfresh.business.ProductType" %>
<%@ page import="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside id="sidebarA">

  <h1>Categories:</h1>
  <% List<ProductType> productTypes = ProductTypeDB.selectProductTypes();
    System.out.println(productTypes);
    pageContext.setAttribute("productTypes", productTypes);
  %>
  <c:choose>
    <c:when test="${productTypes == null}">
      <p>ERROR: No Categories to display.</p>
    </c:when>
    <c:otherwise>
      <nav>
        <ul>
          <c:forEach var="productType" items="${productTypes}">
            <li>
              <a href="<c:url value='/catalogController/displayProductsInSeason?productTypeId=${productType.productTypeId}
                                    &productTypeName=${productType.productTypeName}'/>">
                  ${productType.productTypeName} </a>
            </li>
          </c:forEach>
        </ul>
      </nav>
    </c:otherwise>
  </c:choose>



</aside>