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

    public ListView<String> list = new ListView<String>();

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
    ToggleGroup tg = new ToggleGroup();

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
        } else if (savings.isSelected()) {
            tg.getSelectedToggle();
            loyal.setDisable(false);
            direct.setDisable(true);
        } else if (moneyMarket.isSelected()) {
            tg.getSelectedToggle();
            direct.setDisable(true);
            loyal.setDisable(true);
        } else {
            direct.setDisable(true);
            loyal.setDisable(true);
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
                    list.getItems().add(accC.toString());
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
                    list.getItems().add(accS.toString());
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

            Date dateOpen = new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
            if (dateOpen.isValid()) {
                Profile user = new Profile(firstName.getText(), lastName.getText());
                Account accM = new MoneyMarket(user, Double.parseDouble(balance.getText()), dateOpen);
                boolean added = db.add(accM);
                if (added) {
                    list.getItems().add(accM.toString());
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