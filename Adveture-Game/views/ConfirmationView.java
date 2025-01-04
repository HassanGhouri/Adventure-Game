package views;

import Commands.FastTravelCommand;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class ConfirmationView {

    static int key;
    private Label ConfirmationLabel = new Label(String.format("Are you sure you want to FastTravel to the this room?"));
    private Button Confirm = new Button("Confirm");
    private Button closeWindowButton = new Button("Close Window");



    private AdventureGameView adventureGameView;

    /**
     * Constructor
     */
    public ConfirmationView(AdventureGameView adventureGameView, Integer key) {
        this.adventureGameView = adventureGameView;
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(adventureGameView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: #121212;");

        VBox vbox = new VBox();

        Image objImg = new Image(adventureGameView.model.getDirectoryName() + "/room-images/" + key + ".png");
        javafx.scene.image.ImageView view = new ImageView(objImg);
        view.setFitWidth(90);
        view.setFitHeight(90);
        Label label = new Label(adventureGameView.model.getRooms().get(key).getRoomName() + "(" + key.toString() + ")");
        label.setTextFill(Color.WHITE);
        vbox.setAlignment(Pos.CENTER);

        ConfirmationLabel.setId("Confirm"); // DO NOT MODIFY ID
        ConfirmationLabel.setStyle("-fx-text-fill: #e8e6e3;");
        ConfirmationLabel.setFont(new Font(16));

        vbox.getChildren().addAll(ConfirmationLabel, view, label);
        vbox.setSpacing(10);

        Confirm.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        Confirm.setPrefSize(200, 50);
        Confirm.setFont(new Font(16));
        Confirm.setOnAction(e -> {
            adventureGameView.model.getManager().execute(new FastTravelCommand(adventureGameView.model, adventureGameView.model.getRooms().get(key)));
            adventureGameView.updateScene("You have FastTraveled to " + adventureGameView.model.getRooms().get(key).getRoomName() + "!");
            adventureGameView.updateItems();
            adventureGameView.locationToggle = false;
            dialog.close();
        });

        closeWindowButton = new Button("Close Window");
        closeWindowButton.setId("closeWindowButton"); // DO NOT MODIFY ID
        closeWindowButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        closeWindowButton.setPrefSize(200, 50);
        closeWindowButton.setFont(new Font(16));
        closeWindowButton.setOnAction(e -> dialog.close());
        AdventureGameView.makeButtonAccessible(closeWindowButton, "close window", "This is a button to close the save game window", "Use this button to close the save game window.");

        VBox saveGameBox = new VBox(10, vbox, Confirm,  closeWindowButton);
        saveGameBox.setAlignment(Pos.CENTER);

        dialogVbox.getChildren().add(saveGameBox);
        Scene dialogScene = new Scene(dialogVbox, 400, 320);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
