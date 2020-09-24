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
        System.out.println("Transaction processing starts.....");
        Scanner sc = new Scanner (System.in);
        do{

            // figure out commands
            try {
                String in = sc.nextLine();
                String inputArr[] = in.split("\\s+");
                String command = inputArr[0];

                if (command.equals("Q")) {
                    System.out.println("Transaction processing completed.");
                    break;
                }
            }catch(Exception e){
                System.out.println("Invalid");
            }

        }while(sc.hasNext()) ;
    }
}
