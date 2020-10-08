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



    public String getLname() {
        return lname;
    }

    // Setter
    public void setLname(String lastName) {
        this.lname = lastName;
    }

    public String getFname() {
        return fname;
    }

    // Setter
    public void setFname(String firstName) {
        this.fname = firstName;
    }


    @Override
    public String toString(){
        return (fname + " " + lname);
    }

}