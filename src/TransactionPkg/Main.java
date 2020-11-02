/**
 * @authors Arya Kulkarni and Jesse Barbieri
 */

package TransactionPkg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Main class that handles the stage and sets up the GUI
 */
public class Main extends Application {
    // Create Database - AccountDatabase db = new AccountDatabase();

    /**
     * Sets up the stage for the GUI with title Transaction Manager
     * @param primaryStage Stage for the GUI
     * @throws Exception incase this doesn't work
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Transaction Manager");
        primaryStage.setScene(new Scene(root, 732, 464));
        primaryStage.show();
    }


    /**
     * Main method that calls launch(args)
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
