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

        AccountDatabase database = new AccountDatabase();

        do {
            String in = sc.nextLine();
            // grab command from user input
            String[] inputArr = in.split("\\s+");
            String command = inputArr[0];

            try {

                if (command.equals("Q")) {
                    System.out.println("Transaction processing completed.");
                    break;
                } else if (command.length() == 2) {
                    // figure out which command it is

                    if (command.equals("CC")) {
                        System.out.println("Close a new Checking Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account closeC = new Checking(user, 0, empty, false);
                        database.remove(closeC);

                    } else if (command.equals("CS")) {
                        System.out.println("Close a new Savings Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account closeS = new Savings(user, 0, empty, false);
                        database.remove(closeS);

                    } else if (command.equals("CM")) {
                        System.out.println("Close a new Money Market Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account closeM = new MoneyMarket(user, 0, empty);
                        database.remove(closeM);

                    }
                    else if (command.equals("OC")) {
                        System.out.println("Open a new Checking Account");
                        //Example input: OC John Doe 300 false
                        //What it does: open a checking account with $300, non-direct deposit
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);
                        String date = inputArr[4];
                        String splitDate[] = date.split("/");
                            int month = Integer.parseInt(splitDate[0]);
                            int day = Integer.parseInt(splitDate[1]);
                            int year = Integer.parseInt(splitDate[2]);
                        Date dateOpen = new Date(year, month, day);
                        Boolean directDeposit = Boolean.parseBoolean(inputArr[5]);
                        Profile user = new Profile(firstName, lastName);
                        Account accC = new Checking(user, amount, dateOpen, directDeposit);
                        database.add(accC);


                    } else if (command.equals("OS")) {
                        System.out.println("Open a new Savings Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);
                        String date = inputArr[4];
                        String splitDate[] = date.split("/");
                            int month = Integer.parseInt(splitDate[0]);
                            int day = Integer.parseInt(splitDate[1]);
                            int year = Integer.parseInt(splitDate[2]);
                        Date dateOpen = new Date(year, month, day);
                        Boolean isLoyal = Boolean.parseBoolean(inputArr[5]);
                        Profile user = new Profile(firstName, lastName);
                        Account accS = new Savings(user, amount, dateOpen, isLoyal);
                        database.add(accS);


                    } else if (command.equals("OM")) {
                        System.out.println("Open a new Money Market Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);
                        String date = inputArr[4];
                        String splitDate[] = date.split("/");
                            int month = Integer.parseInt(splitDate[0]);
                            int day = Integer.parseInt(splitDate[1]);
                            int year = Integer.parseInt(splitDate[2]);
                        Date dateOpen = new Date(year, month, day);
                        Profile user = new Profile(firstName, lastName);
                        Account accM = new MoneyMarket(user, amount, dateOpen);
                        database.add(accM);

                    }

                    // D commands – deposit funds to an existing account
                    else if (command.equals("DC")) {
                        System.out.println("Deposit to a Checking Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account depositC = new Checking(user, amount, empty, false);
                        database.deposit(depositC, amount);

                    } else if (command.equals("DS")) {
                        System.out.println("Deposit to a Savings Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account depositS = new Savings(user, amount, empty, false);
                        database.deposit(depositS, amount);

                    } else if (command.equals("DM")) {
                        System.out.println("Deposit to a Money Market Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account depositM = new MoneyMarket(user, amount, empty);
                        database.deposit(depositM, amount);

                    }

                    // W commands – withdraw funds from an existing account
                    else if (command.equals("WC")) {
                        System.out.println("Withdraw from a Checking Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account withC = new Checking(user, amount, empty, false);
                        database.withdrawal(withC, amount);

                    } else if (command.equals("WS")) {
                        System.out.println("Withdraw from a Savings Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account withS = new Savings(user, amount, empty, false);
                        database.withdrawal(withS, amount);

                    } else if (command.equals("WM")) {
                        System.out.println("Withdraw from a Money Market Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account withM = new MoneyMarket(user, amount, empty);
                        database.withdrawal(withM, amount);

                    }

                    // P commands – print the list of accounts or print account statements
                    else if (command.equals("PA")) {
                        System.out.println("Print the list of accounts in the database");
                        database.printAccounts();

                    } else if (command.equals("PD")) {
                        System.out.println("Calculate the monthly interest fees, print account statements" +
                                "and sort by dates opened in ascending order");


                    } else if (command.equals("PN")) {
                        System.out.println("Same as PD but sort by last names in ascending order");


                    }else{
                        throw new InputMismatchException(); // throw exception
                    }
                }else{
                    throw new InputMismatchException(); // throw exception
                }

            } catch (InputMismatchException e) {
                System.out.println("Command '" + command + "' not supported!");
            }catch (NumberFormatException n){
                System.out.println("Input data type mismatch.");
            }
        }while(sc.hasNext());
    }
}