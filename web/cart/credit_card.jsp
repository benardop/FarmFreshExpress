<%--
  Created by IntelliJ IDEA.
  User: benard
  Date: 11/20/2016
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/includes/header.jsp" />
<%-- Use the following left column instead after you configure a
     secure connection.
<jsp:include page="/includes/column_left_all_absolute.jsp" />
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section class="cart">
  <p>THIS PAGE IS UNDER CONSTRUCTION - Thank you for your patience!</p>
  <p>In the future you will have the ability to create multiple credit cards - </p>
  <p>and select which to use when completing the order. </p>
  <br>
  <h1>Enter your credit card information</h1>

  <form action="<c:url value='/order/completeOrder' />" method="post">
    <table>
      <tr>
        <td><b>Credit card type</b></td>
        <td>
          <select name="creditCardType" size="1">
            <option value="Visa">Visa</option>
            <option value="Mastercard">Mastercard</option>
            <option value="AmEx">American Express</option>
          </select>
        </td>
      </tr>
      <tr>
        <td><b>Card number</b></td>
        <td><input type="text" size="20" name="creditCardNumber"
                   maxlength="25" required></td>
      </tr>
      <tr>
        <td><b>Expiration date (mm/yyyy)</b></td>
        <td><select name="creditCardExpMonth">
          <option value="01">01 - January</option>
          <option value="02">02 - February</option>
          <option value="03">03 - March</option>
          <option value="04">04 - April</option>
          <option value="05">05 - May</option>
          <option value="06">06 - June</option>
          <option value="07">07 - July</option>
          <option value="08">08 - August</option>
          <option value="09">09 - September</option>
          <option value="10">10 - October</option>
          <option value="11">11 - November</option>
          <option value="12">12 - December</option>
        </select>
          /
          <select name="creditCardExpYear">
            <c:forEach var="year" items="${creditCardYears}">
              <option value="${year}">${year}</option>
            </c:forEach>
          </select>
        </td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Submit Order"></td>
      </tr>
    </table>
  </form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />
