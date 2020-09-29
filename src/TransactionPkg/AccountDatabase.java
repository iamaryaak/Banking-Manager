package TransactionPkg;

public class AccountDatabase {

    private Account[] accounts;
    private int size;

    /**
     * Default constructor for AccountDatabase class
     * Initializes variables to starting values as described below to create an Accounts Database
     */
    public AccountDatabase(Account[] accounts, int size){
        this.accounts = new Account[5];
        this.size = 0;
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
    }

    public boolean add(Account account) {
        //return false if account exists
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




