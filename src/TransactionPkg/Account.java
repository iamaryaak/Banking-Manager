package TransactionPkg;

import java.text.DecimalFormat;


public abstract class Account {
    /**
     * Declare variables here
     */
    private Profile holder;
    private double balance;
    private Date dateOpen;

    /**
     * Default Constructor
     * @param h
     * @param b
     * @param d
     */
    public Account(Profile h, double b, Date d)
    {
        this.holder = h;
        this.balance = b;
        this.dateOpen = d;
    }

    public void debit(double amount) {
        //decrease the balance by amount
        this.balance -= amount;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public boolean equals(Account a){
        if (a != null) {
            return this.holder.getFname().equals(a.getProfile().getFname())
                    && this.holder.getLname().equals((a.getProfile().getLname()));
        } else
            return false;
    }

    // this is the method that allows polymorphism, it's override is in Checking, dictates checking-type
    public String isDirectDeposit() {
        return null;
    }
    public String isLoyal() {
        return null;
    }

    public Profile getProfile()
    {
        return holder;
    }

    /**
     * Transforms the object into a String while displaying all elements of it
     * @return String represents the object and its elements
     */
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
                    "*"+ dateOpen.toString() + isLoyal());
        }

        else if (this instanceof MoneyMarket)
        {
            output = ("*Money Market*" + holder + "* " + "$" + df.format(balance) +
                    "*"+ dateOpen.toString()) /*here there will be count++ of withdrawls*/ ;
        }

        return output;
    }

    /**
     * Abstract methods
     * @return
     */
    public abstract double monthlyInterest();
    // I got the monthly interest rate conversion for all three types of accounts
    public abstract double monthlyFee();


}