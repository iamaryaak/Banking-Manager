package TransactionPkg;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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

    public ToggleGroup tg = new ToggleGroup();

    public void sayName(ActionEvent actionEvent) {
        String n = firstName.getText();
        String l = lastName.getText();

        System.out.println("Got name: " + n + " " + l);
    }

    public void checkingSelect(ActionEvent e){
        // select checking and gray out other two
        System.out.println("Checking is selected");
    }

}
