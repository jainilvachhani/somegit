package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        marked[v] = true;
        announce();
        distTo[v] = 0;

        Queue<Integer> bfsq = new LinkedList<>();
        bfsq.add(v);
        while (!bfsq.isEmpty()){
            int u = bfsq.peek();
            if(u==t){
                targetFound = true;
            }
            if(targetFound){
                return;
            }
            bfsq.remove();
            for(int x : maze.adj(u)){
                if(marked[x]==false){
                    marked[x] = true;
                    distTo[x] = distTo[u]+1;
                    bfsq.add(x);
                    edgeTo[x] = u;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
}

