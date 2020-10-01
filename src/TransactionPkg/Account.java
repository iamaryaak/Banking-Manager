package TransactionPkg;

import java.text.DecimalFormat;

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
        DecimalFormat df = new DecimalFormat("#.00");

        // i.e. *Checking*John Doe* $500.00*1/1/2010*direct deposit account*
        // need to figure out how to differentiate account types here

        return (holder + " " + "$" + df.format(balance) + " " + dateOpen.toString());
    }

    public abstract double monthlyInterest();
    public abstract double monthlyFee();
}