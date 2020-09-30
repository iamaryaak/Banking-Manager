package TransactionPkg;

public class AccountDatabase {

    private Account[] accounts;
    private int size;
    private int capacity;

    /**
     * Default constructor for AccountDatabase class
     * Initializes variables to starting values as described below to create an Accounts Database
     */
    public AccountDatabase(){
        this.capacity = 5;
        this.size = 0;
        this.accounts = new Account[capacity];
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

        // if the size of the database is 5, increase the capacity by 5
        if (size == capacity) {
            capacity += 5;
            Account[] moreAcc = new Account[capacity];
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
            System.out.println("Capacity: " + capacity);
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
    }

}




