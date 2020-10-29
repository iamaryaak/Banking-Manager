package TransactionPkg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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
    public Button input;
    public MenuButton pr;
    public MenuItem prinAcc;
    public MenuItem stateDate;
    public MenuItem stateLname;
    public TextField depositAmount;

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


    public void initialize() {
        setPr();
        setList1();
        setList2();
        openAccount.setDisable(true);
        direct.setDisable(true);
        loyal.setDisable(true);
        clear.setDisable(true);
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

    public void setDepo(ActionEvent e) throws Exception{
    try{
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String account = list1.getSelectionModel().getSelectedItem().toString();
        String accCupdate = "";
        System.out.println("Account Selected to Deposit: " + account);
        String[] accountInfo = account.split("\\*");
        String typeOfAcc = accountInfo[1];
        String[] fullName = accountInfo[2].split("\\s");
        String inAmount = accountInfo[3].replaceAll("[$,\\s]", "");
        double oldamount = Double.parseDouble(inAmount);

        if (typeOfAcc.equals("Checking")){
            String damount = depositAmount.getText();
            double amount = Double.parseDouble(damount);

            String newamount = String.valueOf(df.format(amount + oldamount));
            System.out.println(amount);
            Profile user = new Profile(fullName[0], fullName[1]);
            Date empty = new Date(0,0,0);
            Account depositC = new Checking(user, amount, empty, false);
            boolean depo = db.deposit(depositC, amount);
            accCupdate = "*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4];
            list.getItems().remove(account);
            if(depo){
                list.getItems().add(accCupdate);
                System.out.println(amount + " deposited to account");
            }else{
                System.out.println("Account does not exist");
            }
        }
        else if(typeOfAcc.equals("Savings")){
            String damount = depositAmount.getText();
            double amount = Double.parseDouble(damount);

            String newamount = String.valueOf(df.format(amount + oldamount));
            System.out.println(amount);
            Profile user = new Profile(fullName[0], fullName[1]);
            Date empty = new Date(0,0,0);
            Account depositS = new Savings(user, amount, empty, false);
            boolean depo = db.deposit(depositS, amount);
            accCupdate = "*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4];
            list.getItems().remove(account);
            if(depo){
                list.getItems().add(accCupdate);
                System.out.println(amount + " deposited to account");
            }else{
                System.out.println("Account does not exist");
            }
        }
        else if(typeOfAcc.equals("Money Market")){
            String damount = depositAmount.getText();
            double amount = Double.parseDouble(damount);

            String newamount = String.valueOf(df.format(amount + oldamount));
            System.out.println(amount);
            Profile user = new Profile(fullName[0], fullName[1]);
            Date empty = new Date(0,0,0);
            Account depositM = new MoneyMarket(user, amount, empty);
            boolean depo = db.deposit(depositM, amount);
            accCupdate = "*" + accountInfo[1] + "*" + accountInfo[2] + "*" + " $" + newamount + "*" + accountInfo[4];
            list.getItems().remove(account);
            if(depo){
                list.getItems().add(accCupdate);
                System.out.println(amount + " deposited to account");
            }else{
                System.out.println("Account does not exist");
            }
        }

    } catch (Exception exception){
        // This try-catch is if someone tries to deposit to an account without selectig an account
        System.out.println("No account selected");
    }}

    public boolean setIsLoyal(ActionEvent e) {
        //System.out.println("Is Loyal");
        isLoyalBool = loyal.isSelected();
        return isLoyalBool;
    }

    public void setOpenAccount(){

        if (checking.isSelected()) {
            //System.out.println("Opening Account for " + firstName.getText() + " " + lastName.getText());
            //System.out.println("Date " + month.getText() + " " + day.getText() + " " + year.getText());
            //System.out.println("Balance " + balance.getText());

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
            }

        } else if (savings.isSelected()) {
            //System.out.println("Opening Account for " + firstName.getText() + " " + lastName.getText());
            //System.out.println("Date " + month.getText() + " " + day.getText() + " " + year.getText());
            //System.out.println("Balance " + balance.getText());

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
            }

        } else if (moneyMarket.isSelected()) { // money Market is selected
            //System.out.println("Opening Account for " + firstName.getText() + " " + lastName.getText());
            //System.out.println("Date " + month.getText() + " " + day.getText() + " " + year.getText());
            //System.out.println("Balance " + balance.getText());

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
                    System.out.println("Account is already in the database.");
                }
            } else {
                System.out.println(dateOpen.toString() + " is not a valid date!");
            }
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
    }

    public void handle() {
        // I WILL CONQUER THIS
    }

    public void setCloseAccount(ActionEvent e) {
        //closeAccount.setDisable(false);
        //closeAccount.disableProperty().bind(list.getSelectionModel().selectedItemProperty().isNull());
        // handle account info
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
    }

    public void setClear() {
        list.getItems().removeAll();
    }

    public void outputAcc() throws FileNotFoundException {
        String exportAcc = "accounts.txt";
        File accounts = new File(exportAcc);

        PrintWriter pw = new PrintWriter(accounts);
        // need to figure out how to change the account database method to write to printwriter
        pw.close();
    }

    public void importFile() throws FileNotFoundException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
        System.out.println("Imported a file");

        Scanner sc = new Scanner(sourceFile);
        while (sc.hasNext()) {

            String s = sc.nextLine();

            String[] inputArr = s.split(",");
            try {
                if (inputArr[0].equals("C")) {
                    System.out.println("Found matching account");
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
                        for (int i=0; i < withdrawals; i++){
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

            } catch (NumberFormatException e) {
                // e.printStackTrace();
            }
        }

    }
}