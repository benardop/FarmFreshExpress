<%--
  Class:  login_form.jsp
  Purpose:  To Display the Form Fields used for Login.  The enable the user
            to initiate Logging In, Resetting their Password or Registering
             as a New User.  Note:  this form is used by login.jsp and
             login_error.jsp.

            Window Actions:
            - Clicking the "Login" Button sends form data to
            /j_security_check which is part of the Servlet API.
            - Clicking the "Password Reset" Link sends form data
            to password_reset.jsp in the Web directory.
            - Clicking the "Register For a New Account" Link sends
            form data to register_user.jsp in the Web directory.


  Author:  Amy Radtke
  Version  1.0  07/01/2017
--%>
<form action="j_security_check" method="post">
    <label>Email</label>
    <input type="email" name="j_username" maxlength="254" required><br>
    <label>Password</label>
    <input type="password" name="j_password" required><br>
    <label>&nbsp;</label>
    <input type="submit" value="Login">
</form>
<br>
<br>
<p><a href="/password_reset.jsp">Reset Your Password</a></p>
<br>
<p><a href="/register_user.jsp">Register For A New Account</a></p>
