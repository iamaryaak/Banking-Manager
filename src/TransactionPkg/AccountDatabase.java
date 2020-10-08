package TransactionPkg;

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

        if(find(account) >= 0)
        {
            addAcc = false;
        }

        else{
            // check if date is valid

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
        if (size == 0) {
            System.out.println("Database is empty");
            return false;
        }
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
            System.out.println("The account does not exist");
        }

        return removed;
    }

    public boolean deposit(Account account, double amount) {
        boolean deposit = false;
        int index = find(account);

        if (find(account) >= 0)
        {
            accounts[index].credit(amount);
            System.out.println("Found account and able to deposit money");
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
        int withdrawal = 0;
        int index = find(account);
        int count = 0;

        if(find(account) >= 0)
        {
            if (amount > accounts[index].getBalance())
            {
                System.out.print("Insufficient funds.");
                withdrawal = 1;
            }
            else
            {
                accounts[index].debit(amount);
                withdrawal = 0;
                count++;
            }
        }
        else
        {
            withdrawal = -1;
            System.out.println("Account does not exist.");
        }
        return withdrawal;
    }
    //return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t exist

    private void sortByDateOpen() {
        //sort in ascending order
    }

    private void sortByLastName() {
        //sort in ascending order
    }

    public void printByDateOpen() {
    }

    public void printByLastName() {
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
        }
    }

}




