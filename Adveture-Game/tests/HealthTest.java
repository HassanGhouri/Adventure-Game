package tests;

import AdventureModel.AdventureGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HealthTest {
    @Test
    public void roomDeathTest(){
        AdventureGame game = new AdventureGame("TinyGame");
        game.player.setHealth(5);
        game.movePlayer("down");
        assertEquals(0, game.player.getHealth());
    }

    @Test
    public void roomDamageTest(){
        AdventureGame game = new AdventureGame("TinyGame");
        game.player.setHealth(100);
        game.movePlayer("down");
        assertEquals(77.0, game.player.getHealth());
    }

    @Test
    public void roomHealingTest(){
        AdventureGame game = new AdventureGame("TinyGame");
        game.player.setHealth(5);
        game.movePlayer("in");
        assertEquals(45.0, game.player.getHealth());
    }

    @Test
    public void roomHealingOnceHealthTest(){
        AdventureGame game = new AdventureGame("TinyGame");
        game.player.setHealth(5);
        game.movePlayer("in");
        game.movePlayer("out");
        game.movePlayer("in");
        game.movePlayer("out");
        game.movePlayer("in");
        game.movePlayer("out");
        assertEquals(45.0, game.player.getHealth());
    }
}


