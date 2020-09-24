package TransactionPkg;

import java.util.*;
public class TransactionManager {
    /**
     * User Interface class that handles transactions and displays
     * the results on the console.
     *
     * Each transaction begins with a two-letter command (case sensitive) which identifies
     * the transaction type and account type followed by data tokens
     */
    public void run() {
        Scanner sc = new Scanner (System.in);
        while(sc.hasNext()) {
            String in = sc.next();
            System.out.println(in);
        }
    }
}
