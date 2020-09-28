package TransactionPkg;

public abstract class Account {
    /**
     * Declare variables here
     */
    private Profile holder;
    private double balance;
    private Date dateOpen;

    public void debit(double amount) {
        //decrease the balance by amount
    }

    public void credit(double amount) {
        // increase the balance by amount
    }

    public String toString() {
        return null;
    }

    public abstract double monthlyInterest();
    public abstract double monthlyFee();
}