package TransactionPkg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Controller {

    public TextField firstName;
    public TextField lastName;
    public TextField month;
    public TextField day;
    public TextField year;
    public TextField balance;

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


    public void selectAccount(){

        if(checking.isSelected()){
            loyal.setDisable(true);
        }
        else if (savings.isSelected()){
            direct.setDisable(true);
        }
        else if (moneyMarket.isSelected()){
            direct.setDisable(true);
            loyal.setDisable(true);
        }
        else
        {
            direct.setDisable(false);
            loyal.setDisable(false);
        }
    }


    public void sayName(ActionEvent actionEvent) {
        String n = firstName.getText();
        String l = lastName.getText();

        System.out.println("Got name: " + n + " " + l);
    }

    public void setDirectDepo(ActionEvent e){
        System.out.println("Direct Deposit");
    }

}