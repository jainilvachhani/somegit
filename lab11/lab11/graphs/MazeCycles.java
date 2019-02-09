package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        int i;
        for(i=0;i<maze.V();i++){
            if(!marked[i]){
                announce();
                if(isCyclicUtil(i,marked,-1)){
                    return;
                }
            }
        }
    }

    // Helper methods go here

    private boolean isCyclicUtil(int v, boolean marked[], int parent){
        marked[v] = true;
        announce();
        for(int i : maze.adj(v)){
            if(!marked[i]){
                if(isCyclicUtil(i,marked,v)){
                    edgeTo[i] = v;
                    announce();
                    return true;
                }
            }
            else if(i!=parent){
                edgeTo[i] = v;
                announce();
                return true;
            }
        }
        return false;
    }
}

