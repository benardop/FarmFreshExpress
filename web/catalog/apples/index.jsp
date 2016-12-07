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
  <jsp:include page="/includes/product_table.jsp" />
  <p class="clear_both">
    The apple tree (Malus pumila, commonly and erroneously called Malus domestica) is a
    deciduous tree in the rose family best known for its sweet, pomaceous fruit, the apple.
    It is cultivated worldwide as a fruit tree, and is the most widely grown species in the
    genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii,
    is still found today. Apples have been grown for thousands of years in Asia and Europe, and
    were brought to North America by European colonists. Apples have religious and
    mythological significance in many cultures, including Norse, Greek and European Christian
    traditions.
  </p>
</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_buttons.jsp" />
<jsp:include page="/includes/footer.jsp" />