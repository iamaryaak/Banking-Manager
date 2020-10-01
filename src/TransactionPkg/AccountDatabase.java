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
     * @return int - finds the index at which the account is
     */
    private int find(Account account) {

        return 0;
    }

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

    public boolean add(Account account) {
        size++;
        grow();
        // adding an account to the database
        accounts[size-1] = account;

        // just to test if it work
            System.out.println("Account added to the database");
            System.out.println("Capacity: " + accounts.length);
            System.out.println("Size: " + size);

        return false;
    }

    public boolean remove(Account account) {
        //return false if account doesn't exist
        return false;
    }

    public boolean deposit(Account account, double amount) {
        //return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t exist
        return false;
    }

    public int withdrawal(Account account, double amount) {
        return 0;
    }

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
            System.out.println("There are no accounts in the database");
        }

        else {
            for (int i = 0; i < size; i++) {
                System.out.println(accounts[i].toString());
            }
        }
    }

}




