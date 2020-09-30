package TransactionPkg;

public abstract class Account {
    /**
     * Declare variables here
     */
    private Profile holder;
    private double balance;
    private Date dateOpen;

    public Account(Profile h, double b, Date d)
    {
        this.holder = h;
        this.balance = b;
        this.dateOpen = d;
    }

    public void debit(double amount) {
        //decrease the balance by amount
        balance = balance - amount;
    }

    public void credit(double amount) {
        // increase the balance by amount
        balance = balance + amount;
    }

    @Override
    public String toString() {
        return (holder + " " + "$" + balance + " " + dateOpen.toString());
    }

    public abstract double monthlyInterest();
    public abstract double monthlyFee();
}