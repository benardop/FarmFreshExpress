package farmfresh.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Mom and Dad on 11/13/2016.
 */
public class FarmFreshContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event){

        ServletContext sc = event.getServletContext();

        //get the absolute parts for switching regular and secure connections
        String contextPath = sc.getContextPath();
        String absolutePath = "http://127.0.0.1:8080" + contextPath;
        String absolutePathSecure = "http://127.0.0.1:8443" + contextPath;

        sc.setAttribute("absolutePath", absolutePath);
        sc.setAttribute("absolutePathSecure", absolutePathSecure);

        // Initialize the customer service address that is used throughout the website
        String custServEmail = sc.getInitParameter("custServEmail");
        sc.setAttribute("custServEmail", custServEmail);

        // Initialize the current year that is used in the copywrite notice
        GregorianCalendar currentDate = new GregorianCalendar();
        int currentYear = currentDate.get(Calendar.YEAR);
        sc.setAttribute("currentYear", currentYear);

        // Initialize the array of years that is used for the Credit Card Year
        ArrayList<String> creditCardYears = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            int year = currentYear + i;
            String yearString = Integer.toString(year);
            creditCardYears.add(yearString);
        }
        sc.setAttribute("creditCardYears", creditCardYears);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event){
        //No need for clean up code - it will be done automatically
    }

}
