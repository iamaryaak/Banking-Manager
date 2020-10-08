package TransactionPkg;

import java.text.DecimalFormat;

/**
 * Abstract class that is the super class for the respective subclass account types
 * Handles debit, void, setBalance, getBalance, equals, getProfile, getDate, toString, monthlyInterest, monthlyFee
 */
public abstract class Account {
    /**
     * Declare variables here
     */
    private Profile holder;
    private double balance;
    private Date dateOpen;

    /**
     * Default Constructor for the Account abstract class, initializes the variables common to all subclasses
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

    /**
     * When withdrawal is called, this method debits the balance the passed amount
     * @param amount
     */
    public void debit(double amount) {
        //decrease the balance by amount
        this.balance -= amount;

    }

    /**
     * When deposit is called, this method credits the balance the passed amount
     * @param amount
     */
    public void credit(double amount) {
        this.balance += amount;
    }

    /**
     * This method sets the balance in order to be used for the sorting methods in AccountDatabase
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * This method returns the balance to be used in the AccountDatabase
     * @return double - the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * This method is intended compare accounts based on the Profile of a given account
     * @param a - Account
     * @return boolean - returns true if equal, false if not
     */
    public boolean equals(Account a){
        if (a != null) {
            return this.holder.getFname().equals(a.getProfile().getFname())
                    && this.holder.getLname().equals((a.getProfile().getLname()));
        } else
            return false;
    }

    /**
     * This method gets the Profile from the holder initialized in this class
     * @return Profile - holder
     */
    public Profile getProfile()
    {
        return this.holder;
    }

    /**
     * Gets the date to be used in the Account Database Class
     * @return Date - dateOpen
     */
    public Date getDate(){return dateOpen;}

    /**
     * Transforms the object into a String while displaying all elements of it
     * @return String represents the object and its elements
     */
    public String toString(){
        DecimalFormat df = new DecimalFormat("#,##0.00");

        return holder.toString() + "* "+ "$" + df.format(balance) +
                "*" + dateOpen.toString();
    }

    /**
     * Abstract method - method used in subclasses to determine monthly interest
     * @return double - controlled in subclasses
     */
    public abstract double monthlyInterest();

    /**
     * Abstract method - method used in subclasses to determine monthly fee
     * @return double - controlled in subclasses
     */
    public abstract double monthlyFee();
}