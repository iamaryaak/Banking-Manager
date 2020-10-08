package TransactionPkg;

/**
 * A subclass of account, this class holds the Savings account type and the values unique to it
 * Handles isLoyal, equals, toString, monthlyInterest, monthlyFee
 */
public class Savings extends Account {

    /**
     * Declares the variable private to Savings
     */
    private boolean isLoyal;

    /**
     * Default constructor for Savings, sends the values common to Account, initializes the variable unique to
     * the subclass
     * @param user
     * @param amount
     * @param dateOpen
     * @param i
     */
    public Savings (Profile user, double amount, Date dateOpen, boolean i){
        super(user, amount, dateOpen);
        this.isLoyal = i;
    }

    /**
     * Used in the toString, if the isLoyal variable is true, it sends a string for the account type
     * @return String - for the account type
     */
    public String isLoyal() {
        String saveType;
        if (isLoyal)
        {
            saveType = "*special Savings account*";
        }
        else
            saveType = "";

        return saveType;
    }

    /**
     * Overrides the equals method in Account, used to ensure the comparison is from the same account type
     * @param a
     * @return Boolean - true if equal, false if not
     */
    @Override
    public boolean equals(Account a) {
        boolean instance = false;
        if (a instanceof Savings && super.equals(a))
            instance = true;

        return instance;
    }

    /**
     * Override method transforms the object into a String while displaying all elements of it
     * @return String - specific for this subclass inheriting the common values from Account
     */
    @Override
    public String toString()
    {
        return "*Savings*" + super.toString() + isLoyal();
    }

    /**
     * Dictates the appropriate monthly interest for this Account type
     * @return double - monthly interest
     */
    @Override
    public double monthlyInterest() {
        if(isLoyal){
            return (double)(0.0035/12.00);
        }else{
            return (double)(0.0025/12.00);
        }
    }

    /**
     * Dictates the appropriate monthly fee for this Account type
     * @return double - monthly fee
     */
    @Override
    public double monthlyFee() {
        return 5.00;
    }



}