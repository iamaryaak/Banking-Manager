package TransactionPkg;

/**
 * A subclass of Account, this class holds the values unique to the Money Market account type
 * Handles countWithdrawals, equals, toString, monthlyInterest, monthlyFee
 */
public class MoneyMarket extends Account {

    /**
     * Declares variable private to the subclass
     */
    private int withdrawals;

    /**
     * Default constructor for the Money Market Class, uses super to send common values to Account, initializes
     * the withdrawal counter
     * @param user User the account is created for
     * @param amount Amount in the account for user
     * @param dateOpen date the account opened
     */
    public MoneyMarket (Profile user, double amount, Date dateOpen){
        super(user, amount, dateOpen);
        this.withdrawals = 0;
    }


    /**
     * helper method that increments the withdrawal variables each time it's called
     */
    public void countWithdrawals() {
        withdrawals++;
    }

    /**
     * Override method for the equals method in Account, used for the specific instance of this subclass
     * @param a account checking to be equal or not
     * @return Boolean - whether or not the accounts are equal and the same Account type
     */
    @Override
    public boolean equals(Account a) {
        boolean instance = false;
        if (a instanceof MoneyMarket && super.equals(a))
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
        return "*Money Market*" + super.toString() + "*" + withdrawals + " withdrawals*";
    }

    /**
     * Dictates the appropriate monthly interest for this Account type
     * @return double - monthly interest
     */
    @Override
    public double monthlyInterest() {
        return (double)(0.0065/12.00);
    }

    /**
     * Dictates the appropriate monthly fee for this Account type
     * @return double - monthly fee
     */
    @Override
    public double monthlyFee() {
        if(withdrawals > 6){
            return 12.00;
        }else{
            return 0.00;
        }

    }

}
