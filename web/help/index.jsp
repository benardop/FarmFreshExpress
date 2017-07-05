<%--
  Class:  index.jsp
  Purpose:  To display Customer Service Contact Information when the User needs Help

            Window Actions:
            - Clicking the "Customer Service Email" Link opens
            the User's Email Application with the "To" set to
            the Customer Service Email Address.

  Author:  Amy Radtke
  Version  1.0  07/01/2017
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/includes/header.jsp" />

  <c:choose>
    <c:when test="${pageContext.request.isUserInRole('admin')}">
      <jsp:include page="/includes/column_left_admin.jsp" />
    </c:when>
    <c:otherwise>
      <jsp:include page="/includes/column_left_all.jsp" />
    </c:otherwise>
  </c:choose>

<!-- start the middle column -->

<section>
  <h1>Customer Service</h1>
  <p>
    We want to back our quality products up with the best possible service.
    Whether you have a question about what fresh produce we offer,
    or need help with an order, just let us know. We'll do everything
    we can to make it easy and enjoyable for you to do business with us.
  </p>
  <p>
    If you have questions or comments, please contact us
    in whatever way is most convenient for you. We look forward to hearing from you!
  </p>
  <h2>Phone:</h2>
  <p>1-800-221-5528</p>
  <h2>FAX:</h2>
  <p>1-559-440-0963</p>
  <h2>Email:</h2>
  <p><a href="mailto:custserv@farmfresh.com">custserv@farmfresh.com</a></p>
</section>
<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />