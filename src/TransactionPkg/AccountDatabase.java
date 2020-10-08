package TransactionPkg;
import java.text.DecimalFormat;

public class AccountDatabase {

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
     *
     * @param account
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
     *
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
     * @param account
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
     * @param account
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
            System.out.println("Account does not exist");
        }

        return deposit;
    }

    public int withdrawal(Account account, double amount) {
        int withdrawals = 0;
        int index = find(account);

        if(find(account) >= 0)
        {
            if (amount > accounts[index].getBalance())
            {
                System.out.println("Insufficient funds.");
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
            System.out.println("Account does not exist.");
        }
        return withdrawals;
    }
    //return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t exist

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
     * print monthly interest
     */
    public void printByDateOpen() {
        if (size == 0)
        {
            System.out.println("Database is empty.");
        }else {
            DecimalFormat df = new DecimalFormat("#.00");
            sortByDateOpen();
            System.out.println("--Printing statements by last name--");
            for (int i = 0; i < size; i++) {
                System.out.println("\n" + accounts[i].toString());
                // get interest, fee, new balance
                // get account type
                double interest = (accounts[i].getBalance() * accounts[i].monthlyInterest());
                System.out.println("-interest: $ " + df.format(interest));
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
                    if(accounts[i].getBalance() >= 2500) {
                        fee = 0.00;
                    }
                }
                System.out.println("-fee: $ " + df.format(fee));
                double newBal = (accounts[i].getBalance() * (1 + accounts[i].monthlyInterest()) - fee);
                accounts[i].setBalance(newBal);
                System.out.println("-new balance: $ " + df.format(newBal));

                System.out.println();

            }
            System.out.println("--end of printing--");
        }
    }

    public void printByLastName() {
        if (size == 0)
        {
            System.out.println("Database is empty.");
        }else {
            DecimalFormat df = new DecimalFormat("#.00");
            sortByLastName();
            System.out.println("--Printing statements by last name--");
            for (int i = 0; i < size; i++) {
                System.out.println("\n" + accounts[i].toString());
                // get interest, fee, new balance
                // get account type
                double interest = (accounts[i].getBalance() * accounts[i].monthlyInterest());
                System.out.println("-interest: $ " + df.format(interest));
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
                    if(accounts[i].getBalance() >= 2500) {
                        fee = 0.00;
                    }                }
                System.out.println("-fee: $ " + df.format(fee));
                double newBal = (accounts[i].getBalance() * (1 + accounts[i].monthlyInterest()) - fee);
                accounts[i].setBalance(newBal);
                System.out.println("-new balance: $ " + df.format(newBal));

                System.out.println();

            }
            System.out.println("--end of printing--");
        }
    }

    public void printAccounts() {

        if (size == 0)
        {
            System.out.println("Database is empty.");
        }

        else {
            System.out.println("--Listing accounts in the database--");
            for (int i = 0; i < size; i++) {
                System.out.println(accounts[i].toString());
            }
            System.out.println("--end of listing--");
        }
    }

}




