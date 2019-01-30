package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.Random;


public class Game implements Serializable{
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private int playerPosX;
    private int playerPosY;
    private TETile prevTile;
    private boolean toQuit;
    private static final String fileName = "save.txt";

    public Game(){}

    private String inputForMoment(){
        String s = "";
        StdDraw.clear();
        if(StdDraw.hasNextKeyTyped()){
            s += StdDraw.nextKeyTyped();
        }
        return s;
    }

    private String solicitCharInput() {
        String s = "";
        StdDraw.clear();
        while(true){
            if(StdDraw.hasNextKeyTyped()){
                s += StdDraw.nextKeyTyped();
                break;
            }
        }
        StdDraw.pause(1000);
        return s;
    }

    private String solicitNCharsInput() {
        String s = "";
        StdDraw.clear();
        while(true){
            if(StdDraw.hasNextKeyTyped()){
                s += StdDraw.nextKeyTyped();
                if(s.charAt(s.length()-1) == 'S' || s.charAt(s.length()-1) == 's'){
                    break;
                }
            }
        }
        StdDraw.pause(1000);
        return s;
    }

    private void saveAndQuit(TETile[][] world){
        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(world);
            output.close();
            file.close();
        }
        catch (IOException ex){
            System.out.println("IOException is caught");
        }
        return;
    }

    private TETile[][] load(){
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        File file = new File(fileName);
        if(file.length()==0){
            return world;
        }
        try{
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            world = (TETile[][]) in.readObject();
            in.close();
            fileInputStream.close();
        }
        catch (IOException ex){
            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        int i,j;
        for(i=0;i<WIDTH;i++){
            for(j=0;j<HEIGHT;j++){
                if(world[i][j].equals(Tileset.PLAYER)){
                    playerPosX = i;
                    playerPosY = j;
                    prevTile = Tileset.FLOOR;
                    break;
                }
            }
        }
        return world;
    }

    private void playerMoment(TETile[][] world, String moment, TETile prev){
        if(moment.equals("")){
            return;
        }
        else if(moment.equals("a") && playerPosX>0 && world[playerPosX-1][playerPosY].equals(Tileset.FLOOR)){
            toQuit = false;
            world[playerPosX][playerPosY] = prev;
            playerPosX--;
            prevTile = world[playerPosX][playerPosY];
            world[playerPosX][playerPosY] = Tileset.PLAYER;
        }
        else if(moment.equals("s") && playerPosY>0 && world[playerPosX][playerPosY-1].equals(Tileset.FLOOR)){
            toQuit = false;
            world[playerPosX][playerPosY] = prev;
            playerPosY--;
            prevTile = world[playerPosX][playerPosY];
            world[playerPosX][playerPosY] = Tileset.PLAYER;
        }
        else if(moment.equals("d")){
            toQuit = false;
            world[playerPosX][playerPosY] = prev;
            playerPosX++;
            prevTile = world[playerPosX][playerPosY];
            world[playerPosX][playerPosY] = Tileset.PLAYER;
        }
        else if(moment.equals("w") && playerPosY<HEIGHT-1 && world[playerPosX][playerPosY+1].equals(Tileset.FLOOR)){
            toQuit = false;
            world[playerPosX][playerPosY] = prev;
            playerPosY++;
            prevTile = world[playerPosX][playerPosY];
            world[playerPosX][playerPosY] = Tileset.PLAYER;
        }
        else if(moment.equals(":")){
            toQuit = true;
        }
        else if(moment.equals("q") && toQuit){
            saveAndQuit(world);
        }
    }

    private void displayStartScreen(){
        StdDraw.setCanvasSize(WIDTH*16, HEIGHT*16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.white);
        StdDraw.text(WIDTH/2,3*HEIGHT/4,"CS61B: THE GAME");
        StdDraw.text(WIDTH/2,HEIGHT/3,"New Game (N)");
        StdDraw.text(WIDTH/2,HEIGHT/3+2,"Load Game (L)");
        StdDraw.text(WIDTH/2,HEIGHT/3   +4,"Quit (Q)");
        StdDraw.show();

    }

    private void askForRandomSeed(){
        StdDraw.clear();
        StdDraw.clear(Color.black);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(WIDTH/2,HEIGHT/2,"Give Random Seed");
        StdDraw.show();
    }

    private void drawWorld(TETile[][] world){
        StdDraw.clear();
        StdDraw.clear(Color.black);
        ter.renderFrame(world);
        Font font = new Font("Monaco", Font.BOLD, 20);
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        String tile;
        if((int) x < WIDTH-1 && (int) y < HEIGHT-1){
            tile = world[(int)x][(int)y].description();
        }
        else{
            tile = "nothing";
        }
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);
        StdDraw.textLeft(1,HEIGHT-1,tile);
        StdDraw.show();
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        displayStartScreen();
        String userInput = solicitCharInput().toLowerCase();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = null;
        while (true){
            if(userInput.equals("n")){
                askForRandomSeed();
                String s = solicitNCharsInput();
                Integer seed = Integer.parseInt(s.substring(0,s.length()-1));

                Random RANDOM = new Random(seed);
                int maxTunnel =  RANDOM.nextInt(10) + 30;
                int maxLength = 20;
                world = generateWorld(RANDOM,maxTunnel,maxLength);
                break;

            }
            else if(userInput.equals("l")){
                world = load();
                break;

            }
            else if(userInput.equals("q")){
                saveAndQuit(world);
                return;
            }
            else{
                continue;
            }
        }
        while (true){
            drawWorld(world);
            StdDraw.pause(100);
            String moment = inputForMoment().toLowerCase();
            playerMoment(world,moment,prevTile);

        }


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
        int i;
        if(input.charAt(0) == 'l' || input.charAt(0) == 'L'){
            return load();
        }
        for(i=1;i<input.length();i++){
            if(Character.toString(input.charAt(i)).equals("s") || Character.toString(input.charAt(i)).equals("S")){
                break;
            }
        }
        String number = input.substring(1,i).toLowerCase();
        long seed = Long.parseLong(number);
        Random RANDOM = new Random(seed);
        int maxTunnel =  RANDOM.nextInt(10) + 30;
        int maxLength = 20;
        TETile[][] world = generateWorld(RANDOM,maxTunnel,maxLength);
        while(i!=input.length()){
            playerMoment(world,Character.toString(input.charAt(i)).toLowerCase(),prevTile);
            i++;
        }
        return world;
    }

    private TETile[][] generateWorld(Random RANDOM, int maxTunnel, int maxLength){
        TETile[][] world = new TETile[WIDTH][HEIGHT];
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

        while(true){
            int x = RANDOM.nextInt(WIDTH),y = RANDOM.nextInt(HEIGHT);
            if(world[x][y].equals(Tileset.FLOOR) && (x<WIDTH-1 && world[x+1][y].equals(Tileset.FLOOR)) &&
                    (y<HEIGHT-1 && world[x][y+1].equals(Tileset.FLOOR)) && (y>0 && world[x][y-1].equals(Tileset.FLOOR))
                    && (x>0 && world[x-1][y].equals(Tileset.FLOOR))){
                world[x][y] = Tileset.LOCKED_DOOR;
                break;
            }
        }
        while(true){
            int x = RANDOM.nextInt(WIDTH),y = RANDOM.nextInt(HEIGHT);
            if(world[x][y].equals(Tileset.FLOOR) && (x<WIDTH-1 && world[x+1][y].equals(Tileset.FLOOR)) &&
                    (y<HEIGHT-1 && world[x][y+1].equals(Tileset.FLOOR)) && (y>0 && world[x][y-1].equals(Tileset.FLOOR))
                    && (x>0 && world[x-1][y].equals(Tileset.FLOOR))){
                playerPosX = x;
                playerPosY = y;
                prevTile = world[x][y];
                world[x][y] = Tileset.PLAYER;
                break;
            }
        }
        return world;
    }


}

