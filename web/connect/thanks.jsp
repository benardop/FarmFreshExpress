<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />

<section>
  <h1>Thanks for joining our email list</h1>
  <p>Here is the information that you entered:</p>

  <label class="no_pad_top">First Name</label>
  <span>${user.firstName}</span><br>
  <label class="no_pad_top">Last Name</label>
  <span>${user.lastName}</span><br>
  <label  class="no_pad_top">Email</label>
  <span>${user.email}</span><br>

</section>

<jsp:include page="/includes/footer.jsp" />