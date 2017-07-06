<%--
  Class:  column_left_all_absolute.jsp
  Purpose:  To populate the Vertical Menu Bar with all Available - Using ABSOLUTE PATH logic
            Categories/Product Types that the User can choose from
            such as:  Fruits, Veggies, etc.
            The Category Name is set up as a Link to initiate
            display of the Products of that Category.

            Window Actions:
            - Clicking a Category Link sends form data to
            /displayAvailableProducts in the CatalogController

  Author:  Amy Radtke
  Version  1.0  07/01/2017
--%>
<%@ page import="farmfresh.data.ProductTypeDB" %>
<%@ page import="farmfresh.business.ProductType" %>
<%@ page import="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside id="sidebarA">

  <h1>Categories:</h1>
  <% List<ProductType> productTypes = ProductTypeDB.selectProductTypes();
    pageContext.setAttribute("productTypes", productTypes);
  %>
  <c:choose>
    <c:when test="${productTypes == null || productTypes.size() == 0}">
      <p>ERROR: No Categories to display.</p>
    </c:when>
    <c:otherwise>
      <nav>
        <ul>
          <c:forEach var="productType" items="${productTypes}">
            <li>
              <a href="<c:url value='${absolutePath}/catalogController/displayAvailableProducts?
                                    productTypeId=${productType.productTypeId}&
                                    productTypeName=${productType.productTypeName}'/>">
                  ${productType.productTypeName} </a>
            </li>
          </c:forEach>
        </ul>
      </nav>
    </c:otherwise>
  </c:choose>
</aside>

<%--
  Class:  column_left_all_absolute.jsp
  Purpose:  NOT CURRENTLY USED


  Author:  Amy Radtke
  Version  1.0  07/01/2017
--%>
<aside id="sidebarA">
  <nav>
    <ul>
      <li><a href="${absolutePath}">
        Home</a></li>
      <li><a href="${absolutePath}/catalog">
        Browse Catalog</a></li>
      <li><a href="${absolutePath}/email">
        Join Email List</a></li>
      <li><a href="${absolutePath}/customer_service">
        Customer Service</a></li>
    </ul>
  </nav>
</aside>