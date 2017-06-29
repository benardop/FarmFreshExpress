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
 * Created by Mom and Dad on 11/11/2016.
 */
public class AdminController extends HttpServlet {

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
    }

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
    }

    private String displayInvoice(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String invoiceNumberString = request.getParameter("invoiceNumber");
        int invoiceNumber = Integer.parseInt(invoiceNumberString);

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
    }

    private String displayInvoices(HttpServletRequest request, HttpServletResponse response){
        List<Invoice> unprocessedInvoices = InvoiceDB.selectUnprocessedInvoices();
        String url;

        if (unprocessedInvoices != null){
            if (unprocessedInvoices.size() <=0){
                unprocessedInvoices = null;
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("unprocessedInvoices", unprocessedInvoices);

        url = "/admin/invoices.jsp";
        return url;
    }

    private String processInvoice(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession();
        Invoice invoice = (Invoice)session.getAttribute("invoice");

        InvoiceDB.update(invoice);

        return "/adminController/displayInvoices";
    }

    /**
     * @param request:  request contains the following Parameters
     *               reportName - Name of report to be run
     *               startDate  -  date used as the Start point of the Report
     *               endDate    -  date used as the End point of the Report
     *
     * @param response:
     * @throws IOException
     */
    private void displayReports(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException{

        String reportName = request.getParameter("reportName");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        Workbook workbook;
        if (reportName.equalsIgnoreCase("userEmail")){
            workbook = ReportDB.getUserEmail();
//        }else if (reportName.equalsIgnoreCase("downloadDetail")){  //TODO at a later time
//            workbook = ReportDB.getDownloadDetail(startDate, endDate);
        }else{
            workbook = new HSSFWorkbook();
        }
        response.setHeader("content-disposition",
                "attachment; filename=" + reportName + ".xls");
//                "attachment; filename=" + reportName + ".xsl:");
//                "attachment; filename=" + reportName + ".xsl");
        try(OutputStream out = response.getOutputStream() ){
            workbook.write(out);
        }
    }
}
