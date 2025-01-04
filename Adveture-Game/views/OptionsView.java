package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class will display an options menu for the user to choose between
 * various contrast modes, colorblind modes, and to save/load their game.
 */
public class OptionsView {
    Button saveButton, loadButton, helpButton, searchButton;
    AdventureGameView adventureGameView;
    Label contrastModesLabel = new Label("Contrast Modes"); //Label for Contrast modes
    Label colorblindModesLabel = new Label("Colorblind Modes"); //Label for Colorblind modes
    Label generalLabel = new Label("General"); //Label for save, load, and close window
    Button lightModeButton = new Button("Light Mode"); //light mode button
    Button darkModeButton = new Button("Dark Mode"); //dark mode button
    Button protanopeButton = new Button("Protanope"); //protanope mode button
    Button deuteranopeButton = new Button("Deuteranope"); //deuteranope mode button
    Button tritanopeButton = new Button("Tritanope"); //tritanope mode button
    Button monochromeButton = new Button("Monochrome"); //monochrome mode button
    Button closeWindowButton = new Button("Close Window"); //button to close window
    VBox dialogVbox = new VBox(20); //Vbox for options menu

    public OptionsView(Button sb, Button lb, Button hb, Button shb, AdventureGameView adv) {
        this.saveButton = sb;
        this.loadButton = lb;
        this.helpButton = hb;
        this.searchButton = shb;
        this.adventureGameView = adv;

        // creating the menu
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(adventureGameView.stage);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: #121212;");

        // labels in the option menu
        contrastModesLabel.setId("ContrastModes"); // DO NOT MODIFY ID
        contrastModesLabel.setStyle("-fx-text-fill: #e8e6e3;");
        contrastModesLabel.setFont(new Font(16));

        colorblindModesLabel.setId("ColorblindModes");
        colorblindModesLabel.setStyle("-fx-text-fill: #e8e6e3;");
        colorblindModesLabel.setFont(new Font(16));

        generalLabel.setId("General");
        generalLabel.setStyle("-fx-text-fill: #e8e6e3;");
        generalLabel.setFont(new Font(16));

        // buttons in the options menu
        lightModeButton.setId("lightModeButton");
        lightModeButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        lightModeButton.setPrefSize(200, 50);
        lightModeButton.setFont(new Font(16));
        lightModeButton.setOnAction(e ->
                lightMode());
        AdventureGameView.makeButtonAccessible(lightModeButton, "light mode", "This is a button to display light mode", "Use this button to turn on light mode.");

        darkModeButton.setId("darkModeButton");
        darkModeButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        darkModeButton.setPrefSize(200, 50);
        darkModeButton.setFont(new Font(16));
        darkModeButton.setOnAction(e ->
                darkMode());
        AdventureGameView.makeButtonAccessible(darkModeButton, "dark mode", "This is a button to display dark mode", "Use this button to turn on dark mode");

        protanopeButton.setId("protanopeButton");
        protanopeButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        protanopeButton.setPrefSize(200, 50);
        protanopeButton.setFont(new Font(16));
        protanopeButton.setOnAction(e ->
                protanopeMode());
        AdventureGameView.makeButtonAccessible(protanopeButton, "protanope mode", "This is a button to display protanope mode", "Use this button to turn on protanope mode.");

        deuteranopeButton.setId("deuteranpeButton");
        deuteranopeButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        deuteranopeButton.setPrefSize(200, 50);
        deuteranopeButton.setFont(new Font(16));
        deuteranopeButton.setOnAction(e ->
                deuteranopeMode());
        AdventureGameView.makeButtonAccessible(deuteranopeButton, "deuteranope mode", "This is a button to display protanope mode", "Use this button to turn on deuteranope mode.");

        tritanopeButton.setId("tritanopeButton");
        tritanopeButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        tritanopeButton.setPrefSize(200, 50);
        tritanopeButton.setFont(new Font(16));
        tritanopeButton.setOnAction(e ->
                tritanopeMode());
        AdventureGameView.makeButtonAccessible(tritanopeButton, "tritanope mode", "This is a button to display tritanope mode", "Use this button to turn on tritanope mode.");

        monochromeButton.setId("monochromeButton");
        monochromeButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        monochromeButton.setPrefSize(200, 50);
        monochromeButton.setFont(new Font(16));
        monochromeButton.setOnAction(e ->
                monochromeMode());
        AdventureGameView.makeButtonAccessible(monochromeButton, "monochrome mode", "This is a button to display monochrome mode", "Use this button to turn on monochrome button");

        closeWindowButton.setId("closeWindowButton"); // DO NOT MODIFY ID
        closeWindowButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        closeWindowButton.setPrefSize(200, 50);
        closeWindowButton.setFont(new Font(16));
        closeWindowButton.setOnAction(e -> dialog.close());
        AdventureGameView.makeButtonAccessible(closeWindowButton, "close window", "This is a button to close the save game window", "Use this button to close the save game window.");

        // creating the menu
        VBox optionsBox = new VBox(10, contrastModesLabel, lightModeButton, darkModeButton, colorblindModesLabel, protanopeButton, deuteranopeButton, tritanopeButton,
                monochromeButton, generalLabel, saveButton, loadButton,closeWindowButton);
        optionsBox.setAlignment(Pos.CENTER);

        dialogVbox.getChildren().add(optionsBox);
        Scene dialogScene = new Scene(dialogVbox, 500, 650);
        dialog.setScene(dialogScene);
        dialog.show();

        dialogVbox.setStyle("-fx-background-color: #C0C0C0;");

        lightModeButton.setStyle("-fx-background-color: #48D1CC; -fx-text-fill: white;");
        darkModeButton.setStyle("-fx-background-color: #48D1CC; -fx-text-fill: white;");
        protanopeButton.setStyle("-fx-background-color: #48D1CC; -fx-text-fill: white;");
        deuteranopeButton.setStyle("-fx-background-color: #48D1CC; -fx-text-fill: white;");
        tritanopeButton.setStyle("-fx-background-color: #48D1CC; -fx-text-fill: white;");
        monochromeButton.setStyle("-fx-background-color: #48D1CC; -fx-text-fill: white;");
        closeWindowButton.setStyle("-fx-background-color: #48D1CC; -fx-text-fill: white;");
        saveButton.setStyle("-fx-background-color: #48D1CC; -fx-text-fill: white;");
        loadButton.setStyle("-fx-background-color: #48D1CC; -fx-text-fill: white;");

        contrastModesLabel.setStyle("-fx-text-fill: #000000;");
        colorblindModesLabel.setStyle("-fx-text-fill: #000000;");
        generalLabel.setStyle("-fx-text-fill: #000000;");
    }

