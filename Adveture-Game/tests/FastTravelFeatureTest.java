package tests;
import AdventureModel.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import Commands.FastTravelCommand;
import AdventureModel.AdventureGame;
import views.AdventureGameView;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javafx.stage.Stage;
public class FastTravelFeatureTest {
    private AdventureGame game;

    @BeforeEach
    void setUp() throws IOException {
        game = new AdventureGame("TinyGame");
        //how to set up the before each so that each test has the game variable?
    }
    @Test
    void FastTravelCommandTest() {
        Room currentRoom = game.player.getCurrentRoom();
        game.player.setCurrentRoom(game.getRooms().get(2));
        FastTravelCommand command = new FastTravelCommand(game,game.getRooms().get(1));
        command.execute();
        assertEquals(game.player.getCurrentRoom(),game.getRooms().get(1));
        assertEquals(game.getVisitedRooms().size(), 2);
    }

    /**
     * Test to ensure that the visited rooms are being tracked correctly, and that
     * FORCED rooms are not being counted.
     */
    @Test
    void getVisitedRoomsTest(){

        for (Room room: game.getRooms().values()){
            room.visit();
        }
        assertEquals(game.getVisitedRooms().size(), 5);
    }

    @Test
    void FastTravelCommandInvalidTest() {
        Room currentRoom = game.player.getCurrentRoom();
        game.player.setCurrentRoom(game.getRooms().get(2));
        FastTravelCommand command = new FastTravelCommand(game,game.getRooms().get(3));
        command.execute();
        assertEquals(game.player.getCurrentRoom(),game.getRooms().get(2));
        assertEquals(game.getVisitedRooms().size(), 2);
    }

    @Test
    void FastTravelCommandUndoTest() {
        Room currentRoom = game.player.getCurrentRoom();
        game.player.setCurrentRoom(game.getRooms().get(2));
        FastTravelCommand command = new FastTravelCommand(game,game.getRooms().get(1));
        command.execute();
        command.undo();
        assertEquals(game.player.getCurrentRoom(),game.getRooms().get(2));
        assertEquals(game.getVisitedRooms().size(), 1);
    }

    @Test
    void FastTravelManagerTest() {
        Room currentRoom = game.player.getCurrentRoom();
        game.player.setCurrentRoom(game.getRooms().get(2));
        FastTravelCommand command = new FastTravelCommand(game,game.getRooms().get(1));
        command.execute();
        command = new FastTravelCommand(game,game.getRooms().get(2));
        command.execute();

        //test the undo functionality
        assertEquals(game.getManager().getCommands().size(), 2);
        game.getManager().undo();
        assertEquals(game.getManager().getCommands().size(), 1);
        game.getManager().undo();
        assertEquals(game.getManager().getCommands().size(), 0);
    }
}


