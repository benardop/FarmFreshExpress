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
 * Created by Mom and Dad on 11/15/2016.
 */
public class ReportDB {

    // Create a workbook, its worksheet and a title row
    public static Workbook getUserEmail(){
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("User Email Report");  // Header
        Row row = sheet.createRow(0);
//        Row row = sheet.getRow(0);
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
        row.createCell(9).setCellValue("Country");
        row.createCell(10).setCellValue("UserID");

        // Create data rows
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User"
                     + " ORDER BY LastName";

        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

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
                row.createCell(9).setCellValue(rs.getString("Country"));
                row.createCell(10).setCellValue(rs.getString("UserID"));
                i++;
            }

            return workbook;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
        }
    }

//    public static Workbook getDownloadDetail(String startDate, String endDate){
//        HSSFWorkbook workbook = new HSSFWorkbook();
//
//        //return workbook;
//    }



}
