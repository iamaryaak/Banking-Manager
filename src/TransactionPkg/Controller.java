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

    @FXML
    ListView<String> list = new ListView<String>();

    public Button openAccount;
    public Button closeAccount;
    public Button clear;

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

    public void initialize(){
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
        if(direct.isSelected()){
            directBool = true;
        }else{
            directBool = false;
        }
        return directBool;
    }

    public boolean setIsLoyal(ActionEvent e) {
        //System.out.println("Is Loyal");
        if(loyal.isSelected()){
            isLoyalBool = true;
        }else{
            isLoyalBool = false;
        }
        return isLoyalBool;
    }

    public void setOpenAccount(){

        if(checking.isSelected()) {
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

        }else if(savings.isSelected()) {
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

        }else if(moneyMarket.isSelected()){ // money Market is selected
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

    }

    public void setList(){
        do{
            closeAccount.setDisable(true);
        }while(list.getSelectionModel().getSelectedItem().isEmpty());

        String item = list.getSelectionModel().getSelectedItem();
        System.out.println(item);
    }

    public void setCloseAccount(ActionEvent e){

        setList();

        String account =  list.getSelectionModel().getSelectedItem();
        //System.out.println("Account Selected to Remove: " + account);

        // "*Checking*" +  holder.toString() + "* "+ "$" + df.format(balance) +
        //                "*" + dateOpen.toString() + isDirectDeposit()

        String[] acc = account.split("\\*");
        System.out.println("Account= " + acc[0]);
        Object accountObj = acc[0];
        String name = acc[1];
        // split this up
        String[] names = name.split("\\s");


        if(checking.isSelected()){
            System.out.println("Checking is selected");

        }else if(savings.isSelected()){
            System.out.println("Savings is selected");
        }else if(moneyMarket.isSelected()){
            System.out.println("moneyMarket is selected");
        }
    }

    public void setClear(){
        list.getItems().removeAll();
    }

}