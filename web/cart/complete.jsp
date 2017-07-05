<%--
  File: complete.jsp
  Purpose:  To display an Order Completion window to the user.
  Note:  custServEmail is defined in web.xml

  Author: Amy Radtke
  Version: 1.0    Dated: 07/01/2017
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- begin middle column -->

<section>

  <h1>Thank you, ${user.firstName}</h1>


  <p>Your order has been submitted. We'll begin processing your
    order right away. If you have any questions about your order,
    please feel free to contact us at
    <a href="mailto:${custServEmail}">${custServEmail}</a></p>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />