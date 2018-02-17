<%-- THIS FUNCTIONALITY IS CURRENTLY IN DEVELOPMENT AND NOT YET AVAILABLE --%>
<%--
  File: cart.jsp
  Purpose:  To display the Credit Card window that enables the User to enter
            their Credit Card Information to complete their purchase and
            save their Order.

            Window Actions:
            - Clicking the "Submit Order" Button sends form data to
            /submitOrder in the CheckoutController

  Author: Amy Radtke
  Version: 1.0    Dated: 06/01/2017
--%>
<jsp:include page="/includes/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section class="cart">
  <p>THIS PAGE IS UNDER CONSTRUCTION - Thank you for your patience!</p>
  <p>Credit Card Processing Logic has not yet been added.</p>
  <p>At this time, your credit card will NOT be charged and your credit card information </p>
  <p>will NOT be saved.  Your invoice WILL be saved as if your payment was processed. </p>
  <p>Additionally, in the future you will have the ability to create multiple credit cards - </p>
  <p>and select which to use when completing the order. </p>

  <br>
  <h1>Enter your credit card information - to complete your purchase and save your order</h1>

  <form action="<c:url value='/checkOut/submitOrder' />" method="post">
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
