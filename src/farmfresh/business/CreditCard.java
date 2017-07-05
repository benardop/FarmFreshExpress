package farmfresh.business;

import java.util.Date;

/**
 * Purpose: CreditCard represents a Credit Card used to make purchases by a User
 * Users can have multiple credit cards
 * NOT YET IMPLEMENTED - THIS IS ONLY A SHELL
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class CreditCard {

    // INSTANCE VARIABLES
    private Long creditCardId;
    private Long userId;  // OR private User user;   what is best to use????
    private String creditCardType;
    private String creditCardNumber;
    private String creditCardExpMonth;
    private String creditCardExpYear;
    private boolean primary;

    // GETTERS AND SETTERS
    public Long getCreditCardId() { return creditCardId; }

    public void setCreditCardId(Long creditCardId) { this.creditCardId = creditCardId; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardExpMonth() {
        return creditCardExpMonth;
    }

    public void setCreditCardExpMonth(String creditCardExpMonth) {
        this.creditCardExpMonth = creditCardExpMonth;
    }

    public String getCreditCardExpYear() {
        return creditCardExpYear;
    }

    public void setCreditCardExpYear(String creditCardExpYear) {
        this.creditCardExpYear = creditCardExpYear;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

}
