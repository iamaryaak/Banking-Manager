import TransactionPkg.AccountDatabase;
import TransactionPkg.TransactionManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApp extends AccountDatabase {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
    // hello
}
