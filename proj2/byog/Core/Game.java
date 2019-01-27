package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.TooManyListenersException;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        Random RANDOM = new Random();
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        int maxTunnel =  RANDOM.nextInt(10) + 30;
        int maxLength = 20;
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.WALL;
            }
        }
        int currentRow = (int) Math.floor(RANDOM.nextDouble()*(WIDTH-1));
        int currentColumn = (int) Math.floor(RANDOM.nextDouble()*(HEIGHT-1));
        Integer[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Integer[] randomDirection;
        Integer[] lastDirection = {Integer.MAX_VALUE,Integer.MAX_VALUE};
        while(maxTunnel>0){
            do{
                randomDirection = directions[(int)Math.floor(RANDOM.nextDouble()*directions.length)];
            }
            while ((randomDirection[0].intValue() == -lastDirection[0].intValue() && randomDirection[1].intValue() == -lastDirection[1].intValue()) || (randomDirection[0].intValue() == lastDirection[0].intValue() && randomDirection[1].intValue() == lastDirection[1].intValue()));
            int randomLength = (int) Math.ceil(RANDOM.nextDouble()*maxLength);
            int tunnelLength = 0;
            while (tunnelLength<randomLength){
                if (((currentRow == 0) && (randomDirection[0] == -1)) ||
                        ((currentColumn == 0) && (randomDirection[1] == -1)) ||
                        ((currentRow == WIDTH - 1) && (randomDirection[0] == 1)) ||
                        ((currentColumn == HEIGHT - 1) && (randomDirection[1] == 1))) {
                    break;
                } else {
                    world[currentRow][currentColumn] = Tileset.FLOOR;
                    currentRow += randomDirection[0];
                    currentColumn += randomDirection[1];
                    tunnelLength++;
                }
            }
            if(tunnelLength>0){
                lastDirection = randomDirection;
                maxTunnel--;
            }
        }
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                boolean isSurrounded = world[x][y] == Tileset.FLOOR || (y>0 &&world[x][y-1] == Tileset.FLOOR) ||
                        (y < HEIGHT-1 && world[x][y+1] == Tileset.FLOOR) || (x < WIDTH-1 && world[x+1][y] == Tileset.FLOOR)||
                        (x>0 && world[x-1][y]== Tileset.FLOOR) || (x<WIDTH-1 && y<HEIGHT-1 && world[x+1][y+1]== Tileset.FLOOR) ||
                        (x>0 && y>0 && world[x-1][y-1]==Tileset.FLOOR) || (x>0 && y<HEIGHT-1 && world[x-1][y+1] == Tileset.FLOOR) ||
                        (x<WIDTH-1 && y>0 && world[x+1][y-1]==Tileset.FLOOR);
                if(!isSurrounded){
                    world[x][y] = Tileset.NOTHING;
                }
            }
        }
        ter.renderFrame(world);
        //TETile[][] finalWorldFrame = null;
        return world;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playWithInputString("sads");
    }
}

