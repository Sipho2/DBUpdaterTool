package application;

//import java.awt.TextArea;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
        VBox container = new VBox(20); // Main container
        container.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Add input fields for DB details
        GridPane dbDetailsPane = createDBInputFields(); // Your existing method

        // Create a BorderPane for precise control over label placement
        BorderPane labeledSection = new BorderPane();
        labeledSection.setStyle(
            "-fx-border-color: gray; " +
            "-fx-border-width: 1; " +
            "-fx-border-radius: 5; " +
            "-fx-background-color: #f9f9f9;"
        );

        // Label for the title
        Label sectionLabel = new Label("Database");
        sectionLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-background-color: #f9f9f9; " +
            "-fx-padding: 0 5 0 5;"
        );

        // Position the label at the top of the border
        labeledSection.setTop(sectionLabel);
        BorderPane.setAlignment(sectionLabel, javafx.geometry.Pos.TOP_LEFT); // Change to TOP_CENTER if needed
        BorderPane.setMargin(sectionLabel, new javafx.geometry.Insets(-10, 0, 0, 10)); // Adjust margins if needed
        // Add padding directly to the GridPane if needed (optional)
        dbDetailsPane.setPadding(new javafx.geometry.Insets(10)); // Adds padding inside the GridPane

        // Add DB input fields to the center of the BorderPane
        labeledSection.setCenter(dbDetailsPane);

        // Add buttons
        GridPane dbButtons = createDBButtons();

        // Results output box
        TextArea resultsOutput = new TextArea();
        resultsOutput.setId("resultsOutput");
        resultsOutput.setEditable(false);
        resultsOutput.setWrapText(true);
        resultsOutput.setPrefHeight(200);

        // Add all sections to the main container
        container.getChildren().addAll(labeledSection, dbButtons, resultsOutput);

        return container;
    }
    private GridPane createDBButtons() {
    	GridPane gridPane = new GridPane();
    	gridPane.setHgap(10);
        gridPane.setStyle("-fx-alignment: center;");
        Button button = new Button("Update Database");
        Button button2 = new Button("Show New Scripts");
        Button button3 = new Button("Install Database");
        gridPane.add(button, 0, 0);
        gridPane.add(button2, 1, 0);
        gridPane.add(button3, 2, 0);
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
        Label dbServerLabel = new Label("Server");
        Label dbNameLabel = new Label("Database");
        Label dbUserNameLabel = new Label("User");
        Label dbPasswordLabel = new Label("Password");
        Label dbScriptsLocationLabel = new Label("Scripts Location");

        // Create text fields
        TextField dbServerNameField = new TextField();
        TextField dbNameField = new TextField();
        TextField dbUserNameField = new TextField();
        TextField dbPasswordField = new TextField();
        TextField dbScriptsLocationField = new TextField();
        dbPasswordField.setPromptText("Enter password...");

        // Add labels and fields to the grid
        gridPane.add(dbServerLabel,0,0);
        gridPane.add(dbServerNameField,1,0);       
        gridPane.add(dbNameLabel, 0, 1);
        gridPane.add(dbNameField, 1, 1);
        gridPane.add(dbUserNameLabel, 0, 2);
        gridPane.add(dbUserNameField, 1, 2);
        gridPane.add(dbPasswordLabel, 0, 3);
        gridPane.add(dbPasswordField, 1, 3);
        //gridPane.add(dbScriptsLocationLabel, 0, 4);
        //gridPane.add(dbScriptsLocationField, 1, 4);

        return gridPane;
    }

    /**
     * Handles the "Submit" button action.
     * 
     * @param dbDetailsPane The grid containing the text fields for DB details.
     */
    private void handleSubmit(GridPane dbDetailsPane) {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
