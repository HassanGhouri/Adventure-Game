package views;

import AdventureModel.*;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

import java.util.concurrent.ExecutionException;

import javafx.concurrent.Task;


/**
 * Class AdventureGameView.
 *
 * This is the Class that will visualize your model.
 * You are asked to demo your visualization via a Zoom
 * recording. Place a link to your recording below.
 *
 */
public class AdventureGameView {

    AdventureGame model; //model of the game
    Stage stage; //stage on which all is rendered
    Button saveButton, loadButton, helpButton, mapButton, searchButton, optionsButton; //buttons, notice the new button
    Boolean helpToggle = false; //is help on display?
    Boolean locationToggle = false; //are locations on display?
    GridPane gridPane = new GridPane(); //to hold images and buttons
    Label roomDescLabel = new Label(); //to hold room description and/or instructions
    Label title = new Label("Click on a room to fast travel to it".toUpperCase()); //for Map command
    Label inst; //to hold help instructions
    VBox help_roomPane = new VBox(); //for help
    VBox help_roomPane2 = new VBox();
    VBox loc_roomPane = new VBox(); //for map
    VBox objectsInRoom = new VBox(); //to hold room items
    VBox objectsInInventory = new VBox(); //to hold inventory items
    ImageView roomImageView; //to hold room image
    TextField inputTextField; //for user input
    public VBox textEntry = new VBox(); //for user input
    public Label objLabel = new Label("Objects in Room"); //to display room items
    public Label invLabel = new Label("Your inventory"); //to display inventory items
    public Label commandLabel = new Label("What would you like to do?"); //user command
    public VBox roomPane; //background
    public ScrollPane scO; //left column
    public ScrollPane scI; // right column
    ScrollPane scT = new ScrollPane(); //for map
    public HealthBar healthBar; //Health bar for player health
    public String current_mode;
    private MediaPlayer mediaPlayer; //to play audio
    private boolean mediaPlaying; //to know if the audio is playing
    /**
     * Adventure Game View Constructor
     * __________________________
     * Initializes attributes
     */
    public AdventureGameView(AdventureGame model, Stage stage) {
        this.model = model;
        this.stage = stage;
        intiUI();
    }

