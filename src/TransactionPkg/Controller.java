package TransactionPkg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {

    public TextField firstName;
    public TextField lastName;
    public TextField month;
    public TextField day;
    public TextField year;
    public TextField balance;

    @FXML
    ListView<String> list = new ListView<String>();

    @FXML
    ListView<String> list1 = new ListView<String>();

    @FXML
    ListView<String> list2 = new ListView<String>();

    public Button openAccount;
    public Button closeAccount;
    public Button clear;
    @FXML
    public Button closeApp1;
    @FXML
    public Button closeApp2;
    public Button input;

    public MenuButton pr;
    public MenuItem prinAcc;
    public MenuItem stateDate;
    public MenuItem stateLname;

    public TextField depositAmount;
    public TextField withdrawalAmount;

    public boolean directBool = false;
    public boolean isLoyalBool = false;

    @FXML
    RadioButton checking;
    @FXML
    RadioButton savings;
    @FXML
    RadioButton moneyMarket;
    @FXML
    CheckBox direct;
    @FXML
    CheckBox loyal;

    public Button withButton;
    public Button depoButton;

    AccountDatabase db = new AccountDatabase();
    ToggleGroup tg = new ToggleGroup();



   /* MenuItem accounts = new MenuItem("Accounts");
    MenuItem stateDate = new MenuItem("Statements by Date");
    MenuItem stateLast = new MenuItem("Statements by Last Name");
    */

    // Binds the list from the first tab to the second
    public void setList2() {
        list2.itemsProperty().bind(list.itemsProperty());
    }

    public void setList1() {
        list1.itemsProperty().bind(list.itemsProperty());
    }

    public void setPr() {
        //pr.getItems().addAll(accounts, stateDate, stateLast);
    }

    @FXML
    public void setCloseApp1(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    public void setCloseApp2(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    public void initialize() {
        setPr();
        setList1();
        setList2();
        openAccount.setDisable(true);
        direct.setDisable(true);
        loyal.setDisable(true);
        clear.setDisable(true);
        closeAccount.setDisable(true);
        depoButton.setDisable(true);
        withButton.setDisable(true);
    }

    /**
     * A void method that sets radio buttons into a group for a single selection
     */
    public void setTg() {
        checking.setToggleGroup(tg);
        savings.setToggleGroup(tg);
        moneyMarket.setToggleGroup(tg);
    }


    public void selectAccount() {
        setTg();

        if (checking.isSelected()) {
            tg.getSelectedToggle();
            loyal.setDisable(true);
            direct.setDisable(false);
            openAccount.setDisable(false);

        } else if (savings.isSelected()) {
            tg.getSelectedToggle();
            loyal.setDisable(false);
            direct.setDisable(true);
            openAccount.setDisable(false);

        } else if (moneyMarket.isSelected()) {
            tg.getSelectedToggle();
            direct.setDisable(true);
            loyal.setDisable(true);
            openAccount.setDisable(false);
        }

    }

    public void sayName(ActionEvent actionEvent) {
        String n = firstName.getText();
        String l = lastName.getText();

        //System.out.println("Got name: " + n + " " + l);
    }

    public boolean setDirectDepo(ActionEvent e) {
        //System.out.println("Direct Deposit");

        list.getSelectionModel().getSelectedItem();
        directBool = direct.isSelected();

        return directBool;
    }

    public void setDepo(ActionEvent e) throws Exception {
        try {

            DecimalFormat df = new DecimalFormat("#,##0.00");
            String account = list1.getSelectionModel().getSelectedItem().toString();
            String accCupdate = "";
            String[] accountInfo = account.split("\\*");
            String typeOfAcc = accountInfo[1];
            String[] fullName = accountInfo[2].split("\\s");
            String inAmount = accountInfo[3].replaceAll("[$,\\s]", "");
            double oldamount = Double.parseDouble(inAmount);

            if (typeOfAcc.equals("Checking")) {
                String damount = depositAmount.getText();
                damount = damount.replace(",", "");
                if(!checkWithDepoVal(damount)){
                    throw new NumberFormatException();
                }
                double amount = Double.parseDouble(damount);
                boolean isDirectB;
                if (accountInfo.length == 5) {
                    isDirectB = true;
                } else {
                    isDirectB = false;
                }
                String newamount = String.valueOf(df.format(amount + oldamount));
                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account depositC = new Checking(user, amount, empty, false);
                boolean depo = db.deposit(depositC, amount);
                //accCupdate = "*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4];
                if (depo) {
                    list.getItems().remove(account);
                    if (isDirectB) {
                        list.getItems().add("*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4] + "*direct deposit account*");
                    } else {
                        list.getItems().add("*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4]);
                    }
                } else {
                    displayAccNotExist();
                }
            } else if (typeOfAcc.equals("Savings")) {
                String damount = depositAmount.getText();
                damount = damount.replace(",", "");
                if(!checkWithDepoVal(damount)){
                    throw new NumberFormatException();
                }
                double amount = Double.parseDouble(damount);
                boolean isLoyalB;
                if (accountInfo.length == 6) {
                    isLoyalB = true;
                } else {
                    isLoyalB = false;
                }
                String newamount = String.valueOf(df.format(amount + oldamount));
                System.out.println(amount);
                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account depositS = new Savings(user, amount, empty, false);
                boolean depo = db.deposit(depositS, amount);
                //accCupdate = "*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4];
                if (depo) {
                    list.getItems().remove(account);
                    if (isLoyalB) {
                        list.getItems().add("*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4] + "*special Savings account*");
                    } else {
                        list.getItems().add("*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4]);
                    }
                } else {
                    System.out.println("Account does not exist");
                }
            } else if (typeOfAcc.equals("Money Market")) {
                String damount = depositAmount.getText();
                damount = damount.replace(",", "");
                if(!checkWithDepoVal(damount)){
                    throw new NumberFormatException();
                }
                double amount = Double.parseDouble(damount);

                String newamount = String.valueOf(df.format(amount + oldamount));
                System.out.println(amount);
                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account depositM = new MoneyMarket(user, amount, empty);
                boolean depo = db.deposit(depositM, amount);
                accCupdate = "*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4];
                list.getItems().remove(account);
                if (depo) {
                    list.getItems().add(accCupdate);
                    System.out.println(amount + " deposited to account");
                } else {
                    System.out.println("Account does not exist");
                }
            }
            depositAmount.clear();
        } catch (NumberFormatException | NullPointerException exception) {
            // This try-catch is if someone tries to deposit to an account without selecting an account
            displayInvalidWithdrawDepo();
        }
    }

    public void setWithdrawal() {
        try {
            // set up for withdrawals
            String account = list1.getSelectionModel().getSelectedItem().toString();

            String wamount = withdrawalAmount.getText();
            wamount = wamount.replace(",", "");
            if(!checkWithDepoVal(wamount)){
                throw new NumberFormatException();
            }

            String[] accPara = account.split("\\*");
            String[] fullName = accPara[2].split("\\s");
            String fName = fullName[0];
            String lName = fullName[1];
            String parseAmount = accPara[3];
            StringBuilder oldamount = new StringBuilder();

            double amount = Double.parseDouble(wamount);
            DecimalFormat df = new DecimalFormat("#,##0.00");
            for (int i = 0; i < parseAmount.length(); i++) {
                if (Character.isDigit(parseAmount.charAt(i)) || parseAmount.charAt(i) == '.') {
                    oldamount.append(parseAmount.charAt(i));
                }
            }

            // split into Money Market, Checking, and Savings
            if (accPara[1].equals("Money Market")) {
                double newAmount = Double.parseDouble(String.valueOf(oldamount)) - amount;
                df.format(newAmount);
                Profile user = new Profile(fName, lName);
                Date empty = new Date(0, 0, 0);
                Account withM = new MoneyMarket(user, amount, empty);
                int with = db.withdrawal(withM, amount);
                if (with == 0) {
                    list.getItems().remove(account);
                    list.getItems().add("*" + accPara[1] + "*" + accPara[2] + "*" + " $" + newAmount + "*" + accPara[4]);
                } else if (with == 1) {
                    System.out.println("Insufficient funds");
                } else {
                    System.out.println("Account does not exist.");
                }
            } else if (accPara[1].equals("Checking")) {
                double newAmount = Double.parseDouble(String.valueOf(oldamount)) - amount;
                boolean isDirectB;
                if (accPara.length == 5) {
                    isDirectB = true;
                } else {
                    isDirectB = false;
                }
                Profile user = new Profile(fName, lName);
                Date empty = new Date(0, 0, 0);
                Account withC = new Checking(user, amount, empty, isDirectB);
                int with = db.withdrawal(withC, amount);
                if (with == 0) {
                    list.getItems().remove(account);
                    if (isDirectB) {
                        list.getItems().add("*" + accPara[1] + "*" + accPara[2] + "*" + " $" + newAmount + "*" + accPara[4] + "*direct deposit account*");
                    } else {
                        list.getItems().add("*" + accPara[1] + "*" + accPara[2] + "*" + " $" + newAmount + "*" + accPara[4]);
                    }
                } else if (with == 1) {
                    System.out.println("Insufficient funds");
                } else {
                    System.out.println("Account does not exist.");
                }
            } else if (accPara[1].equals("Savings")) {
                double newAmount = Double.parseDouble(String.valueOf(oldamount)) - amount;
                boolean isLoyalB;
                if (accPara.length == 6) {
                    isLoyalB = true;
                } else {
                    isLoyalB = false;
                }
                Profile user = new Profile(fName, lName);
                Date empty = new Date(0, 0, 0);
                Account withC = new Savings(user, amount, empty, isLoyalB);
                int with = db.withdrawal(withC, amount);
                if (with == 0) {
                    list.getItems().remove(account);
                    if (isLoyalB) {
                        list.getItems().add("*" + accPara[1] + "*" + accPara[2] + "*" + " $" + newAmount + "*" + accPara[4] + "*special Savings account*");
                    } else {
                        list.getItems().add("*" + accPara[1] + "*" + accPara[2] + "*" + " $" + newAmount + "*" + accPara[4]);
                    }
                } else if (with == 1) {
                    System.out.println("Insufficient funds");
                } else {
                    System.out.println("Account does not exist.");
                }
            }

            withdrawalAmount.clear();
        }catch (NumberFormatException | NullPointerException exception) {
            // This try-catch is if someone tries to deposit to an account without selecting an account
            displayInvalidWithdrawDepo();
        }

    }

    public boolean setIsLoyal(ActionEvent e) {
        isLoyalBool = loyal.isSelected();
        return isLoyalBool;
    }

    public boolean checkString(String str) {
        boolean res = true;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                res = false;
                break;
            }
        }

        return res;
    }

    public boolean checkBalance(String str) {
        boolean res = true;
        str = str.substring(1);
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                res = false;
                break;
            }
        }
        return res;
    }

    public boolean checkWithDepoVal(String str){
        boolean res = true;
        for(int i = 0; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                res = false;
                break;
            }
        }
        return res;
    }

    public boolean checkDate(String year, String month, String day){
        boolean res = true;

        if (year.matches("^\\d+\\.\\d+") || month.matches("^\\d+\\.\\d+") || day.matches("^\\d+\\.\\d+")) {
            res = false;
        }

        return res;
    }

    public void setOpenAccount() {
        try {
            if (checking.isSelected()) {
                if (!checkBalance(balance.getText()) || firstName.getText().equals("") || lastName.getText().equals("") || !(checkString(firstName.getText()) && checkString(lastName.getText()))) {
                    throw new InputMismatchException();
                }
                if (!checkDate(year.getText(), month.getText(), day.getText())) {
                    throw new NumberFormatException();
                }
                Date dateOpen = new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
                if (dateOpen.isValid()) {
                    Profile user = new Profile(firstName.getText(), lastName.getText());
                    Account accC = new Checking(user, Double.parseDouble(balance.getText()), dateOpen, directBool);
                    boolean added = db.add(accC);
                    if (added) {
                        System.out.println("Account opened and added to the database.");
                        list.getItems().add(accC.toString());
                        //closeAccount.setDisable(false);
                        //closeAccount.disableProperty().bind(list.getSelectionModel().selectedItemProperty().isNull());
                    } else {
                        System.out.println("Account is already in the database.");
                    }
                } else {
                    System.out.println(dateOpen.toString() + " is not a valid date!");
                    display(dateOpen);
                }

            } else if (savings.isSelected()) {
                //System.out.println("Opening Account for " + firstName.getText() + " " + lastName.getText());
                //System.out.println("Date " + month.getText() + " " + day.getText() + " " + year.getText());
                //System.out.println("Balance " + balance.getText());
                if (!checkBalance(balance.getText()) || firstName.getText().equals("") || lastName.getText().equals("") || !(checkString(firstName.getText()) && checkString(lastName.getText()))) {
                    throw new InputMismatchException();
                }
                if (!checkDate(year.getText(), month.getText(), day.getText())) {
                    throw new NumberFormatException();
                }
                Date dateOpen = new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
                if (dateOpen.isValid()) {
                    Profile user = new Profile(firstName.getText(), lastName.getText());
                    Account accS = new Savings(user, Double.parseDouble(balance.getText()), dateOpen, isLoyalBool);
                    boolean added = db.add(accS);
                    if (added) {
                        list.getItems().add(accS.toString());
                        System.out.println("Account opened and added to the database.");
                    } else {
                        System.out.println("Account is already in the database.");
                    }
                } else {
                    System.out.println(dateOpen.toString() + " is not a valid date!");
                    display(dateOpen);
                }

            } else if (moneyMarket.isSelected()) { // money Market is selected
                //System.out.println("Opening Account for " + firstName.getText() + " " + lastName.getText());
                //System.out.println("Date " + month.getText() + " " + day.getText() + " " + year.getText());
                //System.out.println("Balance " + balance.getText());
                if (!checkBalance(balance.getText()) || firstName.getText().equals("") || lastName.getText().equals("") || !(checkString(firstName.getText()) && checkString(lastName.getText()))) {
                    throw new InputMismatchException();
                }
                if (!checkDate(year.getText(), month.getText(), day.getText())) {
                    throw new NumberFormatException();
                }
                Date dateOpen = new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
                if (dateOpen.isValid()) {
                    Profile user = new Profile(firstName.getText(), lastName.getText());
                    Account accM = new MoneyMarket(user, Double.parseDouble(balance.getText()), dateOpen);
                    boolean added = db.add(accM);
                    if (added) {
                        list.getItems().add(accM.toString());
                        System.out.println("Account opened and added to the database.");
                        //closeAccount.setDisable(false);
                        //closeAccount.disableProperty().bind(list.getSelectionModel().selectedItemProperty().isNull());
                    } else {
                        System.out.println(dateOpen.toString() + " is not a valid date!");
                        display(dateOpen);
                    }
                }
            }
        }catch (InputMismatchException e) {
            displayInvalidOpenFields();
        } catch (NumberFormatException e) {
            displayInvalidDateFields();
        }

        // reset fields
        direct.setSelected(false);
        directBool = false;
        loyal.setSelected(false);
        isLoyalBool = false;
        firstName.clear();
        lastName.clear();
        month.clear();
        day.clear();
        year.clear();
        balance.clear();
        checking.setSelected(false);
        savings.setSelected(false);
        moneyMarket.setSelected(false);
        closeAccount.setDisable(false);
        depoButton.setDisable(false);
        withButton.setDisable(false);
    }



    public void setCloseAccount(ActionEvent e) {
        // handle account info
        try {
            String account = list.getSelectionModel().getSelectedItem().toString();

            System.out.println("Account Selected to Remove: " + account);
            String[] accountInfo = account.split("\\*");
            String typeOfAcc = accountInfo[1];
            String[] fullName = accountInfo[2].split("\\s");


            if (typeOfAcc.equals("Checking")) {
                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account rC = new Checking(user, 0, empty, false);
                boolean close = db.remove(rC);
                if (close) {
                    list.getItems().remove(account);
                    System.out.println("Account closed and removed from database.");
                } else {
                    System.out.println("Account does not exist.");
                }
            } else if (typeOfAcc.equals("Savings")) {
                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account rS = new Savings(user, 0, empty, false);
                boolean close = db.remove(rS);
                if (close) {
                    list.getItems().remove(account);
                    System.out.println("Account closed and removed from database.");
                } else {
                    System.out.println("Account does not exist.");
                }
            } else if (typeOfAcc.equals("Money Market")) {
                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account rM = new MoneyMarket(user, 0, empty);
                boolean close = db.remove(rM);
                if (close) {
                    list.getItems().remove(account);
                    System.out.println("Account closed and removed from database.");
                } else {
                    System.out.println("Account does not exist.");
                }

            }

            if (list.getItems().isEmpty()) {
                closeAccount.setDisable(true);
                depoButton.setDisable(true);
            }
        } catch (NullPointerException q) {
            displayClose();
        }
    }

    public void setClear() {
        list.getItems().removeAll();
    }

    public void outputByDate() {
        try {
            File myObj = new File("outputDate.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            // write to file
            PrintWriter writer = new PrintWriter("outputDate.txt", StandardCharsets.UTF_8);
            writer.println("--Printing statements by Date Open --");
            String[] resArr = db.printByDateOpen();
            for (String s : resArr) {
                writer.println(s);
            }
            writer.println("--end of printing--");
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void outputAcc() {
        try {
            File myObj = new File("outputAccount.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            // write to file
            PrintWriter writer = new PrintWriter("outputAccount.txt", StandardCharsets.UTF_8);
            writer.println("--Listing accounts in the database--");
            String[] resArr = db.printAccounts();
            for (String s : resArr) {
                writer.println(s);
            }
            writer.println("--end of printing--");
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void outputLastName() {
        try {
            File myObj = new File("outputLastName.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            // write to file
            PrintWriter writer = new PrintWriter("outputLastName.txt", StandardCharsets.UTF_8);
            writer.println("--Printing statements by last name--");
            String[] resArr = db.printByLastName();
            for (String s : resArr) {
                writer.println(s);
            }
            writer.println("--end of printing--");
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void importFile() throws FileNotFoundException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
try{
        Scanner sc = new Scanner(sourceFile);
        while (sc.hasNext()) {

            String s = sc.nextLine();

            String[] inputArr = s.split(",");
            try {
                if (inputArr[0].equals("C")) {
                    if (inputArr.length != 6) {
                        throw new NumberFormatException();
                    }
                    String bool = inputArr[5];
                    if (bool.equals("flash")) {
                        throw new NumberFormatException();
                    }
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
                    if (dateOpen.isValid()) {
                        boolean directDeposit = Boolean.parseBoolean(inputArr[5]);
                        Profile user = new Profile(firstName, lastName);
                        Account accC = new Checking(user, amount, dateOpen, directDeposit);
                        boolean added = db.add(accC);
                        if (added) {
                            System.out.println(accC);
                            list2.getItems().add(accC.toString());
                            System.out.println("Account opened and added to the database.");
                        } else {
                            System.out.println("Account is already in the database.");
                        }
                    } else {
                        System.out.println(dateOpen.toString() + " is not a valid date!");
                    }

                } else if (inputArr[0].equals("S")) {
                    if (inputArr.length != 6) {
                        throw new NumberFormatException();
                    }
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
                    if (dateOpen.isValid()) {
                        boolean isLoyal = Boolean.parseBoolean(inputArr[5]);
                        Profile user = new Profile(firstName, lastName);
                        Account accS = new Savings(user, amount, dateOpen, isLoyal);
                        boolean added = db.add(accS);
                        if (added) {
                            list2.getItems().add(accS.toString());
                            System.out.println("Account opened and added to the database.");
                        } else {
                            System.out.println("Account is already in the database.");
                        }
                    } else {
                        System.out.println(dateOpen.toString() + " is not a valid date!");
                    }

                } else if (inputArr[0].equals("M")) {
                    if (inputArr.length != 6) {
                        throw new NumberFormatException();
                    }
                    String firstName = inputArr[1];
                    String lastName = inputArr[2];
                    double amount = Double.parseDouble(inputArr[3]);
                    String date = inputArr[4];
                    String[] splitDate = date.split("/");
                    int month = Integer.parseInt(splitDate[0]);
                    int day = Integer.parseInt(splitDate[1]);
                    int year = Integer.parseInt(splitDate[2]);
                    int withdrawals = Integer.parseInt(inputArr[5]);
                    // check if date is valid
                    Date dateOpen = new Date(year, month, day);
                    if (dateOpen.isValid()) {
                        Profile user = new Profile(firstName, lastName);
                        Account accM = new MoneyMarket(user, amount, dateOpen);
                        boolean added = db.add(accM);

                        // null withdrawals to make the correct number of withdrawals be associated to account
                        for (int i = 0; i < withdrawals; i++) {
                            db.withdrawal(accM, 0);
                        }

                        if (added) {
                            list2.getItems().add(accM.toString());
                            System.out.println("Account opened and added to the database.");
                        } else {
                            System.out.println("Account is already in the database.");
                        }
                    } else {
                        System.out.println(dateOpen.toString() + " is not a valid date!");
                    }
                }

                if(!list.getItems().isEmpty()) {
                    closeAccount.setDisable(false);
                    withButton.setDisable(false);
                    depoButton.setDisable(false);
                }

            } catch (NumberFormatException e) {
                // e.printStackTrace();
            }
        }
}catch(NullPointerException e){
                displayImportFile();
            }
        }




    // make popup window for not importing file
    public static void display(Date date) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText(date.toString() + " is not a valid date. Please enter the correct date");
        errorAlert.showAndWait();

    }

    public static void displayClose() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Account not selected");
        errorAlert.setContentText("Please select an account!");
        errorAlert.showAndWait();
    }

    public static void displayInvalidOpenFields() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Invalid Fields");
        errorAlert.setContentText("Some fields may have been left blank or inputted incorrectly, please fill them.");
        errorAlert.showAndWait();
    }

    public static void displayInvalidDateFields() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText("Not a valid date! Please enter the date correctly.");
        errorAlert.showAndWait();

    }

    public static void displayImportFile(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Invalid File");
        errorAlert.setContentText("Correct file couldn't be imported");
        errorAlert.showAndWait();
    }

    public static void displayInvalidWithdrawDepo(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText("Please enter a correct monetary value!");
        errorAlert.showAndWait();
    }

    public static void displayAccNotExist(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Account Not Found!");
        errorAlert.setContentText("Account Does Not Exist! Try Again.");
        errorAlert.showAndWait();
    }



}



/*  ERROR CHECKLIST
    DONE    1) Balance Text Field: needs to only accept numbers, popup if otherwise
    DONE    2) First Name, Last Name, only characters
    DONE    3) Date can only accept integers
            4) Low Priority: Uncheck boxes if toggle is changed
    DONE    5) Withdrawal try-catch * NumberFormatException
    DONE    6) Close, deposit, withdrawal
    DONE    7) allow for decimals for withdrawl and deposit
 */