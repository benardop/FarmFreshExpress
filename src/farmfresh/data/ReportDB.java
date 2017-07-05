package farmfresh.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * IN DEVELOPMENT - NOT YET AVAILABLE
 * Purpose: To  produce reports based on data Read from various FarmFreshExpress
 * Database Tables.<br>
 * Reports including:
 * User Detail Report - containing User Detail Information for all Users
 * eNewsletter Email List Report - containing Emails for all Users Subscribed
 * to get an eNewsletter
 * Processed Invoice Report - containing a list of all Invoices Processed
 * between the given dates
 * Outstanding Invoice Report - containing a list of all Invoices that have
 * NOT yet been processed.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class ReportDB {

    /**
     * Returns a List of all Users and their User information -
     * including:  name, email and address information.
     * @return Workbook containing the User Detail Report
     */
    public static Workbook getUserDetailReport(){

        // Create workbook, it's worksheet and a Title Row
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("User Email Report");  // Header
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("The User Email Report");  //Title

        // Create the header row
        row = sheet.createRow(2);
        row.createCell(0).setCellValue("Last Name");
        row.createCell(1).setCellValue("First Name");
        row.createCell(2).setCellValue("Email");
        row.createCell(3).setCellValue("Company Name");
        row.createCell(4).setCellValue("Address 1");
        row.createCell(5).setCellValue("Address 2");
        row.createCell(6).setCellValue("City");
        row.createCell(7).setCellValue("State");
        row.createCell(8).setCellValue("Zip");

        // Pull all Rows from the user Database
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user"
                     + " ORDER BY LastName";

        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            // Create a row in the sheet for each User Row Selected
            int i = 3;
            while (rs.next()){
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(rs.getString("LastName"));
                row.createCell(1).setCellValue(rs.getString("FirstName"));
                row.createCell(2).setCellValue(rs.getString("Email"));
                row.createCell(3).setCellValue(rs.getString("CompanyName"));
                row.createCell(4).setCellValue(rs.getString("Address1"));
                row.createCell(5).setCellValue(rs.getString("Address2"));
                row.createCell(6).setCellValue(rs.getString("City"));
                row.createCell(7).setCellValue(rs.getString("State"));
                row.createCell(8).setCellValue(rs.getString("Zip"));
                i++;
            }

            // Return the complete Workbook/Report
            return workbook;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
        }

    }//End - getUserDetailReport()

}//End - ReportDB.java