    /**
     This method will display the game with a light-themed background.
     */
    public void lightMode() {
        adventureGameView.objLabel.setStyle("-fx-text-fill: black;");
        adventureGameView.invLabel.setStyle("-fx-text-fill: black;");
        adventureGameView.commandLabel.setStyle("-fx-text-fill: black;");
        adventureGameView.textEntry.setStyle("-fx-background-color: #FFFFFF;");
        adventureGameView.gridPane.setStyle("-fx-background-color: #FFFFFF;");
        adventureGameView.roomPane.setStyle("-fx-background-color: #FFFFFF;");
        adventureGameView.roomDescLabel.setStyle("-fx-text-fill: black;");
        adventureGameView.scO.setStyle("-fx-background: #FFFFFF; -fx-background-color:transparent;");
        adventureGameView.scI.setStyle("-fx-background: #FFFFFF; -fx-background-color:transparent;");

        adventureGameView.healthBar.healthBarLabel.setStyle("-fx-text-fill: black; -fx-font-family: 'Arial'; -fx-font-size: 16; -fx-font-weight: bold;");
        adventureGameView.healthBar.progressBar.setStyle("-fx-accent: red;");

        adventureGameView.mapButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        adventureGameView.helpButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        adventureGameView.optionsButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        adventureGameView.searchButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        adventureGameView.current_mode = "light";
        adventureGameView.changeRoomPane();
    }

