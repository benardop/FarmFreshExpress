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
        <td><select name="creditCardExpirationMonth">
          <option value="01 - January">01</option>
          <option value="02 - February">02</option>
          <option value="03 - March">03</option>
          <option value="04 - April">04</option>
          <option value="05 - May">05</option>
          <option value="06 - June">06</option>
          <option value="07 - July">07</option>
          <option value="08 - August">08</option>
          <option value="09 - September">09</option>
          <option value="10 - October">10</option>
          <option value="11 - November">11</option>
          <option value="12 - December">12</option>
        </select>
          /
          <select name="creditCardExpirationYear">
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
