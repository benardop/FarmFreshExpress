<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
<%-- Use the following left column instead after you configure a
     secure connection.
<jsp:include page="/includes/column_left_all_absolute.jsp" />
--%>
<script type="text/javascript">

  function checkForm(form)
  {
//    if(form.username.value == "") {
//      alert("Error: Username cannot be blank!");
//      form.username.focus();
//      return false;
//    }
//    re = /^\w+$/;
//    if(!re.test(form.username.value)) {
//      alert("Error: Username must contain only letters, numbers and underscores!");
//      form.username.focus();
//      return false;
//    }

    if (form.password.value != ""){

        if (form.password.value != form.verificationPassword.value) {
          alert("Error: Passwords do not match - Please re-enter!");
          form.password.focus();
          return false;
        }
//      if(form.pwd1.value.length < 6) {
//        alert("Error: Password must contain at least six characters!");
//        form.pwd1.focus();
//        return false;
//      }
//      if(form.pwd1.value == form.username.value) {
//        alert("Error: Password must be different from Username!");
//        form.pwd1.focus();
//        return false;
//      }
//      re = /[0-9]/;
//      if(!re.test(form.pwd1.value)) {
//        alert("Error: password must contain at least one number (0-9)!");
//        form.pwd1.focus();
//        return false;
//      }
//      re = /[a-z]/;
//      if(!re.test(form.pwd1.value)) {
//        alert("Error: password must contain at least one lowercase letter (a-z)!");
//        form.pwd1.focus();
//        return false;
//      }
//      re = /[A-Z]/;
//      if(!re.test(form.pwd1.value)) {
//        alert("Error: password must contain at least one uppercase letter (A-Z)!");
//        form.pwd1.focus();
//        return false;
//      }
    } else {
      alert("Error: Please check that you've entered and confirmed your password!");
      form.password.focus();
      return false;
    }

    // All fields populated correctly
    return true;
  }

</script>

<!-- begin middle column -->

<section class="cart">

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <h1>Registration:</h1>
  <%--<form ... onsubmit="return checkForm(this);">--%>
  <form action="<c:url value='/user/register' />" onsubmit="return checkForm(this); method=post">
    <p id="required">Required <span class="required">*</span></p>

    <label>First Name</label>
    <input type="text" name="firstName"  maxlength=20
           value="${user.firstName}" required>
    <p class="required">*</p><br>

    <label>Last Name</label>
    <input type="text" name="lastName" value="${user.lastName}" required>
    <p class="required">*</p><br>

    <label>Email Address</label>
    <input type="email" name="email" value="${user.email}" required>
    <p class="required">*</p><br>

    <label>Password</label>
    <input type="password" name="password">
    <p class="required">*</p><br>

    <label>Re-enter Password</label>
    <input type="password" name="verificationPassword">
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

    <label>Country</label>
    <input type="text" name="country" value="${user.country}" required>
    <p class="required">*</p><br>

    <label>&nbsp;</label>
    <input type="submit" value="Continue">
  </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />
