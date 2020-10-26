package TransactionPkg;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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

    public RadioButton checking;
    public RadioButton savings;
    public RadioButton moneyMarket;

    public void sayName(ActionEvent actionEvent) {
        String n = firstName.getText();
        String l = lastName.getText();

        System.out.println("Got name: " + n + " " + l);
    }

    public void checkingSelect(ActionEvent e){
        System.out.println("Checking Account Selected");
        // gray out other 2
    }

}