    /**
     * This method will display the game with a light-themed background.
     */
    public void darkMode() {
        adventureGameView.objLabel.setStyle("-fx-text-fill: white;");
        adventureGameView.invLabel.setStyle("-fx-text-fill: white;");
        adventureGameView.commandLabel.setStyle("-fx-text-fill: white;");
        adventureGameView.textEntry.setStyle("-fx-background-color: #000000;");
        adventureGameView.gridPane.setStyle("-fx-background-color: #000000;");
        adventureGameView.roomPane.setStyle("-fx-background-color: #000000;");
        adventureGameView.roomDescLabel.setStyle("-fx-text-fill: white;");
        adventureGameView.scO.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
        adventureGameView.scI.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
        adventureGameView.healthBar.healthBarLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Arial'; -fx-font-size: 16; -fx-font-weight: bold;");
        adventureGameView.healthBar.progressBar.setStyle("-fx-accent: red;");

        adventureGameView.mapButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        adventureGameView.helpButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        adventureGameView.optionsButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        adventureGameView.searchButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        adventureGameView.current_mode = "dark";
        adventureGameView.changeRoomPane();
    }

    /**
     * This method will display colours for people with Protanopia.
     * People with protanopia are unable to perceive any 'red' light.
     */
    public void protanopeMode() {
        adventureGameView.objLabel.setStyle("-fx-text-fill: gold;");
        adventureGameView.invLabel.setStyle("-fx-text-fill: gold;");
        adventureGameView.commandLabel.setStyle("-fx-text-fill: gold;");
        adventureGameView.textEntry.setStyle("-fx-background-color: #AFEEEE;");
        adventureGameView.gridPane.setStyle("-fx-background-color: #20B2AA;");
        adventureGameView.roomPane.setStyle("-fx-background-color: #20B2AA;");
        adventureGameView.roomDescLabel.setStyle("-fx-text-fill: gold;");
        adventureGameView.scO.setStyle("-fx-background: #20B2AA; -fx-background-color:transparent;");
        adventureGameView.scI.setStyle("-fx-background: #20B2AA; -fx-background-color:transparent;");

        adventureGameView.healthBar.healthBarLabel.setStyle("-fx-text-fill: blue; -fx-font-family: 'Arial'; -fx-font-size: 16; -fx-font-weight: bold;");
        adventureGameView.healthBar.progressBar.setStyle("-fx-accent: yellow;");

        adventureGameView.mapButton.setStyle("-fx-background-color: #AFEEEE; -fx-text-fill: blue;");
        adventureGameView.helpButton.setStyle("-fx-background-color: #AFEEEE; -fx-text-fill: blue;");
        adventureGameView.optionsButton.setStyle("-fx-background-color: #AFEEEE; -fx-text-fill: blue;");
        adventureGameView.searchButton.setStyle("-fx-background-color: #AFEEEE; -fx-text-fill: blue;");

        adventureGameView.current_mode = "protanope";
        adventureGameView.changeRoomPane();
    }

    /**
     * This method will display colours for people with Protanopia.
     * People with protanopia are unable to perceive any 'green' light.
     */
    public void deuteranopeMode() {
        adventureGameView.objLabel.setStyle("-fx-text-fill: gold;");
        adventureGameView.invLabel.setStyle("-fx-text-fill: gold;");
        adventureGameView.commandLabel.setStyle("-fx-text-fill: red;");
        adventureGameView.textEntry.setStyle("-fx-background-color: #F0E68C;");
        adventureGameView.gridPane.setStyle("-fx-background-color: #8A2BE2;");
        adventureGameView.roomPane.setStyle("-fx-background-color: #EEE8AA;");
        adventureGameView.roomDescLabel.setStyle("-fx-text-fill: purple;");
        adventureGameView.scO.setStyle("-fx-background: #F0E68C; -fx-background-color:transparent;");
        adventureGameView.scI.setStyle("-fx-background: #F0E68C; -fx-background-color:transparent;");

        adventureGameView.healthBar.healthBarLabel.setStyle("-fx-text-fill: purple; -fx-font-family: 'Arial'; -fx-font-size: 16; -fx-font-weight: bold;");
        adventureGameView.healthBar.progressBar.setStyle("-fx-accent: purple;");

        adventureGameView.mapButton.setStyle("-fx-background-color: #BA55D3; -fx-text-fill: gold;");
        adventureGameView.helpButton.setStyle("-fx-background-color: #BA55D3; -fx-text-fill: gold;");
        adventureGameView.optionsButton.setStyle("-fx-background-color: #BA55D3; -fx-text-fill: gold;");
        adventureGameView.searchButton.setStyle("-fx-background-color: #BA55D3; -fx-text-fill: gold;");

        adventureGameView.current_mode = "deuteranope";
        adventureGameView.changeRoomPane();
    }

