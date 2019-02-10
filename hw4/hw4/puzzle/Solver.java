package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;

public class Solver {

    private ArrayList<WorldState> solution;
    private MinPQ<SNode> minPQ;

    private class SNode implements Comparable<SNode>{
        private WorldState worldState;
        private int moves;
        private SNode prev;

        private SNode(WorldState worldState,int moves, SNode sNode){
            this.worldState = worldState;
            this.moves = moves;
            this.prev = sNode;
        }

        public WorldState getWorldState(){
            return worldState;
        }

        public int moves(){
            return moves;
        }

        public SNode prev(){
            return prev;
        }

        @Override
        public int compareTo(SNode sNode){
            return (this.moves + worldState.estimatedDistanceToGoal() - (sNode.moves+sNode.worldState.estimatedDistanceToGoal()));
        }
    }

    public Solver(WorldState initial){
        solution = new ArrayList<>();
        minPQ = new MinPQ<>();
        minPQ.insert(new SNode(initial,0,null));
        iterate();
    }

    public int moves(){
        return solution.size()-1;
    }

    public Iterable<WorldState> solution(){
        return solution;
    }

    public void iterate(){
        while(!minPQ.min().getWorldState().isGoal()){
            SNode w = minPQ.delMin();
            for(WorldState u : w.getWorldState().neighbors()){
                if(w.prev()==null || !(u.equals(w.prev().getWorldState()))){
                    minPQ.insert(new SNode(u,w.moves()+1,w));
                }
            }
        }
        SNode s = minPQ.min();
        while (s!=null){
            solution.add(0,s.getWorldState());
            s = s.prev();
        }
    }


}
