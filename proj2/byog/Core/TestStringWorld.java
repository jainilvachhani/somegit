package byog.Core;

import byog.TileEngine.TETile;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestStringWorld {
    static Game game = new Game();

    @Test
    public void testStringWorld(){
        TETile[][] world  = game.playWithInputString("n7286647390967289332swdwwdwssdsasd");
        TETile[][] world2 = game.playWithInputString("n7286647390967289332swdwwdwssdsas:q");
        world2 = game.playWithInputString("ld");
        assertEquals(world,world2);
    }
}
