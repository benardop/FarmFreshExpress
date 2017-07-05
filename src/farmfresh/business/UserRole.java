package farmfresh.business;

/**
 * Purpose: UserRole contains the User's Login Account/Name and their Role
 * Roles such as:  Admin, Super User and User are used.
 * The Role effects what functionality is accessible to the User and what Window
 * Links are displayed on the windows.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class UserRole {

    /**
     * The User's Login Account/Name
     */
    private String username;

    /**
     * The User's (e.g. Admin, Super User, User)
     */
    private String rolename;

    /**
     * @return The User's Login Account/Name
     */
    public String getUsername() { return username; }

    /**
     *
     * @param username The User's Login Account/Name
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The User's Role (e.g. Admin, Super User, User)
     */
    public String getRolename() { return rolename; }

    /**
     *
     * @param rolename The User's Role (e.g. Admin, Super User, User)
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

}//End - UserRole
