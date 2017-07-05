<%--THIS JSP IS NOT CURRENTLY BEING USED...--%>

<%--&lt;%&ndash;--%>
        <%--Class:  index.jsp in FarmFreshExpress/web/eNewsletter directory--%>


<%--@version 1.0  Dated:  05/01/2017--%>
<%--@author Amy Radtke    Initial Creation             05/01/2017--%>
<%--@since ??? version of jdk--%>

<%--&ndash;%&gt;--%>
<%--<jsp:include page="/includes/header.jsp" />--%>
<%--<jsp:include page="/includes/column_left_admin.jsp" />--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<!-- begin middle column -->--%>

<%--<section id="admin">--%>

  <%--<h1> THIS CODE IS NEVER USED</h1>--%>

  <%--&lt;%&ndash;<c:if test="${unprocessedInvoices == null}">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<p>There are no invoices to process.</p>&ndash;%&gt;--%>
  <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>


  <%--&lt;%&ndash;<c:if test="${unprocessedInvoices != null}">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<table>&ndash;%&gt;--%>

      <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<td></td>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<td><b>Customer Name</b></td>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<td><b>Invoice Date</b></td>&ndash;%&gt;--%>
      <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>


      <%--&lt;%&ndash;<c:forEach var="invoice" items="${unprocessedInvoices}">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
          <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<a href="displayInvoice?invoiceNumber=${invoice.invoiceNumber}">Click to View</a>&ndash;%&gt;--%>
          <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
          <%--&lt;%&ndash;<td>${invoice.user.firstName} ${invoice.user.lastName}</td>&ndash;%&gt;--%>
          <%--&lt;%&ndash;<td>${invoice.invoiceDateDefaultFormat}</td>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
      <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>

    <%--&lt;%&ndash;</table>&ndash;%&gt;--%>
  <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>

  <%--&lt;%&ndash;<form action="<c:url value='/admin'/>" method="post">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<input type=submit value="Go Back to Menu">&ndash;%&gt;--%>
  <%--&lt;%&ndash;</form>&ndash;%&gt;--%>

<%--</section>--%>

<%--<!-- end middle column -->--%>

<%--<jsp:include page="/includes/footer.jsp" />--%>




<%--OLD CODE--%>
<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: benard--%>
  <%--Date: 11/20/2016--%>
  <%--Time: 6:35 AM--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<jsp:include page="/includes/header.jsp" />--%>
<%--<jsp:include page="/includes/column_left_admin.jsp" />--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<!-- begin middle column -->--%>

<%--<section id="admin">--%>

  <%--<h1>Admin Menu</h1>--%>

  <%--<!-- these Form tags don't force a secure connection -->--%>
  <%--<form action="/adminController/displayInvoices" method="post">--%>
    <%--<input type="submit" value="View Unprocessed Invoices">--%>
  <%--</form>--%>
  <%--<form action="<c:url value='/adminController/displayInvoices'/>" method="post">--%>
    <%--<input type="submit" value="Process Invoices" class="left_margin">--%>
  <%--</form>--%>
  <%--<form action="reports.jsp" method="post">--%>
    <%--<input type="submit" value="Display Reports" class="left_margin">--%>
  <%--</form>--%>

<%--</section>--%>

<%--<!-- end middle column -->--%>

<%--<jsp:include page="/includes/footer.jsp" />--%>