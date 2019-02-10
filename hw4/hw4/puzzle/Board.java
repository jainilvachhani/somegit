package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;

public class Board implements WorldState {

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    private ArrayList<Integer> board;
    private int BLANK = 0;
    private int N;

    public Board(int [][] tiles){
        board = new ArrayList<>();
        int i,j;
        N = tiles.length;
        for(i=0;i<tiles.length;i++){
            for(j=0;j<tiles[0].length;j++){
                board.add(tiles[i][j]);
            }
        }
    }

    public int tileAt(int i, int j){
        if(i<0 || j<0 || i>N || j>N){
            throw new IndexOutOfBoundsException();
        }
        return board.get(i*N+ j);
    }

    public int size(){
        return N;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming(){
        int i,cnt=0;
        for(i=0;i<N*N-1;i++){
            if(board.get(i)!=i+1){
                cnt++;
            }
        }
        return cnt;
    }

    public int manhattan(){
        int i,cnt=0;
        for(i=0;i<N*N-1;i++){
            cnt += singleManhattan(board.get(i),i/N,i - (i*N));
        }
        return cnt;
    }

    private int singleManhattan(int expected, int expectedRow, int expectedCol){
        int col = expected/N;
        int row = expected - (col*N);
        return Math.abs(col - expectedCol) + Math.abs(row - expectedRow);
    }

    public int estimatedDistanceToGoal(){
        return manhattan();
    }

    public boolean equals(Object y){
        if(this==y){
            return true;
        }
        if(y==null || y.getClass() != this.getClass()){
            return false;
        }

        Board b = (Board) y;
        if(this.N != b.N){
            return false;
        }

        int i,j;
        for(i=0;i<N;i++){
            for(j=0;j<N;j++){
                if(board.get(i*N + j)!=b.tileAt(i,j)){
                    return false;
                }
            }
        }
        return true;
    }
}
