package farmfresh.controllers;

import farmfresh.business.Invoice;
import farmfresh.data.InvoiceDB;
import farmfresh.data.ReportDB;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Purpose: To manage all activities available to the Farm Fresh Administrator.
 * These activities include displaying Admin reports, displaying all unprocessed Invoices,
 * displaying a single invoice (to be viewed and/or processed) and marking an Invoice
 * as "Processed" which means the products in the invoice have been shipped and
 * the User's Credit Card has been charged.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class AdminController extends HttpServlet {

    /**
     *  Handles any URL ending in /displayInvoice or /displayInvoices
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "/adminController/displayInvoices";

        if (requestURI.endsWith("/displayInvoice")){
            url = displayInvoice(request, response);
        }else if(requestURI.endsWith("/displayInvoices")){
            url = displayInvoices(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }//End - doGet()

    /**
     *  Handles any URL ending in /displayInvoices, /processInvoice or /displayReport
     */
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)throws IOException, ServletException{

        String requestURI = request.getRequestURI();
        String url= "/adminController/displayInvoices";

        if (requestURI.endsWith("/displayInvoices")){
            url = displayInvoices(request, response);
        }else if(requestURI.endsWith("/processInvoice")){
            url = processInvoice(request, response);
        }else if(requestURI.endsWith("/displayReport")){
            displayReports(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }//End - DoPost()

    /**
     * <br>
     * Initiates the display of a given Invoice in the Admin Invoice window.
     * <br><br>
     * Retrieves an Invoice based on the Invoice Number pulled from the incoming request.
     * Invoice is retrieved from the List of unprocessed Invoices stored on the Session object.
     * The Invoice retrieved is then stored on the Session object and
     * the URL for the Admin Invoice window is returned - initiating display of that window.
     * <br><br>
     * Request object Parameters:<br>
     * "invoiceNumber" - the Invoice Number that uniquely identifies an Invoice<br>
     * <br>
     * Session object Attributes:<br>
     * "invoice" - an Invoice Object<br>
     * "unprocessedInvoices" - Invoices which have not been processed - have not been
     * shipped or paid for yet
     *
     * @return  URL to /admin/order.jsp which displays the Admin Invoice window
     */
    private String displayInvoice(HttpServletRequest request, HttpServletResponse response){

        String invoiceNumberString = request.getParameter("invoiceNumber");
        int invoiceNumber = Integer.parseInt(invoiceNumberString);

        HttpSession session = request.getSession();
        List<Invoice> unprocessedInvoices = (List<Invoice>)session.getAttribute("unprocessedInvoices");

        Invoice invoice = null;
        for (Invoice unprocessedInvoice : unprocessedInvoices) {
            invoice = unprocessedInvoice;
            if (invoice.getInvoiceNumber() == invoiceNumber){
                break;
            }
        }

        session.setAttribute("invoice", invoice);
        return "/admin/invoice.jsp";

    }//End - displayInvoice()

    /**
     * <br>
     * Initiates the display of all Unprocessed Invoices in the Admin Invoices window.
     * <br><br>
     * Retrieves all Unprocessed Invoices from the Invoice Database.
     * The List of Invoices is then stored on the Session object and
     * the URL for the Admin Invoices window is returned - initiating display of that window.
     * <br><br>
     * Session object Attributes:<br>
     * "unprocessedInvoices" - Invoices which have not been processed - have not been
     * shipped or paid for yet
     *
     * @return  URL to /admin/order.jsp which displays the Admin Invoice window
     */
    private String displayInvoices(HttpServletRequest request, HttpServletResponse response){

        List<Invoice> unprocessedInvoices = InvoiceDB.selectUnprocessedInvoices();

        if (unprocessedInvoices != null && unprocessedInvoices.size() <= 0) {
            unprocessedInvoices = null;
        }

        HttpSession session = request.getSession();
        session.setAttribute("unprocessedInvoices", unprocessedInvoices);

        return "/admin/invoices.jsp";

    }//End - displayInvoices()

    /**
     * <br>
     * Marks a given Invoice as Processed and initiates the refresh and display of the
     * new list of Unprocessed Invoices in the Admin Invoices window.
     * <br><br>
     * Retrieves the given Invoice from the Session object.
     * Marks the Invoice as Processed in the Invoice database.
     * Initiates the refresh of the Admin Invoices window by returning
     * The URL for the AdminController's Display Invoices function is returned -
     * initiating the refresh of the Admin Invoices window.
     * <br><br>
     * Session object Attributes:<br>
     * "invoice" - an Invoice Object<br>
     *
     * @return  URL to /adminController/displayInvoices which displays the Admin Invoices window
     */
    private String processInvoice(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession();
        Invoice invoice = (Invoice)session.getAttribute("invoice");

        InvoiceDB.markAsProcessed(invoice);

        return "/adminController/displayInvoices";

    }//End - processInvoice()

    /**
     * <br>
     * Creates the following Admin Reports:<br>
     * User Detail Report - Lists detail information for all Users registered
     * in the System
     * OTHER REPORTS TO BE DEVELOPED LATER
     * <br><br>
     * Request object Parameters:<br>
     * "reportName" - Name of report to be run<br>
     * "startDate"  - Date used as the Starting point of the Report<br>
     * "endDate"    - Date used as the End point of the Report<br>
     *
     * @throws IOException
     */
    private void displayReports(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException{

        String reportName = request.getParameter("reportName");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        Workbook workbook;
        if (reportName.equalsIgnoreCase("userEmail")){
            workbook = ReportDB.getUserDetailReport();
        }else {
            // workbook = ReportDB.get???(startDate, endDate);//TODO at a later time
            workbook = new HSSFWorkbook();
        }
        response.setHeader("content-disposition",
                "attachment; filename=" + reportName + ".xls");

//                "attachment; filename=" + reportName + ".xsl:"); //TODO Ask benard which is correct
//                "attachment; filename=" + reportName + ".xsl");  //TODO

        try(OutputStream out = response.getOutputStream() ){
            workbook.write(out);
        }

    }//End - displayReports()

}// End - AdminController.java
