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

/**
 * Handles the button and actions related to any text fields, buttons, etc.
 * Calls methods from Account Database to handle User Inputs
 */
public class Controller {

    public TextField firstName;
    public TextField lastName;
    public TextField month;
    public TextField day;
    public TextField year;
    public TextField balance;

    @FXML
    ListView<String> list = new ListView<>();

    @FXML
    ListView<String> list1 = new ListView<>();

    @FXML
    ListView<String> list2 = new ListView<>();

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

    /**
     * Binds the list from the first tab to the second
     */
    public void setList2() {
        list2.itemsProperty().bind(list.itemsProperty());
    }

    /**
     * Binds list from 0th list to 1st list
     */
    public void setList1() {
        list1.itemsProperty().bind(list.itemsProperty());
    }

    /**
     * unneeded method
     */
    public void setPr() { }

    /**
     * When clicked, closes the app
     * @param event mouse click on Close app
     */
    @FXML
    public void setCloseApp1(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    /**
     * When clicked, closes the app
     * @param event mouse click on Close app
     */
    @FXML
    public void setCloseApp2(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    /**
     * Initialize the UI when it is created
     */
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

    /**
     * When account is selected certain buttons will gray out while others wont
     */
    public void selectAccount() {
        setTg();

        if (checking.isSelected()) {
            tg.getSelectedToggle();
            loyal.setDisable(true);
            direct.setDisable(false);
            openAccount.setDisable(false);
            loyal.setSelected(false);

        } else if (savings.isSelected()) {
            tg.getSelectedToggle();
            loyal.setDisable(false);
            direct.setDisable(true);
            openAccount.setDisable(false);
            direct.setSelected(false);

        } else if (moneyMarket.isSelected()) {
            tg.getSelectedToggle();
            direct.setDisable(true);
            loyal.setDisable(true);
            openAccount.setDisable(false);
            direct.setSelected(false);
            loyal.setSelected(false);
        }

    }

    /**
     * Gets full name from text fields after button is clicked
     * @param actionEvent button click
     */
    public void sayName(ActionEvent actionEvent) {
        String n = firstName.getText();
        String l = lastName.getText();
    }

    /**
     * Helper method = Set up direct deposit
     * @param e button click
     * @return directBool if it is a direct deposit account
     */
    public boolean setDirectDepo(ActionEvent e) {
        list.getSelectionModel().getSelectedItem();
        directBool = direct.isSelected();

        return directBool;
    }

    /**
     * deposit money into an existing account, has try catch for UI error
     * @param e button click (Deposit)
     * @throws Exception when someone deposits without selecting account
     */
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
                if (accountInfo.length == 6) {
                    isDirectB = true;
                } else {
                    isDirectB = false;
                }
                String newamount = String.valueOf(df.format(amount + oldamount));
                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account depositC = new Checking(user, amount, empty, false);
                boolean depo = db.deposit(depositC, amount);
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

                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account depositS = new Savings(user, amount, empty, false);
                boolean depo = db.deposit(depositS, amount);

                if (depo) {
                    list.getItems().remove(account);
                    if (isLoyalB) {
                        list.getItems().add("*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4] + "*special Savings account*");
                    } else {
                        list.getItems().add("*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + (newamount) + "*" + accountInfo[4]);
                    }
                } else {
                    displayAccNotExist();
                }
            } else if (typeOfAcc.equals("Money Market")) {
                String damount = depositAmount.getText();
                damount = damount.replace(",", "");
                if(!checkWithDepoVal(damount)){
                    throw new NumberFormatException();
                }
                double amount = Double.parseDouble(damount);

                String newamount = String.valueOf(df.format(amount + oldamount));
                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account depositM = new MoneyMarket(user, amount, empty);
                boolean depo = db.deposit(depositM, amount);
                accCupdate = "*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4];
                list.getItems().remove(account);
                if (depo) {
                    list.getItems().add(accCupdate);
                } else {
                    displayAccNotExist();
                }
            }
            depositAmount.clear();
        } catch (NumberFormatException exception) {
            // This try-catch is if someone tries to deposit to an account without selecting an account
            displayInvalidWithdrawDepo();
            depositAmount.clear();
        }catch(NullPointerException exception){
            displayAccNotSelected();
        }
    }

    /**
     * Withdraw money from account after User selects it
     */
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

            double newAmount = Double.parseDouble(String.valueOf(oldamount)) - amount;
            // split into Money Market, Checking, and Savings
            if (accPara[1].equals("Money Market")) {

                Profile user = new Profile(fName, lName);
                Date empty = new Date(0, 0, 0);
                Account withM = new MoneyMarket(user, amount, empty);
                int with = db.withdrawal(withM, amount);
                if (with == 0) {
                    list.getItems().remove(account);
                    list.getItems().add("*" + accPara[1] + "*" + accPara[2] + "*" + " $" + df.format(newAmount) + "*" + accPara[4]);
                } else if (with == 1) {
                    displayInsufficientFunds();
                } else {
                    displayAccNotExist();
                }
            } else if (accPara[1].equals("Checking")) {
                boolean isDirectB;
                if (accPara.length == 5) {
                    isDirectB = true;
                } else {
                    isDirectB = false;
                }
                Profile user = new Profile(fName, lName);
                Date empty = new Date(0, 0, 0);
                Account withC = new Checking(user, amount, empty, isDirectB);
                df.format(newAmount);
                int with = db.withdrawal(withC, amount);
                if (with == 0) {
                    list.getItems().remove(account);
                    if (isDirectB) {
                        list.getItems().add("*" + accPara[1] + "*" + accPara[2] + "*" + " $" + df.format(newAmount) + "*" + accPara[4] + "*direct deposit account*");
                    } else {
                        list.getItems().add("*" + accPara[1] + "*" + accPara[2] + "*" + " $" + df.format(newAmount) + "*" + accPara[4]);
                    }
                } else if (with == 1) {
                    displayInsufficientFunds();
                } else {
                    displayAccNotExist();
                }
            } else if (accPara[1].equals("Savings")) {
                boolean isLoyalB;
                if (accPara.length == 6) {
                    isLoyalB = true;
                } else {
                    isLoyalB = false;
                }
                Profile user = new Profile(fName, lName);
                Date empty = new Date(0, 0, 0);
                Account withC = new Savings(user, amount, empty, isLoyalB);
                df.format(newAmount);
                int with = db.withdrawal(withC, amount);
                if (with == 0) {
                    list.getItems().remove(account);
                    if (isLoyalB) {
                        list.getItems().add("*" + accPara[1] + "*" + accPara[2] + "*" + " $" + df.format(newAmount) + "*" + accPara[4] + "*special Savings account*");
                    } else {
                        list.getItems().add("*" + accPara[1] + "*" + accPara[2] + "*" + " $" + df.format(newAmount) + "*" + accPara[4]);
                    }
                } else if (with == 1) {
                    displayInsufficientFunds();

                } else {
                    displayAccNotExist();
                }
            }

            withdrawalAmount.clear();
        }catch (NumberFormatException exception) {
            // This try-catch is if someone tries to deposit to an account without selecting an account
            displayInvalidWithdrawDepo();
            withdrawalAmount.clear();
        }catch ( NullPointerException exception){
            displayAccNotSelected();
        }

    }

    /**
     * Returns boolean if the savings account is loyal or not
     * @param e button click
     * @return isloyalBool if true or false based on the checkbox click
     */
    public boolean setIsLoyal(ActionEvent e) {
        isLoyalBool = loyal.isSelected();
        return isLoyalBool;
    }

    /**
     * Check String for names and such
     * @param str string that needs to be checked
     * @return res if it is a valid string or not
     */
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

    /**
     * Check if balance has all numbers
     * @param str string to be checked
     * @return res if it is valid or not
     */
    public boolean checkBalance(String str) {
        if(str.equals("")){
            return false;
        }
        boolean res = true;
        str = str.substring(1);
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '.'){
                // do nothing
            }else if (!Character.isDigit(str.charAt(i))) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * Check values from Deposit and Withdrawal
     * @param str string to be checked
     * @return res if they are valid or not
     */
    public boolean checkWithDepoVal(String str){
        boolean res = true;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '.'){
                // do nothing
            }else if(!Character.isDigit(str.charAt(i))){
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * Checks if data is valid (User Input)
     * @param year string to be checked
     * @param month stiring to be checked
     * @param day string to be checked
     * @return res if the date is valid
     */
    public boolean checkDate(String year, String month, String day){
        boolean res = true;
        if(year.equals("") || month.equals("") || day.equals("")){
            return false;
        }
        if (year.matches("^\\d+\\.\\d+") || month.matches("^\\d+\\.\\d+") || day.matches("^\\d+\\.\\d+")) {
            res = false;
        }

        return res;
    }

    /**
     * Opens an account based on which account type is selected and User Inputs
     */
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
                        list.getItems().add(accC.toString());
                    } else {
                        displayAccountAlready();
                    }
                } else {
                    display(dateOpen);
                }

            } else if (savings.isSelected()) {
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
                    } else {
                        displayAccountAlready();
                    }
                } else {
                    display(dateOpen);
                }

            } else if (moneyMarket.isSelected()) { // money Market is selected

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
                    } else {
                        displayAccountAlready();
                    }
                }else {
                    display(dateOpen);
                }
            }else{
                displaySelection();
                openAccount.setDisable(true);
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
        direct.setDisable(true);
        loyal.setDisable(true);

        if (list.getItems().isEmpty()) {
            closeAccount.setDisable(true);
            depoButton.setDisable(true);
            withButton.setDisable(true);
            clear.setDisable(true);
        }else {
            closeAccount.setDisable(false);
            depoButton.setDisable(false);
            withButton.setDisable(false);
            clear.setDisable(false);
        }
    }

    /**
     * Closes account based on User Input based on button click
     * @param e button click
     */
    public void setCloseAccount(ActionEvent e) {
        // handle account info
        try {
            String account = list.getSelectionModel().getSelectedItem().toString();

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
                } else {
                    //System.out.println("Account does not exist.");
                    displayAccNotExist();

                }
            } else if (typeOfAcc.equals("Savings")) {
                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account rS = new Savings(user, 0, empty, false);
                boolean close = db.remove(rS);
                if (close) {
                    list.getItems().remove(account);
                } else {
                    //System.out.println("Account does not exist.");
                    displayAccNotExist();

                }
            } else if (typeOfAcc.equals("Money Market")) {
                Profile user = new Profile(fullName[0], fullName[1]);
                Date empty = new Date(0, 0, 0);
                Account rM = new MoneyMarket(user, 0, empty);
                boolean close = db.remove(rM);
                if (close) {
                    list.getItems().remove(account);
                } else {
                    //System.out.println("Account does not exist.");
                    displayAccNotExist();

                }

            }

            if (list.getItems().isEmpty()) {
                closeAccount.setDisable(true);
                depoButton.setDisable(true);
                withButton.setDisable(true);
                clear.setDisable(true);
            }
        } catch (NullPointerException q) {
            displayClose();
            if (list.getItems().isEmpty()) {
                closeAccount.setDisable(true);
                depoButton.setDisable(true);
                withButton.setDisable(true);
                clear.setDisable(false);
            }
        }
    }

    /**
     * Clear the account and clear the list (db)
     */
    public void setClear() {
        displayClear();
        list.getItems().clear();
        db.listClear();

        if (list.getItems().isEmpty()) {
            closeAccount.setDisable(true);
            depoButton.setDisable(true);
            withButton.setDisable(true);
            clear.setDisable(true);
        }else {
            closeAccount.setDisable(false);
            depoButton.setDisable(false);
            withButton.setDisable(false);
            clear.setDisable(false);
        }
    }

    /**
     * Clear the display on the UI
     */
    public static void displayClear(){
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setHeaderText("Are you sure?");
        errorAlert.setContentText("This will remove all accounts in the database.");
        errorAlert.showAndWait();

    }

    /**
     * Export data by date opened
     */
    public void outputByDate() {
        try {
            File myObj = new File("outputDate.txt");
            // write to file
            PrintWriter writer = new PrintWriter("outputDate.txt", StandardCharsets.UTF_8);
            writer.println("--Printing statements by Date Open --");
            String[] resArr = db.printByDateOpen();
            for (String s : resArr) {
                writer.println(s);
            }
            writer.println("--end of printing--");
            writer.close();
            displayFileCreated();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Export account by Listing all accounts
     */
    public void outputAcc() {
        try {
            File myObj = new File("outputAccount.txt");
            // write to file
            PrintWriter writer = new PrintWriter("outputAccount.txt", StandardCharsets.UTF_8);
            writer.println("--Listing accounts in the database--");
            String[] resArr = db.printAccounts();
            for (String s : resArr) {
                writer.println(s);
            }
            writer.println("--end of printing--");
            writer.close();
            displayFileCreated();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Export accounts by last name
     */
    public void outputLastName() {
        try {
            File myObj = new File("outputLastName.txt");

            // write to file
            PrintWriter writer = new PrintWriter("outputLastName.txt", StandardCharsets.UTF_8);
            writer.println("--Printing statements by last name--");
            String[] resArr = db.printByLastName();
            for (String s : resArr) {
                writer.println(s);
            }
            writer.println("--end of printing--");
            writer.close();
            displayFileCreated();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Import file when it is selected by user
     * @throws FileNotFoundException if file not found then the popup appears
     */
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
                            list2.getItems().add(accC.toString());
                        } else {
                            //System.out.println("Account is already in the database.");
                            displayAccountAlready();
                        }
                    } else {
                        //System.out.println(dateOpen.toString() + " is not a valid date!");
                        displayInvalidDateFields();
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
                        } else {
                            //System.out.println("Account is already in the database.");
                            displayAccountAlready();
                        }
                    } else {
                        //System.out.println(dateOpen.toString() + " is not a valid date!");
                        displayInvalidDateFields();

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
                        } else {
                            //System.out.println("Account is already in the database.");
                            displayAccountAlready();
                        }
                    } else {
                        //System.out.println(dateOpen.toString() + " is not a valid date!");
                        displayInvalidDateFields();
                    }
                }

                if(!list.getItems().isEmpty()) {
                    closeAccount.setDisable(false);
                    withButton.setDisable(false);
                    depoButton.setDisable(false);
                    clear.setDisable(false);
                }

            } catch (NumberFormatException e) {
                // e.printStackTrace();
                displayInvalidOpenFields();
            }
        }
            }catch(NullPointerException e){
                displayImportFile();
            }
        }

    /**
     * Make popup for not inputting valid date
      * @param date date inputted by the user
     */
    public static void display(Date date) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText(date.toString() + " is not a valid date. Please enter the correct date");
        errorAlert.showAndWait();

    }

    /**
     * Popup if account isn't selected
     */
    public static void displayClose() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Account not selected");
        errorAlert.setContentText("Please select an account!");
        errorAlert.showAndWait();
    }

    /**
     * Popup if some fields are left blank
     */
    public static void displayInvalidOpenFields() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Invalid Fields");
        errorAlert.setContentText("Some fields may have been left blank or inputted incorrectly, please fill them.");
        errorAlert.showAndWait();
    }

    /**
     * Popup if the date isn't valid
     */
    public static void displayInvalidDateFields() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText("Not a valid date! Please enter the date correctly.");
        errorAlert.showAndWait();

    }

    /**
     * Pop up if correct file isn't imported
     */
    public static void displayImportFile(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Invalid File");
        errorAlert.setContentText("Correct file couldn't be imported");
        errorAlert.showAndWait();
    }

    /**
     * PopUp if the wrong value is inputted
     */
    public static void displayInvalidWithdrawDepo(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText("Please enter a correct monetary value!");
        errorAlert.showAndWait();
    }

    /**
     * Popup for account does not exist
     */
    public static void displayAccNotExist(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Account Not Found!");
        errorAlert.setContentText("Account Does Not Exist! Try Again.");
        errorAlert.showAndWait();
    }

    /**
     * Popup for no account type selected.
     */
    public static void displaySelection(){
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setHeaderText("Warning");
        errorAlert.setContentText("No Account Type Selected!");
        errorAlert.showAndWait();
    }

    /**
     * Popup for insufficient funds
     */
    public static void displayInsufficientFunds(){
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setHeaderText("Warning");
        errorAlert.setContentText("Insufficient Funds!");
        errorAlert.showAndWait();
    }

    /**
     * Popup for adding new accounts to the database
     */
    public static void displayAccountAlready(){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setHeaderText("Account Already Exists!");
        errorAlert.setContentText("You Can Only Add New Accounts To The Database");
        errorAlert.showAndWait();
    }

    public static void displayFileCreated(){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setHeaderText("File status");
        errorAlert.setContentText("File has been created!");
        errorAlert.showAndWait();
    }

    public static void displayAccNotSelected(){
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setHeaderText("Account status");
        errorAlert.setContentText("Please select an account!");
        errorAlert.showAndWait();
    }

}
