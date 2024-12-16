package application;

import java.io.File;

//import java.awt.TextArea;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Set up the root layout
            BorderPane root = new BorderPane();

            // Add UI elements to the root
            root.setCenter(createMainUI(primaryStage));

            // Set up the scene
            Scene scene = new Scene(root, 400, 600);
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
    private VBox createMainUI(Stage stg) {
        VBox container = new VBox(20); // Main container
        container.setStyle("-fx-padding: 20; -fx-alignment: center;");

        //Add input field for Connections( we get these from an xml file)
        GridPane connectionPane = createConnectionField(); // Your existing method        
        // Add input fields for DB details
        GridPane dbDetailsPane = createDBInputFields(); // Your existing method
        // Add input field for updatescripts location
        GridPane folderLocationPane = createScriptsLocationField(stg);
        
        // Create BorderPan Sql Update Scripts folder
        BorderPane connectionSection = new BorderPane();
        connectionSection.setStyle(
                "-fx-border-color: gray; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 5; " +
                "-fx-background-color: #f9f9f9;"
            );
        // Label for the title
        Label connectionSectionLabel = new Label("Connections");
        connectionSectionLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-background-color: #f9f9f9; " +
            "-fx-padding: 0 5 0 5;"
        );
        // Position the label at the top of the border
        connectionSection.setTop(connectionSectionLabel);
        BorderPane.setAlignment(connectionSectionLabel, javafx.geometry.Pos.TOP_LEFT); // Change to TOP_CENTER if needed
        BorderPane.setMargin(connectionSectionLabel, new javafx.geometry.Insets(-10, 0, 0, 10)); // Adjust margins if needed
        // Add padding directly to the GridPane if needed (optional)
        connectionPane.setPadding(new javafx.geometry.Insets(10)); // Adds padding inside the GridPane
        // Add DB input fields to the center of the BorderPane
        connectionSection.setCenter(connectionPane);
        
        // Create BorderPan Sql Update Scripts folder
        BorderPane folderSection = new BorderPane();
        folderSection.setStyle(
                "-fx-border-color: gray; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 5; " +
                "-fx-background-color: #f9f9f9;"
            );
        // Label for the title
        Label folderSectionLabel = new Label("SQL Update Scripts Folder");
        folderSectionLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-background-color: #f9f9f9; " +
            "-fx-padding: 0 5 0 5;"
        );
        // Position the label at the top of the border
        folderSection.setTop(folderSectionLabel);
        BorderPane.setAlignment(folderSectionLabel, javafx.geometry.Pos.TOP_LEFT); // Change to TOP_CENTER if needed
        BorderPane.setMargin(folderSectionLabel, new javafx.geometry.Insets(-10, 0, 0, 10)); // Adjust margins if needed
        // Add padding directly to the GridPane if needed (optional)
        folderLocationPane.setPadding(new javafx.geometry.Insets(10)); // Adds padding inside the GridPane
        // Add DB input fields to the center of the BorderPane
        folderSection.setCenter(folderLocationPane);
        
        // Create a BorderPane for precise control over label placement
        BorderPane dbSection = new BorderPane();
        dbSection.setStyle(
            "-fx-border-color: gray; " +
            "-fx-border-width: 1; " +
            "-fx-border-radius: 5; " +
            "-fx-background-color: #f9f9f9;"
        );

        // Label for the title
        Label dbSectionLabel = new Label("Database");
        dbSectionLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-background-color: #f9f9f9; " +
            "-fx-padding: 0 5 0 5;"
        );

        // Position the label at the top of the border
        dbSection.setTop(dbSectionLabel);
        BorderPane.setAlignment(dbSectionLabel, javafx.geometry.Pos.TOP_LEFT); // Change to TOP_CENTER if needed
        BorderPane.setMargin(dbSectionLabel, new javafx.geometry.Insets(-10, 0, 0, 10)); // Adjust margins if needed
        // Add padding directly to the GridPane if needed (optional)
        dbDetailsPane.setPadding(new javafx.geometry.Insets(10)); // Adds padding inside the GridPane

        // Add DB input fields to the center of the BorderPane
        dbSection.setCenter(dbDetailsPane);

        // Add buttons
        GridPane dbButtons = createDBButtons();

        
        // Create a BorderPane for precise control over label placement
        BorderPane resultSection = new BorderPane();
        resultSection.setStyle(
            "-fx-border-color: gray; " +
            "-fx-border-width: 1; " +
            "-fx-border-radius: 5; " +
            "-fx-background-color: #f9f9f9;"
        );

        // Label for the title
        Label resultSectionLabel = new Label("Result");
        resultSectionLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-background-color: #f9f9f9; " +
            "-fx-padding: 0 5 0 5;"
        );

        // Position the label at the top of the border
        resultSection.setTop(resultSectionLabel);
        BorderPane.setAlignment(resultSectionLabel, javafx.geometry.Pos.TOP_LEFT); // Change to TOP_CENTER if needed
        BorderPane.setMargin(resultSectionLabel, new javafx.geometry.Insets(-10, 0, 0, 10)); // Adjust margins if needed

        // Results output box
        TextArea resultsOutput = new TextArea();
        resultsOutput.setId("resultsOutput");
        resultsOutput.setEditable(false);
        resultsOutput.setWrapText(true);
        resultsOutput.setPrefHeight(200);
        // Add DB input fields to the center of the BorderPane
        resultSection.setCenter(resultsOutput);
        // Add all sections to the main container
        container.getChildren().addAll(connectionSection,folderSection,dbSection, dbButtons, resultSection);

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
        Label dbScriptsLocationLabel = new Label("Scripts Log Table");

        // Create text fields
        TextField dbServerNameField = new TextField();
        TextField dbNameField = new TextField();
        TextField dbUserNameField = new TextField();
        TextField dbPasswordField = new TextField();
        TextField dbScriptsLocationField = new TextField();
