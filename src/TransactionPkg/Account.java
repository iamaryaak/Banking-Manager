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

    public void setBalance(double balance) {
        this.balance = balance;
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

    public Profile getProfile()
    {
        return this.holder;
    }
    public Date getDate(){return dateOpen;}

    /**
     * Transforms the object into a String while displaying all elements of it
     * @return String represents the object and its elements
     */

    public String toString(){
        DecimalFormat df = new DecimalFormat("#.00");

        return holder.toString() + "* "+ "$" + df.format(balance) +
                "*" + dateOpen.toString();
    }

    /**
     * Abstract methods
     * @return
     */
    public abstract double monthlyInterest();
    public abstract double monthlyFee();
}