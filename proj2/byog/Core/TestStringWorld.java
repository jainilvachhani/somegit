package byog.Core;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestStringWorld {
    static Game game = new Game();

    @Test
    public void testStringWorld(){
        assertEquals(game.playWithInputString("N543SWWWWAA"),game.playWithInputString("N543SWWWWAA"));
    }
}
