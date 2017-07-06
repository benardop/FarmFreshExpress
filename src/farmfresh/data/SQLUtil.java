package farmfresh.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Returns SQL Result Set in HTML format
 * NOT CURRENTLY USED...
 *
 * @author Benard Pacho developed the logic
 *         Amy Radtke commented the code.
 */
public class SQLUtil {

    /**
     * Returns SQL Result Set in HTML format
     * @param result {@link ResultSet}
     * @return A String containing the Result Set in HTML format
     * @throws SQLException
     */
    public static String getHTMLRows(ResultSet result) throws SQLException {

        StringBuilder htmlRows = new StringBuilder();
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();

        htmlRows.append("<tr>");
        String cell;
        for (int i = 1; i <= columnCount; i++) {
            cell = "<th>" + metaData.getColumnName(i) + "</th>";
            htmlRows.append(cell);
        }
        htmlRows.append("</tr>");

        while (result.next()) {
            htmlRows.append("<tr>");

            for (int i = 1; i <= columnCount; i++) {
                cell = "<td>" + result.getString(i) + "</td>";
                htmlRows.append(cell);
            }
        }
        htmlRows.append("</tr>");
        return htmlRows.toString();

    }//End - getHTMLRows()

    //NOT CURRENTLY USED...
    public static String encode(String s) {

        if (s == null) {
            return s;
        }

        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);

            if (ch == 39) {  // ASCII Code for apostrophe
                sb.insert(i++, " '");
            }
        }

        return sb.toString();

    }//End - encode()

}//End - SQLUtil.java
