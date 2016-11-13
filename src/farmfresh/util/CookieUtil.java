package farmfresh.util;

import javax.servlet.http.*;

/**
 * Created by Mom and Dad on 11/9/2016.
 */
public class CookieUtil {

    public static String getCookieValue(Cookie[] cookies, String cookieName){
            String cookieValue = "";
            if (cookies != null){
                for (Cookie cookie: cookies) {
                    if (cookieName.equals(cookie.getName())){
                        cookieValue = cookie.getValue();
                    }
                }
            }
        return cookieValue;
    }
}