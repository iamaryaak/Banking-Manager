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

    public String isDirectDeposit() {
        return null;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        String output = "";
        if (this instanceof Checking)
        {
           output = ("*Checking*" + holder + "* "+ "$" + df.format(balance) +
                    "*" + dateOpen.toString() + isDirectDeposit());
        }

        else if (this instanceof Savings)
        {
            output = ("*Savings*" + holder + "* " + "$" + df.format(balance) +
                    "*"+ dateOpen.toString() +"*special Savings account*");
        }

        return output;
    }

    public abstract double monthlyInterest();
    public abstract double monthlyFee();
}