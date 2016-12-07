<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<img src="<c:url value='/images/BR001_cover.jpg'/>" alt="Farm Fresh Express Logo" width="58">--%>
<%--<h1>${product.imageURL}"</h1--%>
<img src="${product.imageURL}"
     alt="Unable to display Image"
     width="100"
     height="100"
/>
<%--<img src="images/apple_cover.jsp">--%>
<%--<img src="/FarmFreshExpress/images/apple_cover.jsp" width="175" height="175" alt="Product Image">--%>
<%--<h2>${product.productName}</h2>--%>
<p>${product.productType}</p>
<p>${product.priceCurrencyFormat}</p>