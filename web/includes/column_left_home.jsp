<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 7:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebarA">
  <nav>
    <ul>
        <li><a class="current" href="<c:url value='/' />">
        Home</a></li>
        <%--<li><a href="<c:url value='/catalog/displayProducts/Fruits' />">--%>
        <%--Fresh Fruit</a></li>--%>
        <%--<li><a href="displayProduct?productID=2">Click to View Oranges</a></li>--%>
        <li><a href="<c:url value='/catalog/displayProductTypes' />">
            Catalog</a></li>
        <li><a href="<c:url value='/catalog/displayProducts?ProductTypeID=1' />">
            Fresh Fruit</a></li>
        <li><a href="<c:url value='/catalog/displayProduct?ProductID=2' />">
            Oranges</a></li>
        <%-- CODE COPIED FROM OTHER FILES TO USE AS AN EXAMPLE--%>
        <%--<h2><a href="catalog/product/bananas" class="no_underline">--%>
            <%--Delicious sweet organic bananas--%>
        <%--</a></h2>--%>

        <%--<h2>Fresh Organic Fruits</h2>--%>
            <%--<p><a href="product/BR001">Brocolli</a></p>--%>

            <%--<h2>Fresh Organic Fruits</h2>--%>
            <%--<p><a href="product/apples">apples</a></p>--%>

        <%--ORIGINAL CODE FROM THIS FILE--%>
        <%--<li><a class="current" href="<c:url value='/' />">--%>
        <%--column_left_home Home</a></li>--%>
      <%--<li><a href="<c:url value='/catalog' />">--%>
        <%--Browse Catalog</a></li>--%>
      <%--<li><a href="<c:url value='/email' />">--%>
        <%--Join Email List</a></li>--%>
      <%--<li><a href="<c:url value='/customer_service' />">--%>
        <%--Customer Service</a></li>--%>
    </ul>
  </nav>
</aside>