    /**
     * This is the Class that will display colours for people with Tritanopia.
     * People with tritanopia are unable to perceive any 'blue' light.
     */
    public void tritanopeMode() {
        adventureGameView.objLabel.setStyle("-fx-text-fill: orange;");
        adventureGameView.invLabel.setStyle("-fx-text-fill: orange;");
        adventureGameView.commandLabel.setStyle("-fx-text-fill: white;");
        adventureGameView.textEntry.setStyle("-fx-background-color: #87CEFA;");
        adventureGameView.gridPane.setStyle("-fx-background-color: #C71585;");
        adventureGameView.roomPane.setStyle("-fx-background-color: #F08080;");
        adventureGameView.roomDescLabel.setStyle("-fx-text-fill: purple;");
        adventureGameView.scO.setStyle("-fx-background: #F08080; -fx-background-color:transparent;");
        adventureGameView.scI.setStyle("-fx-background: #F08080; -fx-background-color:transparent;");

        adventureGameView.healthBar.healthBarLabel.setStyle("-fx-text-fill: blue; -fx-font-family: 'Arial'; -fx-font-size: 16; -fx-font-weight: bold;");
        adventureGameView.healthBar.progressBar.setStyle("-fx-accent: blue;");

        adventureGameView.mapButton.setStyle("-fx-background-color: #F08080; -fx-text-fill: purple;");
        adventureGameView.helpButton.setStyle("-fx-background-color: #F08080; -fx-text-fill: purple;");
        adventureGameView.optionsButton.setStyle("-fx-background-color: #F08080; -fx-text-fill: purple;");
        adventureGameView.searchButton.setStyle("-fx-background-color: #F08080; -fx-text-fill: purple;");

        adventureGameView.current_mode = "tritanope";
        adventureGameView.changeRoomPane();
    }

    /**
     * This is the Class that will display shades of black and white for people with Monochromia.
     * People with monochromia are unable to perceive any colour.
     */
    public void monochromeMode() {
        adventureGameView.objLabel.setStyle("-fx-text-fill: white;");
        adventureGameView.invLabel.setStyle("-fx-text-fill: white;");
        adventureGameView.commandLabel.setStyle("-fx-text-fill: white;");
        adventureGameView.textEntry.setStyle("-fx-background-color: #000000;");
        adventureGameView.gridPane.setStyle("-fx-background-color: #000000;");
        adventureGameView.roomPane.setStyle("-fx-background-color: #000000;");
        adventureGameView.roomDescLabel.setStyle("-fx-text-fill: white;");
        adventureGameView.scO.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
        adventureGameView.scI.setStyle("-fx-background: #000000; -fx-background-color:transparent;");

        adventureGameView.healthBar.healthBarLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Arial'; -fx-font-size: 16; -fx-font-weight: bold;");
        adventureGameView.healthBar.progressBar.setStyle("-fx-accent: black;");

        adventureGameView.mapButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");
        adventureGameView.helpButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");
        adventureGameView.optionsButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");
        adventureGameView.searchButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");

        adventureGameView.current_mode = "monochrome";
        adventureGameView.changeRoomPane();
    }
}
