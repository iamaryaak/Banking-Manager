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
            System.out.print(accounts[i].getDate());
            // Find the minimum element in unsorted array
            Date min = accounts[i].getDate();
            int min_ind = i;
            for (int j = i+1; j < size; j++)
                if (accounts[j].getDate().compareTo((accounts[min_ind].getDate())) < 0)
                    min_ind = j;

            // Swap the found minimum element with the first
            // element
            Account temp = accounts[min_ind];
            accounts[min_ind] = accounts[i];
            accounts[i] = temp;

        }

    }

    private void sortByLastName() {
        System.out.println("Sorting by last name****");
        //sort in ascending order
        for(int i = 0; i < size; i++){
            // Find the minimum element in unsorted array
            System.out.print(accounts[i].getProfile().getLname());

        }

    }

    /**
     * print monthly interest
     */
    public void printByDateOpen() {
        sortByDateOpen();
        for(int i = 0; i < size; i++){
            System.out.println(accounts[i].toString());
        }
    }

    public void printByLastName() {
        sortByLastName();
        System.out.println("Sorting Done -------");
        for (int i = 0; i < size; i++) {
            System.out.println(accounts[i].toString());
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
            System.out.println("--End of Listing--");
        }
    }

}




