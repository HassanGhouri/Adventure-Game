import AdventureModel.AdventureGame;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAdventureTest {
    @Test
    void getCommandsTest() throws IOException {
        AdventureGame game = new AdventureGame("TinyGame");
        String commands = game.player.getCurrentRoom().getCommands();
        assertTrue((commands.contains("DOWN")));
        assertTrue((commands.contains("NORTH")));
        assertTrue((commands.contains("IN")));
        assertTrue((commands.contains("WEST")));
        assertTrue((commands.contains("UP")));
        assertTrue((commands.contains("SOUTH")));
    }

    @Test
    void getObjectString() throws IOException {
        AdventureGame game = new AdventureGame("TinyGame");
        String objects = game.player.getCurrentRoom().getObjectString();
        assertEquals("a water bird", objects);
    }

}
