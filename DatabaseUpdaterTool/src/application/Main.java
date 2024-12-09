package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Set up the root layout
            BorderPane root = new BorderPane();

            // Add UI elements to the root
            root.setCenter(createMainUI());

            // Set up the scene
            Scene scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // Configure the stage
            primaryStage.setTitle("Database Configuration");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the main UI layout.
     * 
     * @return VBox containing the main UI elements.
     */
    private VBox createMainUI() {
        VBox container = new VBox(20); // Vertical layout with spacing
        container.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Add input fields for DB details
        GridPane dbDetailsPane = createDBInputFields();

        GridPane dbButtons = createDBButtons();        
        // Create a button
        Button button = new Button("Submit");
        button.setOnAction(event -> handleSubmit(dbDetailsPane));
        dbDetailsPane.add(button, 0, 4);
        // Add components to the container
        container.getChildren().addAll(dbDetailsPane, dbButtons);

        return container;
    }
    private GridPane createDBButtons() {
    	GridPane gridPane = new GridPane();
    	gridPane.setHgap(10);
        gridPane.setStyle("-fx-alignment: center;");
        Button button = new Button("Submit");
        Button button2 = new Button("Check New Scripts");
        gridPane.add(button, 0, 0);
        gridPane.add(button2, 1, 0);
        return gridPane;
    }
    /**
     * Creates a grid layout with DB input fields.
     * 
     * @return GridPane containing labels and text fields.
     */
    private GridPane createDBInputFields() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal spacing between columns
        gridPane.setVgap(15); // Vertical spacing between rows
        gridPane.setStyle("-fx-alignment: center;");

        // Create labels
        Label dbNameLabel = new Label("DB Name:");
        Label dbUserNameLabel = new Label("DB UserName:");
        Label dbPasswordLabel = new Label("DB Password:");
        Label dbScriptsLocationLabel = new Label("Scripts Location:");

        // Create text fields
        TextField dbNameField = new TextField();
        TextField dbUserNameField = new TextField();
        TextField dbPasswordField = new TextField();
        TextField dbScriptsLocationField = new TextField();
        dbPasswordField.setPromptText("Enter password...");

        // Add labels and fields to the grid
        gridPane.add(dbNameLabel, 0, 0);
        gridPane.add(dbNameField, 1, 0);
        gridPane.add(dbUserNameLabel, 0, 1);
        gridPane.add(dbUserNameField, 1, 1);
        gridPane.add(dbPasswordLabel, 0, 2);
        gridPane.add(dbPasswordField, 1, 2);
        gridPane.add(dbScriptsLocationLabel, 0, 3);
        gridPane.add(dbScriptsLocationField, 1, 3);

        return gridPane;
    }

    /**
     * Handles the "Submit" button action.
     * 
     * @param dbDetailsPane The grid containing the text fields for DB details.
     */
    private void handleSubmit(GridPane dbDetailsPane) {
        // Retrieve text fields
        TextField dbNameField = (TextField) dbDetailsPane.getChildren().get(1);
        TextField dbUserNameField = (TextField) dbDetailsPane.getChildren().get(3);
        TextField dbPasswordField = (TextField) dbDetailsPane.getChildren().get(5);

        // Collect user input
        String dbName = dbNameField.getText();
        String dbUserName = dbUserNameField.getText();
        String dbPassword = dbPasswordField.getText();

        // Display the input in a popup
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Database Configuration");
        alert.setHeaderText("Input Details:");
        alert.setContentText("DB Name: " + dbName + "\nDB UserName: " + dbUserName + "\nDB Password: " + dbPassword);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