    /**
     * Initialize the UI
     */
    public void intiUI() {

        // setting up the stage
        this.stage.setTitle("Liminal Game"); //Replace <YOUR UTORID> with your UtorID

        //Inventory + Room items
        objectsInInventory.setSpacing(10);
        objectsInInventory.setAlignment(Pos.TOP_CENTER);
        objectsInRoom.setSpacing(10);
        objectsInRoom.setAlignment(Pos.TOP_CENTER);

        // GridPane, anyone?
        gridPane.setPadding(new Insets(20));
        gridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#000000"),
                new CornerRadii(0),
                new Insets(0)
        )));

        //Three columns, three rows for the GridPane
        ColumnConstraints column1 = new ColumnConstraints(150);
        ColumnConstraints column2 = new ColumnConstraints(650);
        ColumnConstraints column3 = new ColumnConstraints(150);
        column3.setHgrow( Priority.SOMETIMES ); //let some columns grow to take any extra space
        column1.setHgrow( Priority.SOMETIMES );

        // Row constraints
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints( 550 );
        RowConstraints row3 = new RowConstraints();
        row1.setVgrow( Priority.SOMETIMES );
        row3.setVgrow( Priority.SOMETIMES );

        gridPane.getColumnConstraints().addAll( column1 , column2 , column1 );
        gridPane.getRowConstraints().addAll( row1 , row2 , row1 );

        // Buttons
        saveButton = new Button("Save");
        saveButton.setId("Save");
        customizeButton(saveButton, 100, 50);
        makeButtonAccessible(saveButton, "Save Button", "This button saves the game.", "This button saves the game. Click it in order to save your current progress, so you can play more later.");
        addSaveEvent();

        //New options button to open up a menu for accessibility options
        optionsButton = new Button("Options");
        optionsButton.setId("Options");
        customizeButton(optionsButton, 100, 50);
        makeButtonAccessible(optionsButton, "Options Button", "This button opens up a menu of accessibility options.", "This button opens up a menu. The menu contains various color modes and an instructions page.");
        addOptionsEvent();

        loadButton = new Button("Load");
        loadButton.setId("Load");
        customizeButton(loadButton, 100, 50);
        makeButtonAccessible(loadButton, "Load Button", "This button loads a game from a file.", "This button loads the game from a file. Click it in order to load a game that you saved at a prior date.");
        addLoadEvent();

        helpButton = new Button("Help");
        helpButton.setId("Help");
        customizeButton(helpButton, 100, 50);
        makeButtonAccessible(helpButton, "Help Button", "This button gives game instructions.", "This button gives instructions on the game controls. Click it to learn how to play.");
        addInstructionEvent();

        //New map button at the top of the screen beside the save,help, and load buttons
        mapButton = new Button("Map");
        mapButton.setId("Map");
        customizeButton(mapButton, 100, 50);
        makeButtonAccessible(helpButton, "Map button", "This button gives a list of all visited rooms.", "This button gives a list of all visited rooms. Click it to see what rooms you have visited.");
        addLocationsEvent();

        //New Search Button for Search Event
        searchButton = new Button("Search");
        searchButton.setId("Search");
        customizeButton(searchButton, 100, 50);
        makeButtonAccessible(searchButton, "Search Button", "This button facilitates the search mechanic.", "This button allows the player to search for items in specific rooms.");
        searchButton.setVisible(false);
        addsearchButton();

        HBox topButtons = new HBox();
        topButtons.getChildren().addAll(helpButton, mapButton, optionsButton);
        topButtons.setSpacing(10);
        topButtons.setAlignment(Pos.CENTER);

        inputTextField = new TextField();
        inputTextField.setFont(new Font("Arial", 16));
        inputTextField.setFocusTraversable(true);

        inputTextField.setAccessibleRole(AccessibleRole.TEXT_AREA);
        inputTextField.setAccessibleRoleDescription("Text Entry Box");
        inputTextField.setAccessibleText("Enter commands in this box.");
        inputTextField.setAccessibleHelp("This is the area in which you can enter commands you would like to play.  Enter a command and hit return to continue.");
        addTextHandlingEvent(); //attach an event to this input field

        //labels for inventory and room items
        objLabel.setAlignment(Pos.CENTER);
        objLabel.setStyle("-fx-text-fill: white;");
        objLabel.setFont(new Font("Arial", 16));

        invLabel.setAlignment(Pos.CENTER);
        invLabel.setStyle("-fx-text-fill: white;");
        invLabel.setFont(new Font("Arial", 16));

        //add all the widgets to the GridPane
        gridPane.add( objLabel, 0, 0, 1, 1 );  // Add label
        gridPane.add( topButtons, 1, 0, 1, 1 );  // Add buttons
        gridPane.add( invLabel, 2, 0, 1, 1 );  // Add label

        commandLabel.setStyle("-fx-text-fill: white;");
        commandLabel.setFont(new Font("Arial", 16));

        inst = new Label(model.getInstructions()); //instructions menu background
        current_mode = "dark";  //current color mode

        updateScene(""); //method displays an image and whatever text is supplied
        updateItems(); //update items shows inventory and objects in rooms

        // adding the text area and submit button to a VBox
        textEntry.setStyle("-fx-background-color: #000000;");
        textEntry.setPadding(new Insets(20, 20, 20, 20));
        healthBar = new HealthBar(100);
        textEntry.getChildren().addAll(healthBar, inputTextField, searchButton);
        textEntry.setSpacing(10);
        textEntry.setAlignment(Pos.CENTER);
        gridPane.add( textEntry, 0, 2, 3, 1 );

        // Render everything
        var scene = new Scene( gridPane ,  1000, 800);
        scene.setFill(Color.BLACK);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();

    }


    /**
     * makeButtonAccessible
     * __________________________
     * For information about ARIA standards, see
     * https://www.w3.org/WAI/standards-guidelines/aria/
     *
     * @param inputButton the button to add screenreader hooks to
     * @param name ARIA name
     * @param shortString ARIA accessible text
     * @param longString ARIA accessible help text
     */
    public static void makeButtonAccessible(Button inputButton, String name, String shortString, String longString) {
        inputButton.setAccessibleRole(AccessibleRole.BUTTON);
        inputButton.setAccessibleRoleDescription(name);
        inputButton.setAccessibleText(shortString);
        inputButton.setAccessibleHelp(longString);
        inputButton.setFocusTraversable(true);
    }

    /**
     * customizeButton
     * __________________________
     *
     * @param inputButton the button to make stylish :)
     * @param w width
     * @param h height
     */
    private void customizeButton(Button inputButton, int w, int h) {
        inputButton.setPrefSize(w, h);
        inputButton.setFont(new Font("Arial", 16));
        inputButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
    }

    /**
     * addTextHandlingEvent
     * __________________________
     * Add an event handler to the myTextField attribute 
     *
     * Your event handler should respond when users 
     * hits the ENTER or TAB KEY. If the user hits 
     * the ENTER Key, strip white space from the
     * input to myTextField and pass the stripped 
     * string to submitEvent for processing.
     *
     * If the user hits the TAB key, move the focus 
     * of the scene onto any other node in the scene 
     * graph by invoking requestFocus method.
     */
    private void addTextHandlingEvent() {
        gridPane.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                submitEvent(inputTextField.getText().strip());
                inputTextField.clear();
            } else if (ke.getCode().equals(KeyCode.TAB)){
                gridPane.requestFocus();
            }
        });
    }


    /**
     * submitEvent
     * __________________________
     *
     * @param text the command that needs to be processed
     */
    private void submitEvent(String text) {

        text = text.strip(); //get rid of white space
        //stopArticulation(); //if speaking, stop


        if (text.equalsIgnoreCase("LOOK") || text.equalsIgnoreCase("L")) {
            String roomDesc = this.model.getPlayer().getCurrentRoom().getRoomDescription();
            String objectString = this.model.getPlayer().getCurrentRoom().getObjectString();
            if (!objectString.isEmpty()) roomDescLabel.setText(roomDesc + "\n\nObjects in this room:\n" + objectString);
            articulateRoomDescription(); //all we want, if we are looking, is to repeat description.
            return;
        } else if (text.equalsIgnoreCase("HELP") || text.equalsIgnoreCase("H")) {
            showInstructions();
            return;
        } else if (text.equalsIgnoreCase("COMMANDS") || text.equalsIgnoreCase("C")) {
            showCommands(); //this is new!  We did not have this command in A1
            return;
        }
        else if (text.equalsIgnoreCase("MAP")){
            showLocations();
            return;
        }

        //try to move!
        String output = this.model.interpretAction(text); //process the command!
        lockUI(false);

        if (output == null || (!output.equals("GAME OVER") && !output.equals("FORCED") && !output.equals("HELP") && !output.equals("YOU DIED"))) {
            updateScene(output);
            updateItems();
        } else if (output.equals("GAME OVER")) {
            updateScene("");
            updateItems();
            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        } else if (output.equals("FORCED")) {
            updateScene("");
            updateItems();
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            lockUI(true);
            pause.pause();
            pause.setOnFinished(e -> {
                PassageTable pt = model.player.getCurrentRoom().getMotionTable();
                if(pt.getDirection().get(0).getDirection().equals("FORCED")){
                    submitEvent("FORCED");
                }
            });
            pause.play();
        } else if (output.equals("YOU DIED")) {
            updateScene("YOU DIED");
            updateItems();
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            lockUI(true);
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
    }

    private void lockUI(boolean b) {
        for(Node n : gridPane.getChildren()){
            if(!(n instanceof Label)){
                n.setDisable(b);
            }
        }
        roomDescLabel.setDisable(false);
    }


    /**
     * showCommands
     * __________________________
     *
     * update the text in the GUI (within roomDescLabel)
     * to show all the moves that are possible from the 
     * current room.
     */
    private void showCommands() {
        roomDescLabel.setText(this.model.getPlayer().getCurrentRoom().getCommands());
    }


    /**
     * updateScene
     * __________________________
     *
     * Show the current room, and print some text below it.
     * If the input parameter is not null, it will be displayed
     * below the image.
     * Otherwise, the current room description will be dispplayed
     * below the image.
     * 
     * @param textToDisplay the text to display below the image.
     */
    public void updateScene(String textToDisplay) {

        getRoomImage(); //get the image of the current room
        formatText(textToDisplay); //format the text to display
        roomDescLabel.setPrefWidth(500);
        roomDescLabel.setPrefHeight(500);
        roomDescLabel.setTextOverrun(OverrunStyle.CLIP);
        roomDescLabel.setWrapText(true);
        roomPane = new VBox(roomImageView,roomDescLabel);
        roomPane.setPadding(new Insets(10));
        roomPane.setAlignment(Pos.TOP_CENTER);
        roomPane.setStyle("-fx-background-color: #000000;");

        changeBackground(); //change background color

        gridPane.add(roomPane, 1, 1);
        stage.sizeToScene();


        //finally, articulate the description
        if (textToDisplay == null || textToDisplay.isBlank()) articulateRoomDescription();
        if(healthBar != null){
            healthBar.setHealth(model.player.getHealth());
        }

        ArrayList<Integer> searchableRooms = new ArrayList<>();
        searchableRooms.add(111);
        searchableRooms.add(112);
        searchableRooms.add(114);

        if (searchableRooms.contains(this.model.getPlayer().getCurrentRoom().getRoomNumber())){
            searchButton.setVisible(true);
        }
        else {
            searchButton.setVisible(false);
        }
    }

    /**
     * This method changes the background color of the current roomPane
     * to match the current color mode.
     */
    private void changeBackground() {
        switch (current_mode) {
            case "light" -> {
                roomPane.setStyle("-fx-background-color: #FFFFFF;");
                roomDescLabel.setStyle("-fx-text-fill: black;");
            }
            case "dark", "monochrome" -> {
                roomPane.setStyle("-fx-background-color: #000000;");
                roomDescLabel.setStyle("-fx-text-fill: white;");
            }
            case "protanope" -> {
                roomPane.setStyle("-fx-background-color: #20B2AA;");
                roomDescLabel.setStyle("-fx-text-fill: gold;");
            }
            case "deuteranope" -> {
                roomPane.setStyle("-fx-background-color: #EEE8AA;");
                roomDescLabel.setStyle("-fx-text-fill: purple;");
            }
            case "tritanope" -> {
                roomPane.setStyle("-fx-background-color: #F08080;");
                roomDescLabel.setStyle("-fx-text-fill: purple;");
            }
        }
    }

    /**
     * formatText
     * __________________________
     *
     * Format text for display.
     * 
     * @param textToDisplay the text to be formatted for display.
     */
    private void formatText(String textToDisplay) {
        if (textToDisplay == null || textToDisplay.isBlank()) {
            String roomDesc = this.model.getPlayer().getCurrentRoom().getRoomDescription() + "\n";
            roomDescLabel.setText(roomDesc);
        } else roomDescLabel.setText(textToDisplay);
        roomDescLabel.setStyle("-fx-text-fill: white;");
        roomDescLabel.setFont(new Font("Arial", 16));
        roomDescLabel.setAlignment(Pos.CENTER);
    }

    /**
     * getRoomImage
     * __________________________
     *
     * Get the image for the current room and place 
     * it in the roomImageView 
     */
    private void getRoomImage() {

        int roomNumber = this.model.getPlayer().getCurrentRoom().getRoomNumber();
        String roomImage = this.model.getDirectoryName() + "/room-images/" + roomNumber + ".png";

        Image roomImageFile = new Image(roomImage);
        roomImageView = new ImageView(roomImageFile);
        roomImageView.setPreserveRatio(true);
        roomImageView.setFitWidth(400);
        roomImageView.setFitHeight(400);

        //set accessible text
        roomImageView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        roomImageView.setAccessibleText(this.model.getPlayer().getCurrentRoom().getRoomDescription());
        roomImageView.setFocusTraversable(true);
    }

    /**
     * updateItems
     * __________________________
     *
     * This method is partially completed, but you are asked to finish it off.
     *
     * The method should populate the objectsInRoom and objectsInInventory Vboxes.
     * Each Vbox should contain a collection of nodes (Buttons, ImageViews, you can decide)
     * Each node represents a different object.
     * 
     * Images of each object are in the assets 
     * folders of the given adventure game.
     */
    public void updateItems() {

        //write some code here to add images of objects in a given room to the objectsInRoom Vbox
        //write some code here to add images of objects in a player's inventory room to the objectsInInventory Vbox
        //please use setAccessibleText to add "alt" descriptions to your images!
        //the path to the image of any is as follows:
        //this.model.getDirectoryName() + "/objectImages/" + objectName + ".jpg";

        objectsInInventory.getChildren().clear();
        for(AdventureObject obj : this.model.player.inventory){
            Image objImg = new Image(this.model.getDirectoryName() + "/objectImages/" + obj.getName() + ".png");
            ImageView view = new ImageView(objImg);
            view.setAccessibleText(obj.getDescription());
            view.setFitWidth(100);
            view.setFitHeight(100);

            Button bt = new Button();
            bt.setGraphic(view);
            makeButtonAccessible(bt, obj.getName(), obj.getDescription(), obj.getDescription());
            bt.setOnAction(actionEvent -> {
                model.player.getCurrentRoom().objectsInRoom.add(obj);
                model.player.inventory.remove(obj);
                updateItems();
            });

            objectsInInventory.getChildren().add(bt);
        }

        objectsInRoom.getChildren().clear();
        for(AdventureObject obj : this.model.player.getCurrentRoom().objectsInRoom){
            Image objImg = new Image(this.model.getDirectoryName() + "/objectImages/" + obj.getName() + ".png");
            ImageView view = new ImageView(objImg);
            view.setAccessibleText(obj.getDescription());
            view.setFitWidth(100);
            view.setFitHeight(100);

            Button bt = new Button();
            bt.setGraphic(view);
            makeButtonAccessible(bt, obj.getName(), obj.getName(), obj.getDescription());
            bt.setOnAction(actionEvent -> {
                model.player.getCurrentRoom().objectsInRoom.remove(obj);
                model.player.inventory.add(obj);
                updateItems();
            });

            objectsInRoom.getChildren().add(bt);
        }

        scO = new ScrollPane(objectsInRoom);
        scO.setPadding(new Insets(0));
        scO.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
        scO.setFitToWidth(true);
        gridPane.add(scO,0,1);

        scI = new ScrollPane(objectsInInventory);
        scI.setPadding(new Insets(0));
        scI.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
        scI.setFitToWidth(true);
        gridPane.add(scI,2,1);
    }

    /*
     * Show possible fast travel locations.
     *
     * If locationToggle is FALSE:
     * -- display the possible fast travel locations within the gridPane
     * -- set the locationToggle to TRUE
     * -- REMOVE whatever nodes are within the cell beforehand!
     *
     * If locationToggle is TRUE:
     * -- redraw the room image in the CENTRE of the gridPane (i.e. within cell 1,1)
     * -- set the locationToggle to FALSE
     */
    public void showLocations(){
        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 1);
        if (locationToggle){
            //generate the room screen
            getRoomImage(); //get the image of the current room
            roomDescLabel.setPrefWidth(500);
            roomDescLabel.setPrefHeight(500);
            roomDescLabel.setTextOverrun(OverrunStyle.CLIP);
            roomDescLabel.setWrapText(true);
            loc_roomPane = new VBox(roomImageView, roomDescLabel);
            loc_roomPane.setPadding(new Insets(10));
            loc_roomPane.setAlignment(Pos.TOP_CENTER);
            loc_roomPane.setStyle("-fx-background-color: #0091010;");

            gridPane.add(loc_roomPane, 1, 1);
            stage.sizeToScene();
            locationToggle = false;
        }
        else {
            // generate the map of non FORCED visited rooms
            model.player.getCurrentRoom().visit();
            HashMap<Integer, Room> locations = model.getVisitedRooms();
            FlowPane flowPane = new FlowPane();
            for (Integer key : locations.keySet()) {
                VBox vBox = new VBox();
                Image objImg = new Image(this.model.getDirectoryName() + "/room-images/" + key + ".png");
                ImageView view = new ImageView(objImg);
                view.setAccessibleText(locations.get(key).getRoomName());
                view.setFitWidth(70);
                view.setFitHeight(70);

                Button bt = new Button("");
                bt.setGraphic(view);
                bt.setOnAction(actionEvent -> {
                    gridPane.requestFocus();
                    ConfirmationView confirmationView = new ConfirmationView(this, key);
                });

                Label label = new Label(key.toString());
                label.setTextFill(locations.get(key).objectsInRoom.isEmpty() ? Color.WHITE : Color.BLUEVIOLET);
                vBox.getChildren().addAll(bt, label);
                vBox.setSpacing(10);
                vBox.setAlignment(Pos.CENTER);
                flowPane.getChildren().add(vBox);
            }
            flowPane.setHgap(15);
            flowPane.setVgap(15);
            flowPane.setOrientation(Orientation.HORIZONTAL);

            VBox big_picture = new VBox();
            title.setTextFill(Color.PALETURQUOISE);
            title.setFont(Font.font(20));

            big_picture.getChildren().addAll(title, flowPane);
            big_picture.setSpacing(37.80);
            big_picture.setAlignment(Pos.TOP_CENTER);

            scT.setContent(big_picture);
            scT.setPadding(new Insets(1 * 37.8, 2 * 37.8, 0, 3 * 37.8));
            scT.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
            scT.setFitToWidth(true);
            gridPane.add(scT,1,1);

            locationToggle = true;
            helpToggle = false;
        }
        changeRoomPane(); //change the background colors of the roomPane
    }

    /*
     * Show the game instructions.
     *
     * If helpToggle is FALSE:
     * -- display the help text in the CENTRE of the gridPane (i.e. within cell 1,1)
     * -- use whatever GUI elements to get the job done!
     * -- set the helpToggle to TRUE
     * -- REMOVE whatever nodes are within the cell beforehand!
     *
     * If helpToggle is TRUE:
     * -- redraw the room image in the CENTRE of the gridPane (i.e. within cell 1,1)
     * -- set the helpToggle to FALSE
     * -- Again, REMOVE whatever nodes are within the cell beforehand!
     */
    public void showInstructions() {
        ObservableList<Node> children = gridPane.getChildren();
        for(Node n : children) {
            if(GridPane.getColumnIndex(n) == 1 && GridPane.getRowIndex(n) == 1 && n instanceof VBox) {
                gridPane.getChildren().remove(n);
                break;
            }
        }

        if(helpToggle){
            getRoomImage(); //get the image of the current room
            roomDescLabel.setPrefWidth(500);
            roomDescLabel.setPrefHeight(500);
            roomDescLabel.setTextOverrun(OverrunStyle.CLIP);
            roomDescLabel.setWrapText(true);
            help_roomPane = new VBox(roomImageView, roomDescLabel);
            help_roomPane.setPadding(new Insets(10));
            help_roomPane.setAlignment(Pos.TOP_CENTER);
            help_roomPane.setStyle("-fx-background-color: #000000;");

            gridPane.add(help_roomPane, 1, 1);
            stage.sizeToScene();
            helpToggle = false;
        } else {
            inst.setAlignment(Pos.CENTER);
            inst.setTextOverrun(OverrunStyle.CLIP);
            inst.setWrapText(true);
            inst.setStyle("-fx-text-fill: white;");
            inst.setFont(new Font("Arial", 14));

            help_roomPane2 = new VBox(inst);
            help_roomPane2.setPadding(new Insets(10));
            help_roomPane2.setAlignment(Pos.TOP_CENTER);
            help_roomPane2.setStyle("-fx-background-color: #000000;");

            gridPane.add(help_roomPane2, 1, 1);
            stage.sizeToScene();
            helpToggle = true;
            locationToggle = false;
        }
        changeRoomPane(); //change the background colors of the roomPane
    }

    /**
     * this method handles the event realted to the new locations button
     */

    public void addLocationsEvent(){
        mapButton.setOnAction(e ->{
            stopArticulation();
            showLocations();
        });
    }

    /**
     * This method handles the event related to the
     * help button.
     */
    public void addInstructionEvent() {
        helpButton.setOnAction(e -> {
            stopArticulation(); //if speaking, stop
            showInstructions();
        });
    }

    /**
     * This method handles the event related to the
     * options button.
     */
    public void addOptionsEvent() {
        optionsButton.setOnAction(e -> {
            gridPane.requestFocus();
            OptionsView optionsView = new OptionsView(saveButton, loadButton, helpButton, searchButton, this);
        });
    }

    /**
     * This method handles the event related to the
     * save button.
     */
    public void addSaveEvent() {
        saveButton.setOnAction(e -> {
            gridPane.requestFocus();
            SaveView saveView = new SaveView(this);
        });
    }
    private void addsearchButton() {
        searchButton.setOnAction(e -> {
            Stage searchStage = new Stage();
            int roomNumber = this.model.getPlayer().getCurrentRoom().getRoomNumber();
            SearchEvent searchEvent = new SearchEvent(searchStage, roomNumber);
            System.out.println(this.model.getDirectoryName());
            searchEvent.start();

            // Secondar
            new Thread(() -> {
                try {
                    Thread.sleep(10000); //10 second task
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }

                // Use Platform.runLater to update UI components
                Platform.runLater(() -> {
                    System.out.println(searchEvent.found);
                    if(searchEvent.found){
                        System.out.println(searchEvent.found);

                        if (roomNumber == 111){
                            AdventureObject ob1 = new NonConsumableObject("LIGHTER", "A Lighter.", this.model.player.getCurrentRoom());
                            this.model.player.getCurrentRoom().objectsInRoom.add(ob1);
                            this.model.player.inventory.add(ob1);
                            this.model.player.getCurrentRoom().objectsInRoom.remove(ob1);
                            updateItems();
                        }
                        else if (roomNumber == 112){
                            AdventureObject ob1 = new NonConsumableObject("LIZARD", "A Yellow Lizard.", this.model.player.getCurrentRoom());
                            this.model.player.getCurrentRoom().objectsInRoom.add(ob1);
                            this.model.player.inventory.add(ob1);
                            this.model.player.getCurrentRoom().objectsInRoom.remove(ob1);
                            updateItems();
                        }
                        else {
                            if (roomNumber == 114){
                                AdventureObject ob1 = new NonConsumableObject("UMBRELLA", "A Yellow Umbrella.", this.model.player.getCurrentRoom());
                                this.model.player.getCurrentRoom().objectsInRoom.add(ob1);
                                this.model.player.inventory.add(ob1);
                                this.model.player.getCurrentRoom().objectsInRoom.remove(ob1);
                                updateItems();
                            }
                        }

                    }


                });
            }).start();
        });
    }


    /**
     * This method handles the event related to the
     * load button.
     */
    public void addLoadEvent() {
        loadButton.setOnAction(e -> {
            gridPane.requestFocus();
            LoadView loadView = new LoadView(this);
        });
    }

    /**
     * This method changes the color of the roomPane
     * according to the current mode.
     */
    public void changeRoomPane() {
        switch (current_mode) {
            case "light" -> {
                help_roomPane.setStyle("-fx-background-color: #FFFFFF;");
                help_roomPane2.setStyle("-fx-background-color: #FFFFFF;");
                inst.setStyle("-fx-text-fill: black;");
                loc_roomPane.setStyle("-fx-background-color: #FFFFFF;");
                scT.setStyle("-fx-background: #FFFFFF; -fx-background-color:transparent;");
                title.setStyle("-fx-text-fill: black;");
            }
            case "dark", "monochrome" -> {
                help_roomPane.setStyle("-fx-background-color: #000000;");
                help_roomPane2.setStyle("-fx-background-color: #000000;");
                inst.setStyle("-fx-text-fill: white;");
                loc_roomPane.setStyle("-fx-background-color: #000000;");
                scT.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
                title.setStyle("-fx-text-fill: white;");

            }
            case "protanope" -> {
                help_roomPane.setStyle("-fx-background-color: #20B2AA;");
                help_roomPane2.setStyle("-fx-background-color: #20B2AA;");
                inst.setStyle("-fx-text-fill: gold;");
                loc_roomPane.setStyle("-fx-background-color: #20B2AA;");
                scT.setStyle("-fx-background: #20B2AA; -fx-background-color:transparent;");
                title.setStyle("-fx-text-fill: gold;");
            }
            case "deuteranope" -> {
                help_roomPane.setStyle("-fx-background-color: #EEE8AA;");
                help_roomPane2.setStyle("-fx-background-color: #EEE8AA;");
                inst.setStyle("-fx-text-fill: purple;");
                loc_roomPane.setStyle("-fx-background-color: #8A2BE2;");
                scT.setStyle("-fx-background: #8A2BE2; -fx-background-color:transparent;");
                title.setStyle("-fx-text-fill: gold;");
            }
            case "tritanope" -> {
                help_roomPane.setStyle("-fx-background-color: #F08080;");
                help_roomPane2.setStyle("-fx-background-color: #F08080;");
                inst.setStyle("-fx-text-fill: purple;");
                loc_roomPane.setStyle("-fx-background-color: #C71585;");
                scT.setStyle("-fx-background: #C71585; -fx-background-color:transparent;");
                title.setStyle("-fx-text-fill: orange;");
            }
        }
    }

    /**
     * This method articulates Room Descriptions
     */
    public void articulateRoomDescription() {
        String musicFile;
        String adventureName = this.model.getDirectoryName();
        String roomName = this.model.getPlayer().getCurrentRoom().getRoomName();

        if (!this.model.getPlayer().getCurrentRoom().getVisited()) musicFile = "./" + adventureName + "/sounds/" + roomName.toLowerCase() + "-long.mp3" ;
        else musicFile = "./" + adventureName + "/sounds/" + roomName.toLowerCase() + "-short.mp3" ;
        musicFile = musicFile.replace(" ","-");

        //Media sound = new Media(new File(musicFile).toURI().toString());

        //mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.play();
        mediaPlaying = true;

    }

    /**
     * This method stops articulations 
     * (useful when transitioning to a new room or loading a new game)
     */
    public void stopArticulation() {
        if (mediaPlaying) {
            //mediaPlayer.stop(); //shush!
            mediaPlaying = false;
        }
    }
}
