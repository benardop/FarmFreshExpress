<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 7:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<aside id="sidebarA">--%>
    <%--<h1>Categories:</h1>--%>
    <%--<c:if test="${productTypes == null}">--%>
        <%--<p>ERROR:  No Categories to display.</p>--%>
    <%--</c:if>--%>

    <%--<c:if test="${productTypes != null}">--%>
        <%--<nav>--%>
            <%--<ul>--%>
                <%--<c:forEach var="productType" items="${productTypes}">--%>
                    <%--<li>--%>
                        <%--<a href="<c:url value='/catalog/displayProducts?productTypeID=${productType.productTypeId}'/>">--%>
                        <%--${productType.productTypeName} </a>--%>
                    <%--</li>--%>
                <%--</c:forEach>--%>
            <%--</ul>--%>
        <%--</nav>--%>
    <%--</c:if>--%>
<%--</aside>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebarA">
    <nav>
        <ul>
            <li><a class="current" href="<c:url value='/' />">
                column_left_catalog Home</a></li>
        </ul>
    </nav>
</aside>



