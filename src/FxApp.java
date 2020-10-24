import TransactionPkg.AccountDatabase;
import javafx.stage.Stage;

public class FxApp extends AccountDatabase {

    //@Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    private static void launch(String[] args) {
    }
    // hello
}