//        dbPasswordField.setPromptText("Enter password...");
//        dbServerNameField.setPromptText("localhost");
//        dbNameField.setPromptText("Test");
//        dbUserNameField.setPromptText("sa");
        
        // Add labels and fields to the grid
        gridPane.add(dbServerLabel,0,0);
        gridPane.add(dbServerNameField,1,0);       
        gridPane.add(dbNameLabel, 0, 1);
        gridPane.add(dbNameField, 1, 1);
        gridPane.add(dbUserNameLabel, 0, 2);
        gridPane.add(dbUserNameField, 1, 2);
        gridPane.add(dbPasswordLabel, 0, 3);
        gridPane.add(dbPasswordField, 1, 3);
        gridPane.add(dbScriptsLocationLabel, 0, 4);
        gridPane.add(dbScriptsLocationField, 1, 4);

        return gridPane;
    }

    
    private GridPane createScriptsLocationField(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal spacing between columns
        gridPane.setVgap(15); // Vertical spacing between rows
        gridPane.setStyle("-fx-alignment: center;");

        // Create folder input field with a browse button
        HBox folderInputField = createFolderInputField(stage);

        // Add components to the GridPane
        gridPane.add(folderInputField, 0, 0);     // Column 1, Row 0     

        return gridPane;
    }

    private HBox createFolderInputField(Stage stage) {
        // TextField for manual input
        TextField folderPathField = new TextField();
        folderPathField.setPromptText("Enter folder location or browse...");
        HBox.setHgrow(folderPathField, Priority.ALWAYS); // Allow the TextField to grow horizontally

        // Button to open the folder browser
        Button browseButton = new Button("Browse");
        browseButton.setOnAction(event -> {
            // Open DirectoryChooser to select a folder
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Folder");

            // Optional: Set initial directory
            File initialDirectory = new File(System.getProperty("user.home"));
            if (initialDirectory.exists()) {
                directoryChooser.setInitialDirectory(initialDirectory);
            }

            // Show the folder chooser and get the selected folder
            File selectedDirectory = directoryChooser.showDialog(stage);
            if (selectedDirectory != null) {
                folderPathField.setText(selectedDirectory.getAbsolutePath()); // Update the TextField with selected path
            }
        });

        // Combine TextField and Button in an HBox
        HBox folderInputBox = new HBox(10); // 10 is the spacing between TextField and Button
        folderInputBox.getChildren().addAll(folderPathField, browseButton);

        return folderInputBox;
    }

    private GridPane createConnectionField() {
        // Create GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal spacing between columns
        gridPane.setVgap(15); // Vertical spacing between rows
        gridPane.setStyle("-fx-alignment: center;");
 
        ComboBox<String> comboBox = new ComboBox<String>();
        gridPane.add(comboBox, 0, 0);  
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
