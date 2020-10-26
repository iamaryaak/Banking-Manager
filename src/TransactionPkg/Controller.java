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
        } else if (savings.isSelected()) {
            direct.setDisable(true);
        } else if (moneyMarket.isSelected()) {
            direct.setDisable(true);
            loyal.setDisable(true);
        } else {
            direct.setDisable(false);
            loyal.setDisable(false);
        }
    }

    public void sayName(ActionEvent actionEvent) {
        String n = firstName.getText();
        String l = lastName.getText();

        System.out.println("Got name: " + n + " " + l);
    }

    public void setDirectDepo(ActionEvent e) {
        System.out.println("Direct Deposit");
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
                Account accC = new Checking(user, Double.parseDouble(balance.getText()), dateOpen, false);
                boolean added = db.add(accC);
                if (added) {
                    System.out.println("Account opened and added to the database.");
                } else {
                    System.out.println("Account is already in the database.");
                }
            } else {
                System.out.println(dateOpen.toString() + " is not a valid date!");
            }

        }

        if(savings.isSelected()) {
            System.out.println("Opening Account for " + firstName.getText() + " " + lastName.getText());
            System.out.println("Date " + month.getText() + " " + day.getText() + " " + year.getText());
            System.out.println("Balance " + balance.getText());

            Date dateOpen = new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
            if (dateOpen.isValid()) {
                Profile user = new Profile(firstName.getText(), lastName.getText());
                Account accS = new Savings(user, Double.parseDouble(balance.getText()), dateOpen, false);
                boolean added = db.add(accS);
                if (added) {
                    System.out.println("Account opened and added to the database.");
                } else {
                    System.out.println("Account is already in the database.");
                }
            } else {
                System.out.println(dateOpen.toString() + " is not a valid date!");
            }

        }

    }

}