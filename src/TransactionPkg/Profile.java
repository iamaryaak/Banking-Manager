package TransactionPkg;

public class Profile {
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

    public String getFname() {
        String firstName = "";
        this.fname = firstName;
        return firstName;
    }

    public String getLname() {
        String lastName = "";
        this.lname = lastName;
        return lastName;
    }

    @Override
    public String toString(){

        return (fname + " " + lname);
    }

}