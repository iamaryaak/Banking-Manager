package TransactionPkg;

/**
 *
 */
public class Profile {
    /**
     *
     */
    private String fname;
    private String lname;

    /**
     * Default Constructor
     * @param firstName
     * @param lastName
     */
    public Profile(String firstName, String lastName){
        this.fname = firstName;
        this.lname = lastName;
    }

    /**
     *
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     *
     * @param lastName
     */
    public void setLname(String lastName) {
        this.lname = lastName;
    }

    /**
     *
     * @return
     */
    public String getFname() {
        return fname;
    }

    /**
     *
     * @param firstName
     */
    public void setFname(String firstName) {
        this.fname = firstName;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return (fname + " " + lname);
    }

}