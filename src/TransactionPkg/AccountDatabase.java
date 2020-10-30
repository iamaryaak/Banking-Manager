package TransactionPkg;
import java.text.DecimalFormat;

/**
 * Handles the database for all types of accounts that the user inputs in the program.
 * Handles print, remove, add, and automatically grows the database when limit is reached.
 */
public class AccountDatabase {

    /**
     * Declares array to hold accounts
     * Declares array size
     */
    private Account[] accounts;
    private int size;


    /**
     * Default constructor for AccountDatabase class
     * Initializes variables to starting values as described below to create an Accounts Database
     */
    public AccountDatabase(){
        this.size = 0;
        this.accounts = new Account[5];
    }

    /**
     * This method finds the account based on the equals method given in Account when an account is passed
     * @param account Target account
     * @return int - finds the index at which the account is and returns that value
     */
    private int find(Account account) {
        int index = -10;

        for (int i = 0; i < size; i++) {
            if (accounts[i].equals(account)) {
                // you found the item
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * This method grows the capacity by 5 accounts when the size is equal to the capacity
     */
    private void grow() {

        int capacity = 0;
        capacity = accounts.length;
        // if the size of the database is 5, increase the capacity by 5
        if (size == accounts.length) {
            Account[] moreAcc = new Account[capacity + 5];
            for (int i = 0; i < size; i++) {
                moreAcc[i] = accounts[i];
            }
            accounts = moreAcc;
        }
    }

    /**
     * Adds an account to the database
     * @param account Account to be added to the database
     * @return - returns a boolean that shows whether or not an account already exists,
     * if it doesn't an account is added.
     */
    public boolean add(Account account) {

        boolean addAcc;
        int returnValFind = -10;
        if(find(account) != returnValFind)
        {
            addAcc = false;
        } else{
            size++;
            grow();
            // adding an account to the database
            accounts[size-1] = account;
            addAcc = true;
        }

        return addAcc;
    }

    /**
     * Remove an account from the Accounts array
     * @param account Target account to remove
     * @return boolean, returns true or false if remove is successful
     */
    public boolean remove(Account account) {
        boolean removed = false;
        /**if (size == 0) {
            System.out.println("Database is empty");
            return false;
        }**/
        int index = find(account);
        if (index != -10) {
            removed = true;
            // swap
            Account temp = accounts[index];
            accounts[index] = accounts[size - 1];
            accounts[size - 1] = temp;

            size--;
            // change last item to null
            accounts[size] = null;

        }
        else {
            removed = false;
            System.out.println("Account does not exist");
        }

        return removed;
    }

    /**
     * Deposits an amount into an account, return value is dependent on it's success
     * @param account Target account to deposit money into
     * @param amount Target amount to add to the balance
     * @return Boolean - returns true or false if the deposit is successful
     */
    public boolean deposit(Account account, double amount) {
        boolean deposit = false;
        int index = find(account);

        if (find(account) >= 0)
        {
            accounts[index].credit(amount);
            deposit = true;
        }
        else
        {
            deposit = false;

        }

        return deposit;
    }

    /**
     * Withdrawals a passed amount from a passed account, returns an int dependent on it's success
     * @param account Target account to remove money from
     * @param amount Target amount to be removed
     * @return Int - 0 if successful, 1 if insufficient funds, -1 if the account doesn't exist
     */
    public int withdrawal(Account account, double amount) {
        int withdrawals = 0;
        int index = find(account);

        if(find(account) >= 0)
        {
            if (amount > accounts[index].getBalance())
            {
                withdrawals = 1;
            }
            else
            {
                accounts[index].debit(amount);
                withdrawals = 0;
                if(accounts[index] instanceof MoneyMarket)
                {
                    ((MoneyMarket) accounts[index]).countWithdrawals();
                }
            }
        }
        else
        {
            withdrawals = -1;

        }
        return withdrawals;
    }

    /**
     * Sorts the accounts based on the ascending order they're opened
     */
    private void sortByDateOpen() {
        //sort in ascending order
        for(int i = 0; i < size; i++){
            // Find the minimum element in unsorted array
            int min = i;
            for ( int k=i+1; k < size; k++ )

                if ( accounts[k].getDate().compareTo(accounts[min].getDate()) > 0 ) {
                    min = k;
                }
            // Swap the reference at j with the reference at min
            Account temp = accounts[i];
            accounts[i] = accounts[min];
            accounts[min] = temp;

        }

    }

    /**
     * Sorts the accounts in the ascending order of their last name
     */
    private void sortByLastName() {
        //sort in ascending order
        for(int i = 0; i < size; i++){
            // Find the minimum element in unsorted array
            int min = i;
            for ( int k=i+1; k < size; k++ )
                if (accounts[k].getProfile().getLname().compareTo(accounts[min].getProfile().getLname()) < 0 ) {
                    min = k;
                }
            // Swap the reference at j with the reference at min
            Account temp = accounts[i];
            accounts[i] = accounts[min];
            accounts[min] = temp;

        }

    }

    /**
     * Prints the Statements based on the sorting of dates, including interest and fees
     */
    public String[] printByDateOpen() {
        String[] res = new String[size];
        String accountInfo = "";
        int index = 0;
        if (size == 0)
        {
            return new String[]{"Database is empty."};
        }else {
            DecimalFormat df = new DecimalFormat("#,##0.00");
            sortByDateOpen();
            //accountInfo = accountInfo + ("--Printing statements by Date Open --");
            for (int i = 0; i < size; i++) {
                accountInfo = (accounts[i].toString() + "\n");
                // get interest, fee, new balance
                // get account type
                double interest = (accounts[i].getBalance() * accounts[i].monthlyInterest());
                accountInfo = accountInfo + ("-interest: $ " + df.format(interest) + "\n");
                double fee = accounts[i].monthlyFee();

                // Create conditions to print out fees per account type
                if ( accounts[i] instanceof Checking) {
                    if(accounts[i].getBalance() >= 1500) {
                        fee = 0.00;
                    }
                } else if (accounts[i] instanceof Savings) {
                    if(accounts[i].getBalance() >= 300){
                        fee = 0.00;
                    }
                } else if (accounts[i] instanceof MoneyMarket) {
                    if(accounts[i].getBalance() >= 2500 && fee == 12.00 || accounts[i].getBalance() < 2500 && fee == 0.00){
                        fee = 12.00;
                    }else{
                        fee = 0;
                    }
                }
                accountInfo = accountInfo + ("-fee: $ " + df.format(fee) + "\n");
                double newBal = (accounts[i].getBalance() * (1 + accounts[i].monthlyInterest()) - fee);
                accounts[i].setBalance(newBal);
                accountInfo = accountInfo + ("-new balance: $ " + df.format(newBal) + "\n");

                // set and reset
                res[index] = accountInfo;
                index++;
                accountInfo = "";
            }
        }
        return res;
    }

    /**
     * Prints the Statements based on the sorting of last names, including interest and fees
     */
    public String[] printByLastName() {
        String[] returnable = new String[size];
        int index = 0;
        if (size == 0)
        {
            String res = ("Database is empty.");
            String[] all = new String[1];
            all[0] = res;
            return all;
        }else {
            DecimalFormat df = new DecimalFormat("#,##0.00");
            sortByLastName();
            String addition = "";
            //System.out.println("--Printing statements by last name--");
            for (int i = 0; i < size; i++) {
                addition = addition + (accounts[i].toString());
                // get interest, fee, new balance
                // get account type
                double interest = (accounts[i].getBalance() * accounts[i].monthlyInterest());
                addition = addition + ("\n-interest: $ " + df.format(interest)+"\n");
                double fee = accounts[i].monthlyFee();

                // Create conditions to print out fees per account type
                if ( accounts[i] instanceof Checking) {
                    if(accounts[i].getBalance() >= 1500) {
                        fee = 0.00;
                    }
                } else if (accounts[i] instanceof Savings) {
                    if(accounts[i].getBalance() >= 300){
                        fee = 0.00;
                    }
                } else if (accounts[i] instanceof MoneyMarket) {
                    if(accounts[i].getBalance() >= 2500 && fee == 12.00 || accounts[i].getBalance() < 2500 && fee == 0.00){
                        fee = 12.00;
                    }else{
                        fee = 0;
                    }
                }
                addition = addition + ("-fee: $ " + df.format(fee) + "\n");
                double newBal = (accounts[i].getBalance() * (1 + accounts[i].monthlyInterest()) - fee);
                accounts[i].setBalance(newBal);
                addition = addition + ("-new balance: $ " + df.format(newBal)) + "\n";

                addition = addition + ("\n");
                returnable[index] = addition;
                addition = "";
                index++;
            }
            //System.out.println("--end of printing--");
        }
        return returnable;
    }

    /**
     * Prints the accounts in the database
     */
    public String[] printAccounts() {
        String[] returnable = new String[size];
        String res = "";
        if (size == 0)
        {
            return new String[]{"Database is empty."};
        }

        else {
            //System.out.println("--Listing accounts in the database--");
            for (int i = 0; i < size; i++) {
                res = (accounts[i].toString() + "\n");
                returnable[i] = res;
            }
            //System.out.println("--end of listing--");
        }

        return returnable;
    }

}




