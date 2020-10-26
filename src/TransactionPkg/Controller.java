package TransactionPkg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    public TextField firstName;
    public TextField lastName;
    public TextField month;
    public TextField day;
    public TextField year;
    public TextField balance;

    public ListView<Account> list;

    public Button openAccount;

    public boolean directBool;
    public boolean isLoyalBool;

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


    public void selectAccount() {

        if (checking.isSelected()) {
            loyal.setDisable(true);
            savings.setDisable(true);
            moneyMarket.setDisable(true);
        } else if (savings.isSelected()) {
            direct.setDisable(true);
            checking.setDisable(true);
            moneyMarket.setDisable(true);
        } else if (moneyMarket.isSelected()) {
            direct.setDisable(true);
            loyal.setDisable(true);
            savings.setDisable(true);
            checking.setDisable(true);
        } else {
            checking.setDisable(false);
            savings.setDisable(false);
            moneyMarket.setDisable(false);
            direct.setDisable(false);
            loyal.setDisable(false);
        }
    }

    public void sayName(ActionEvent actionEvent) {
        String n = firstName.getText();
        String l = lastName.getText();

        System.out.println("Got name: " + n + " " + l);
    }

    public boolean setDirectDepo(ActionEvent e) {
        System.out.println("Direct Deposit");
        if(direct.isSelected()){
            directBool = true;
        }else{
            directBool = false;
        }
        return directBool;
    }

    public boolean setIsLoyal(ActionEvent e) {
        System.out.println("Is Loyal");
        if(loyal.isSelected()){
            isLoyalBool = true;
        }else{
            isLoyalBool = false;
        }
        return isLoyalBool;
    }

    public void setOpenAccount(){
        /**public TextField firstName;
        public TextField lastName;
        public TextField month;
        public TextField day;
        public TextField year;
        public TextField balance;
         **/
        if(checking.isSelected()) {
            System.out.println("Opening Account for " + firstName.getText() + " " + lastName.getText());
            System.out.println("Date " + month.getText() + " " + day.getText() + " " + year.getText());
            System.out.println("Balance " + balance.getText());

            Date dateOpen = new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
            if (dateOpen.isValid()) {
                Profile user = new Profile(firstName.getText(), lastName.getText());
                Account accC = new Checking(user, Double.parseDouble(balance.getText()), dateOpen, directBool);
                boolean added = db.add(accC);
                if (added) {
                    System.out.println("Account opened and added to the database.");
                } else {
                    System.out.println("Account is already in the database.");
                }
            } else {
                System.out.println(dateOpen.toString() + " is not a valid date!");
            }

        }else if(savings.isSelected()) {
            System.out.println("Opening Account for " + firstName.getText() + " " + lastName.getText());
            System.out.println("Date " + month.getText() + " " + day.getText() + " " + year.getText());
            System.out.println("Balance " + balance.getText());

            Date dateOpen = new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
            if (dateOpen.isValid()) {
                Profile user = new Profile(firstName.getText(), lastName.getText());
                Account accS = new Savings(user, Double.parseDouble(balance.getText()), dateOpen, isLoyalBool);
                boolean added = db.add(accS);
                if (added) {
                    System.out.println("Account opened and added to the database.");
                } else {
                    System.out.println("Account is already in the database.");
                }
            } else {
                System.out.println(dateOpen.toString() + " is not a valid date!");
            }

        }else if(moneyMarket.isSelected()){ // money Market is selected
            System.out.println("Opening Account for " + firstName.getText() + " " + lastName.getText());
            System.out.println("Date " + month.getText() + " " + day.getText() + " " + year.getText());
            System.out.println("Balance " + balance.getText());
        }

    }

}