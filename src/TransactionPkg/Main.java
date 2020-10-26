package TransactionPkg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    // Create Database - AccountDatabase db = new AccountDatabase();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Transaction Manager");
        primaryStage.setScene(new Scene(root, 732, 464));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
