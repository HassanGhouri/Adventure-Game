package AdventureModel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class handles the GUI related to the search Button.
 */

public class SearchEvent {
    /**
     *  The Stage for the search GUI.
     */
    public Stage primaryStage;
    /**
     * The picture for the search GUI.
     */
    public String background;
    /**
     * Boolean storing if the user found the item or not.
     */
    public boolean found;
    /**
     * List to store the different components for the search GUI.
     */
    public Object[] roomInfo;

    private Object[] room1 = {"111.png", "Find the Lighter.", 1048.0, 1131.0, 402.0, 465.0};
    private Object[] room3 = {"112.png", "Find the Lizard.", 200.0, 268.0, 460.0, 540.0};
    private Object[] room5 = {"114.png", "Find the Yellow Umbrella. ", 196.0, 306.0, 263.0, 363.0};

    /**

     AdvGameRoom constructor.*
     @param primaryStage: The Stage for the search GUI.
     @param roomnumber: The number of the room.*/
    public SearchEvent(Stage primaryStage, int roomnumber){
        this.primaryStage = primaryStage;

        if (roomnumber == 111){
            this.roomInfo = room1;
        }
        else if (roomnumber == 112){
            this.roomInfo = room3;
        }
        else{
            this.roomInfo = room5;
        }

    }

    public void start() {

        // Setup GUI
        System.out.println(this.roomInfo[0]);
        Image backgroundImage = new Image("Games/Liminal/room-images/" + (String) this.roomInfo[0]);
        ImageView backgroundImageView = new ImageView(backgroundImage);
        StackPane root = new StackPane(backgroundImageView);
        Scene scene = new Scene(root, backgroundImage.getWidth(), backgroundImage.getHeight());

        primaryStage.setTitle((String) this.roomInfo[1]);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Mouse click eventhandler
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();

            //Check if mouse click is in right area
            Double validXmin = (Double) this.roomInfo[2];
            Double validXmax = (Double) this.roomInfo[3];
            Double validYmin = (Double) this.roomInfo[4];
            Double validYmax = (Double) this.roomInfo[5];

            if (validXmin < mouseX && mouseX < validXmax && validYmin < mouseY && mouseY < validYmax) {
                this.found = true;
                primaryStage.close();
            }
            else{
                primaryStage.close();
            }

        });

    }

}

