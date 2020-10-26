package TransactionPkg;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    public TextField firstName;
    public Button openAccount;

    public void sayFirstName(ActionEvent actionEvent) {
        String n = firstName.getText();
        System.out.println("Got name " + n);
    }

}
