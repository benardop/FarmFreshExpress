package farmfresh.business;

/**
 * Purpose: UserPass contains the User's Login Account/Name and Password
 * The User must enter both correctly to Login to the System
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class UserPass {

    /**
     * The User's Login Account/Name
     */
    private String username;

    /**
     * The User's Password
     */
    private String password;

    /**
     * @return The User's Login Account/Name
     */
    public String getUsername() { return username; }

    /**
     * @param username The User's Login Account/Name
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The User's Password
     */
    public String getPassword() { return password; }

    /**
     * @param password The User's Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}//End - UserPass
