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

        do {
            String in = sc.nextLine();
            try {
                String[] inputArr = in.split("\\s+");
                String command = inputArr[0];

                if (command.equals("Q")) {
                    System.out.println("Transaction processing completed.");
                    break;
                } else if (command.length() == 2) {
                    // figure out which command it is

                    // C commands – close an existing account
                    if (command.equals("CC")) {

                    } else if (command.equals("CS")) {

                    } else if (command.equals("CM")) {

                    }

                    // O commands – open a new checking (C), savings (S) or money market (M) account
                    else if (command.equals("OC")) {

                    } else if (command.equals("OS")) {

                    } else if (command.equals("OM")) {

                    }

                    // D commands – deposit funds to an existing account
                    else if (command.equals("DC")) {

                    } else if (command.equals("DS")) {

                    } else if (command.equals("DM")) {

                    }

                    // W commands – withdraw funds from an existing account
                    else if (command.equals("WC")) {

                    } else if (command.equals("WS")) {

                    } else if (command.equals("WM")) {

                    }

                    // P commands – print the list of accounts or print account statements
                    else if (command.equals("PA")) {

                    } else if (command.equals("PD")) {

                    } else if (command.equals("PN")) {

                    }
                }

                // Throws catch exceptions for invalid input
                if (command.matches("^[0-9]+$"))
                    throw new InputMismatchException();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
            }catch (NumberFormatException n){
                System.out.println("Invalid numbers!");
            }
        }while(sc.hasNextLine());
    }
}