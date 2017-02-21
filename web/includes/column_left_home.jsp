<%@ page import="farmfresh.data.ProductTypeDB" %>
<%@ page import="farmfresh.business.ProductType" %>
<%@ page import="java.util.List" %>


<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 7:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<aside id="sidebarA">--%>
  <%--<nav>--%>
    <%--<ul>--%>
        <%--<li><a class="current" href="<c:url value='/' />">--%>
        <%--Home</a></li>--%>
        <%--<li><a href="<c:url value='/catalog/displayProducts/Fruits' />">--%>
        <%--Fresh Fruit</a></li>--%>
        <%--<li><a href="displayProduct?productID=2">Click to View Oranges</a></li>--%>
        <%--<li><a href="<c:url value='/catalog/displayProductTypes' />">--%>
            <%--Catalog</a></li>--%>
        <%--<li><a href="<c:url value='/catalogController/displayProducts?ProductTypeID=1' />">--%>
            <%--Fresh Fruit</a></li>--%>
        <%--<li><a href="<c:url value='/catalogController/displayProduct?ProductID=1' />">--%>
            <%--Bananas</a></li>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebarA">

    <h1>Categories:</h1>
    <% List<ProductType> productTypes = ProductTypeDB.selectProductTypes();
        System.out.println(productTypes);
        pageContext.setAttribute("productTypes", productTypes);
    %>

    <%--<c:if test="${productTypes == null}">--%>
    <c:if test="${productTypes == null}">
        <p>ERROR: No Categories to display.</p>
    </c:if>

    <c:if test="${productTypes != null}">
        <nav>
            <ul>
                <c:forEach var="productType" items="${productTypes}">
                    <li>
                        <a href="<c:url value='/catalogController/displayProducts?productTypeId=${productType.productTypeId}&productTypeName=${productType.productTypeName}'/>">
                                ${productType.productTypeName} </a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>
</aside>

