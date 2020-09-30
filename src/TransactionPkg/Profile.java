package TransactionPkg;

public class Profile {
    private String fname;
    private String lname;
    private String accountType;

    /**
     * Default Constructor
     * @param firstName
     * @param lastName
     */
    public Profile(String firstName, String lastName){
        this.fname = firstName;
        this.lname = lastName;
    }

    @Override
    public String toString(){
        return (fname + " " + lname);
    }

}