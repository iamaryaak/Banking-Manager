package TransactionPkg;

/**
 * This class holds the Profile object, which includes the first and last name of an account holder
 * Handles getLname, getFname, toString
 */
public class Profile {
    /**
     * Declares variables private to the profile
     */
    private String fname;
    private String lname;

    /**
     * Default Constructor of the profile, initializes variables declared for the class
     * @param firstName
     * @param lastName
     */
    public Profile(String firstName, String lastName){
        this.fname = firstName;
        this.lname = lastName;
    }

    /**
     * Gets the last name from this class to be used in other methods
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     * Gets the first name from this class to be used in equals method
     * @return
     */
    public String getFname() {
        return fname;
    }

    /**
     * Combines the first name and last name into a single string
     * @return String - returns the combined name
     */
    @Override
    public String toString(){
        return (fname + " " + lname);
    }

}