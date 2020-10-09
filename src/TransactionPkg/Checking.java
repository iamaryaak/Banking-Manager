package TransactionPkg;

/**
 * A subclass of Account, this class holds the values unique to the Checking account type
 * Handles isDirectDeposit, equals, toString, monthlyInterest, monthlyFee
 */
public class Checking extends Account {

    /**
     * Declares variable private to class
     */
    private boolean directDeposit;

    /**
     * Default constructor of the Checking class, uses super to send common values to Account, initializes
     * unique class variable
     * @param user
     * @param balance
     * @param dateOpen
     * @param d
     */
    public Checking(Profile user, double balance, Date dateOpen, boolean d) {
        super(user, balance, dateOpen);
        this.directDeposit = d;

    }

    /**
     * Helper method used in toString below, this method adds a String based on the Checking account type
     * @return String - the deposit type
     */
    public String isDirectDeposit() {
        String depositType;
        if (directDeposit)
        {
            depositType = "*direct deposit account*";
        }
        else
            depositType = "";

        return depositType;
    }

    /**
     * Override method for the equals method in Account, used for the specific instance of this subclass
     * @param a
     * @return Boolean - whether or not the accounts are equal and the same Account type
     */
    @Override
    public boolean equals(Account a) {
        boolean instance = false;
        if (a instanceof Checking && super.equals(a))
            instance = true;

        return instance;
    }

    /**
     * Override method transforms the object into a String while displaying all elements of it
     * @return String - specific for this subclass inheriting the common values from Account
     */
    @Override
    public String toString(){
        return "*Checking*" +  super.toString() + isDirectDeposit();
    }

    /**
     * Dictates the appropriate monthly interest for this Account type
     * @return double - monthly interest
     */
    @Override
    public double monthlyInterest() {
        return (double)(0.0005/12.00);
    }


    /**
     * Dictates the appropriate monthly fee for this Account type
     * @return double - monthly fee
     */
    @Override
    public double monthlyFee() {
        if (directDeposit || getBalance() >= 1500)
            return 0;
        return 25.00;
    }



}