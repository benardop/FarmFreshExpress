package farmfresh.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Purpose: To initialize the Servlet Context object Attributes. <br>
 * Attributes set include:  Absolute Path , Absolute Path (Secured),
 * Default Customer Service Email Address, Current Year, Array of Years to
 * be used in Credit Card processing logic.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class FarmFreshContextListener implements ServletContextListener {

    /**
     * Initializes the Servlet Context object Attributes. <br>
     * Attributes set include:  Absolute Path , Absolute Path (Secured),
     * Default Customer Service Email Address, Current Year, Array of Years to
     * be used in Credit Card processing logic.
     *
     * @param event Which contains the ServletContext object used by this method
     */
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

    }//End - contextInitialized()

    /**
     * Performs any cleanup needed when the Context object is destroyed...
     */
    @Override
    public void contextDestroyed(ServletContextEvent event){
        //No need for clean up code - it will be done automatically

    }//End - contextDestoyed()

}//End - FarmFreshContextListener.java
