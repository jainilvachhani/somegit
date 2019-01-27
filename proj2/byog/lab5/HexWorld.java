package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import javafx.geometry.Pos;

import java.util.Random;
/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private static final int SIZE = 3;

    private static final Random RANDOM = new Random();

    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }

        return s + 2 * effectiveI;
    }

    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = t;
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);

        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            case 3: return Tileset.FLOOR;
            case 4: return Tileset.GRASS;
            case 5: return Tileset.WATER;
            case 6: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    private static void addRandomHexagon(TETile[][] world, Position p, int s){
        addHexagon(world,p,s,randomTile());
    }
    private static void addRandomHexagon(TETile[][] world, Position[] p, int s){
        for(int i=0;i<p.length;i++){
            addHexagon(world,p[i],s,randomTile());
        }
    }

    private static Position getLeftNeighbour(Position pos, int size){
        return new Position(pos.x - size*2 + 1,pos.y + size);
    }

    private static Position getRightNeighbour(Position pos, int size){
        return new Position(pos.x + size*2 - 1,pos.y + size);
    }

    public static void main(String args[]){
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position[] firstRow = new Position[1];
        firstRow[0] =  new Position((WIDTH/2)-1,0);
        addRandomHexagon(world,firstRow,SIZE);
        Position secondRow[] = new Position[2];
        secondRow[0] = getLeftNeighbour(firstRow[0],SIZE);
        secondRow[1] = getRightNeighbour(firstRow[0],SIZE);
        addRandomHexagon(world,secondRow,SIZE);
        Position thirdTow[] = new Position[4];
        thirdTow[0] = getLeftNeighbour(secondRow[0],SIZE);
        thirdTow[1] = getRightNeighbour(secondRow[0],SIZE);
        thirdTow[2] = getLeftNeighbour(secondRow[1],SIZE);
        thirdTow[3] = getRightNeighbour(secondRow[1],SIZE);
        addRandomHexagon(world,thirdTow,SIZE);
        Position fourthRow[] = new Position[2];
        fourthRow[0] = getLeftNeighbour(thirdTow[1],SIZE);
        fourthRow[1] = getRightNeighbour(thirdTow[1],SIZE);
        addRandomHexagon(world,fourthRow,SIZE);
        Position[] fifthRow = new Position[3];
        fifthRow[0] = getLeftNeighbour(fourthRow[0],SIZE);
        fifthRow[1] = getRightNeighbour(fourthRow[0],SIZE);
        fifthRow[2] = getRightNeighbour(fourthRow[1],SIZE);
        addRandomHexagon(world,fifthRow,SIZE);
        Position sixthRow[] = new Position[2];
        sixthRow[0] = getLeftNeighbour(fifthRow[1],SIZE);
        sixthRow[1] = getRightNeighbour(fifthRow[1],SIZE);
        addRandomHexagon(world,sixthRow,SIZE);
        Position[] seventhRow = new Position[3];
        seventhRow[0] = getLeftNeighbour(sixthRow[0],SIZE);
        seventhRow[1] = getRightNeighbour(sixthRow[0],SIZE);
        seventhRow[2] = getRightNeighbour(sixthRow[1],SIZE);
        addRandomHexagon(world,seventhRow,SIZE);
        Position eighthRow[] = new Position[2];
        eighthRow[0] = getLeftNeighbour(seventhRow[1],SIZE);
        eighthRow[1] = getRightNeighbour(seventhRow[1],SIZE);
        addRandomHexagon(world,eighthRow,SIZE);
        Position ninthRow[] = new Position[1];
        ninthRow[0] = getRightNeighbour(eighthRow[0],SIZE);
        addRandomHexagon(world,ninthRow,SIZE);

        ter.renderFrame(world);
    }
}
