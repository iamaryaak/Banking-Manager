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
                        if(inputArr.length != 3){
                            throw new NumberFormatException();
                        }
                        System.out.println("Close a new Checking Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account closeC = new Checking(user, 0, empty, false);
                        database.remove(closeC);

                    } else if (command.equals("CS")) {
                        if(inputArr.length != 3){
                            throw new NumberFormatException();
                        }
                        System.out.println("Close a new Savings Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account closeS = new Savings(user, 0, empty, false);
                        database.remove(closeS);

                    } else if (command.equals("CM")) {
                        if(inputArr.length != 3){
                            throw new NumberFormatException();
                        }
                        System.out.println("Close a new Money Market Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account closeM = new MoneyMarket(user, 0, empty);
                        database.remove(closeM);

                    }
                    else if (command.equals("OC")) {
                        if(inputArr.length != 6){
                            throw new NumberFormatException();
                        }
                        System.out.println("Open a new Checking Account");
                        //Example input: OC John Doe 300 false
                        //What it does: open a checking account with $300, non-direct deposit
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);
                        String date = inputArr[4];
                        String[] splitDate = date.split("/");
                        int month = Integer.parseInt(splitDate[0]);
                        int day = Integer.parseInt(splitDate[1]);
                        int year = Integer.parseInt(splitDate[2]);

                        // check if date is valid
                        Date dateOpen = new Date(year, month, day);
                        if(dateOpen.isValid()){
                            boolean directDeposit = Boolean.parseBoolean(inputArr[5]);
                            Profile user = new Profile(firstName, lastName);
                            Account accC = new Checking(user, amount, dateOpen, directDeposit);
                            boolean added = database.add(accC);
                            if (added) {
                                System.out.println("Account opened and added to the database.");
                            } else{
                                System.out.println("Account is already in the database.");
                            }
                        }else{
                            System.out.println(dateOpen.toString() + " is not a valid date!");
                        }


                    } else if (command.equals("OS")) {
                        if(inputArr.length != 6){
                            throw new NumberFormatException();
                        }
                        System.out.println("Open a new Savings Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);
                        String date = inputArr[4];
                        String splitDate[] = date.split("/");
                        int month = Integer.parseInt(splitDate[0]);
                        int day = Integer.parseInt(splitDate[1]);
                        int year = Integer.parseInt(splitDate[2]);
                        // check if date is valid
                        Date dateOpen = new Date(year, month, day);
                        if(dateOpen.isValid()){
                            Boolean isLoyal = Boolean.parseBoolean(inputArr[5]);
                            Profile user = new Profile(firstName, lastName);
                            Account accS = new Savings(user, amount, dateOpen, isLoyal);
                            boolean added = database.add(accS);
                            if (added) {
                                System.out.println("Account opened and added to the database.");
                            } else{
                                System.out.println("Account is already in the database.");
                            }
                        }else{
                            System.out.println(dateOpen.toString() + " is not a valid date!");
                        }

                    } else if (command.equals("OM")) {
                        if(inputArr.length != 5){
                            throw new NumberFormatException();
                        }
                        System.out.println("Open a new Money Market Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);
                        String date = inputArr[4];
                        String splitDate[] = date.split("/");
                        int month = Integer.parseInt(splitDate[0]);
                        int day = Integer.parseInt(splitDate[1]);
                        int year = Integer.parseInt(splitDate[2]);
                        // check if date is valid
                        Date dateOpen = new Date(year, month, day);
                        if(dateOpen.isValid()){
                            Profile user = new Profile(firstName, lastName);
                            Account accM = new MoneyMarket(user, amount, dateOpen);
                            boolean added = database.add(accM);
                            if (added) {
                                System.out.println("Account opened and added to the database.");
                            } else{
                                System.out.println("Account is already in the database.");
                            }
                        }else{
                            System.out.println(dateOpen.toString() + " is not a valid date!");
                        }

                    }

                    // D commands – deposit funds to an existing account
                    else if (command.equals("DC")) {
                        if(inputArr.length != 4){
                            throw new NumberFormatException();
                        }
                        System.out.println("Deposit to a Checking Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account depositC = new Checking(user, amount, empty, false);
                        database.deposit(depositC, amount);

                    } else if (command.equals("DS")) {
                        if(inputArr.length != 4){
                            throw new NumberFormatException();
                        }
                        System.out.println("Deposit to a Savings Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account depositS = new Savings(user, amount, empty, false);
                        database.deposit(depositS, amount);

                    } else if (command.equals("DM")) {
                        if(inputArr.length != 4){
                            throw new NumberFormatException();
                        }
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
                        if(inputArr.length != 4){
                            throw new NumberFormatException();
                        }
                        System.out.println("Withdraw from a Checking Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account withC = new Checking(user, amount, empty, false);
                        database.withdrawal(withC, amount);

                    } else if (command.equals("WS")) {
                        if(inputArr.length != 4){
                            throw new NumberFormatException();
                        }
                        System.out.println("Withdraw from a Savings Account");
                        String firstName = inputArr[1];
                        String lastName = inputArr[2];
                        double amount = Double.parseDouble(inputArr[3]);

                        Profile user = new Profile(firstName, lastName);
                        Date empty = new Date(0,0,0);
                        Account withS = new Savings(user, amount, empty, false);
                        database.withdrawal(withS, amount);

                    } else if (command.equals("WM")) {
                        if(inputArr.length != 4){
                            throw new NumberFormatException();
                        }
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
                        database.printByDateOpen();

                    } else if (command.equals("PN")) {
                        System.out.println("Same as PD but sort by last names in ascending order");
                        database.printByLastName();

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