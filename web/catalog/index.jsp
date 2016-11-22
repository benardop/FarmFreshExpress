<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_catalog.jsp" />

<!-- start the middle column -->

<!-- If necessary, this page could be generated from the database. -->

<section>
  <h1>The Fresh FArm Express Catalog</h1>

  <h2>Fresh Organic Apples</h2>
  <p><a href="product/apples">Apple Varieties</a></p>

  <h2 class="top_margin">Bananas</h2>
  <p><a href="product/bananas">Banana Varieties</a></p>


</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" flush="true" />
<jsp:include page="/includes/footer.jsp" />