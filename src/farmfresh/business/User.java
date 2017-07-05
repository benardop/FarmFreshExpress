package farmfresh.business;

import java.util.Date;

/**
 * Purpose: User represents the User that is using the System
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class User {

    /**
     * User's Unique Identifier
     */
    private Long userId;

    /**
     * User's First Name
     */
    private String firstName;

    /**
     * User's Last Name
     */
    private String lastName;
    /**
     * User's Email Address
     */
    private String email;

    /**
     * User's Company Name
     */
    private String companyName;

    /**
     * The First Line of the User's Address
     */
    private String address1;

    /**
     * The Second Line of the User's Address
     */
    private String address2;

    /**
     * The User's City
     */
    private String city;

    /**
     * The User's State (in format VA, ME, etc)
     */
    private String state;

    /**
     * The User's Zip Code
     */
    private String zip;

    /**
     * Flag indicating that the User is Subscribed to the Newsletter
     */
    private boolean isSubscribedToNewsletter;

    /**
     * @return  User's Unique Identifier
     */
    public Long getUserId() { return userId; }

    /**
     * @param userId  User's Unique Identifier
     */
    public void setUserId(Long userId) { this.userId = userId; }

    /**
     * @return User's First Name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName  User's First Name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return User's Last Name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName  User's Last Name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return  User's Email Address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email  User's Email Address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return  User's Company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName  User's Company Name
     */
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    /**
     * @return The First Line of the User's Address
     */
    public String getAddress1() { return address1; }

    /**
     * @param address1  The First Line of the User's Address
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return  The Last Line of the User's Address
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2  The Last Line of the User's Address
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return  User's City
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city  User's City
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return  User's State
     */
    public String getState() {
        return state;
    }

    /**
     * @param state  User's State
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return  User's Zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip  User's Zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return  TRUE if User is Subscribed to the Newsletter, FALSE otherwise.
     */
    public boolean isSubscribedToNewsletter() {
        return isSubscribedToNewsletter;
    }

    /**
     * @param isSubscribedToNewsletter FLAG indicating if User is Subscribed
     * to the Newsletter
     */
    public void setIsSubscribedToNewsletter(boolean isSubscribedToNewsletter) {
        this.isSubscribedToNewsletter = isSubscribedToNewsletter;
    }

    /**
     * @return User's "Ship To" Address in HTML Format
     */
    public String getShipToAddressInHTMLFormat(){
        String addressHTML = getFirstName() + " "
                            + getLastName() + "</br>"
                            + getAddress1() + "</br>";

        String address2 = getAddress2();
        if (address2 != null && !address2.equals("")) {
            addressHTML += address2 + "</br>" ;
        }

        addressHTML += getCity() + ", "
                    + getState() + "</br>"
                    + getZip();

        return addressHTML;
    }

}//End - User.java
