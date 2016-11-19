package farmfresh.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Mom and Dad on 11/18/2016.
 * Return sql result set in HTML format
 */
public class SQLUtil {

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
    }

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
    }

}
