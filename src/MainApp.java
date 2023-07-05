// imports for allowing the program to run
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.IOException;

// Main App Class
public class MainApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GDP Comparison"); // Set the title of the primary stage
        this.primaryStage.getIcons().add(new Image("icon.png")); // Set the application icon

        showBarChartScene(); // Show the initial Bar Chart scene
    }

    // Method to show the Bar Chart scene
    public void showBarChartScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BarChartScene.fxml")); // Load the FXML file for the Bar Chart scene
            Scene scene = new Scene(loader.load()); // Create a new scene with the loaded FXML file
            primaryStage.setScene(scene); // Set the scene on the primary stage

            BarChartSceneController controller = loader.getController(); // Get the controller instance from the loader
            controller.setMainApp(this); // Set the reference to the main application in the controller

            primaryStage.show(); // Show the primary stage
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an error occurs while loading the scene
        }
    }

    // Method to show the Table scene
    public void showTableScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TableScene.fxml")); // Load the FXML file for the Table scene
            Scene scene = new Scene(loader.load()); // Create a new scene with the loaded FXML file
            primaryStage.setScene(scene); // Set the scene on the primary stage

            TableSceneController controller = loader.getController(); // Get the controller instance from the loader
            controller.setMainApp(this); // Set the reference to the main application in the controller

            primaryStage.show(); // Show the primary stage
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an error occurs while loading the scene
        }
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}