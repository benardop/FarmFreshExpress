import java.util.ArrayList;
import java.util.List;

/**   BEN What is the best way to use JavaDoc for commenting through out the code
 *    BEN Is there a standard way of formatting code documentation and comments w/ javadoc
 *
 * Class:  Customer.java
 * Desc:   Customer is the class containing all customer related functionality
 * <ul>
 * LEARN HOW TO BEST USE COMMENTS --- DETAIL THIS OUT FURTHER LATER
 * <li>The Component to draw on
 * <li>A translation origin for rendering and clipping coordinates
 * <li>The current clip
 * <li>The current color
 * <li>The current font
 * <li>The current logical pixel operation function (XOR or Paint)
 * <li>The current XOR alternation color
 *     (see <a href="#setXORMode">setXORMode</a>)
 * </ul>
 * <p>
 * Coordinates are infinitely thin and lie between the pixels of the
 * output device.
 * EXAMPLE PARAGRAPH
 * <p>
 * All coordinates which appear as arguments to the methods of this
 * Graphics object are considered relative to the translation origin
 * EXAMPLE PARAGRAPH
 *
 * @author      Amy Radtke
 * @version     %I%, %G%     ????????
 * @since       1.0     ???????
 */

//  BEN I would like an automated tool (Batch???) that would take a class definition and spit out a
//      Class with all of the getters, setters - etc...  So I have a full class template to start with.

    /*
    Class:  Customer
      TODO Set up the class description to use javadoc for documentation purposes!
     */
public class Customer {
    private String name;          //  I think it's OK to store in a single field
    //private Address address;   //BEN Is there a class that encapsulates addresses and offers formatting
    private String emailAddress;  //BEN Is there a class that encapsulates email addresses and offers formatting
    private String mobilePhone;
    private String passWord;      //Is there a class for passwords
    //private payment method //BEN - what is the best way to manage payment information

    private ShoppingCart cart;

}
