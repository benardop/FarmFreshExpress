package farmfresh.util;

import javax.servlet.http.*;

/**
 * Purpose: To provide helpful functions relating to Cookies such as:<br>
 * Returning a Cookie from the Array of Cookies given the Cookie's Name <br>
 * Returning the Value of a Cookie with a given Cookie Name that was found
 * in the Array of Cookies provided.
 *
 * @author Benard Pacho developed the logic  Amy Radtke commented the code.
 */
public class CookieUtil {

    /**
     * Searches the given Array of Cookies for a Cookie with the given Name -
     * Returning the Value stored within that Cookie
     *
     * @param cookies    - The Array of Cookies to be searched
     * @param cookieName - The Name of the Cookie
     * @return - The Value of the Cookie with the given Name
     */
    public static String getCookieValue(Cookie[] cookies, String cookieName) {
        String cookieValue = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                }
            }
        }
        return cookieValue;
    }//End - getCookieValue()

    /**
     * Returns the Cookie with the given Name from the Array of Cookies provided
     * @param cookies - The Array of Cookies to be searched
     * @param cookieName - The Name of the Cookie
     * @return The Cookie with the given Name
     */
    public static Cookie getCookie(Cookie[] cookies, String cookieName){
        if (cookies != null){
            for (Cookie cookie: cookies) {
                if (cookieName.equals(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }//End - getCookie()

}//End - CookieUtil.java