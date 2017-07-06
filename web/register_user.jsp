<%--
  File:  register_user.jsp
  Purpose:  To display the User Registration window.

            Window Actions:
            - Clicking the "Continue" Button sends form data to
            /register in the UserController

  Author:  Amy Radtke
  Version  1.0  07/01/2017
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- begin middle column -->

<section class="cart">

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <h1>Registration:</h1>
  <h4><i>${message}</i></h4>

    <form action="<c:url value='/user/register' />"  method=post">
    <p id="required">Required <span class="required">*</span></p>

    <label>First Name</label>
    <input type="text" name="firstName" value="${user.firstName}" required>
    <p class="required">*</p><br>

    <label>Last Name</label>
    <input type="text" name="lastName" value="${user.lastName}" required>
    <p class="required">*</p><br>

    <label>Email Address</label>
    <input type="email" name="email" maxlength="254" value="${user.email}" required>
    <p class="required">*</p><br>

    <label>Password</label>
    <input type="password" name="password1" required>
    <p class="required">*</p><br>

    <label>Re-Enter Password</label>
    <input type="password" name="password2" required>
    <p class="required">*</p><br>

    <label>Company</label>
    <input type="text" name="companyName" value="${user.companyName}">
    <p class="required">&nbsp;</p><br>

    <label>Address1</label>
    <input type="text" name="address1" value="${user.address1}" required>
    <p class="required">*</p><br>

    <label>Address2</label>
    <input type="text" name="address2" value="${user.address2}">
    <p class="required">&nbsp;</p><br>

    <label>City</label>
    <input type="text" name="city" value="${user.city}" required>
    <p class="required">*</p><br>

    <label>State</label>
    <input type="text" name="state" value="${user.state}" required>
    <p class="required">*</p><br>

    <label>Zip Code</label>
    <input type="text" name="zip" value="${user.zip}" required>
    <p class="required">*</p><br>

    <label>&nbsp;</label>
    <input type="submit" value="Continue">
  </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />