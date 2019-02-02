package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int top;
    private int bottom;
    private int totalOpenSites;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    private int xyTo1D(int r, int c, int n){
        return  r*n + c;
    }

    public Percolation(int n){

        if(n<=0){
            throw new IllegalArgumentException();
        }
        grid = new boolean[n][n];
        int i,j;
        for(i=0;i<n;i++){
            for(j=0;j<n;j++){
                grid[i][j] = false;
            }
        }
        weightedQuickUnionUF = new WeightedQuickUnionUF(n*n + 2);
        top = n*n;
        bottom = n*n+1;
        totalOpenSites = 0;
    }

    public void open(int r, int c){
        if(grid[r][c]){
            return;
        }
        grid[r][c] = true;
        if(r-1>=0 && grid[r-1][c]){
            weightedQuickUnionUF.union(xyTo1D(r-1,c,grid.length),xyTo1D(r,c,grid.length));
        }
        if(r+1<grid.length && grid[r+1][c]){
            weightedQuickUnionUF.union(xyTo1D(r+1,c,grid.length),xyTo1D(r,c,grid.length));
        }
        if(c-1>=0 && grid[r][c-1]){

            weightedQuickUnionUF.union(xyTo1D(r,c-1,grid.length),xyTo1D(r,c,grid.length));
        }
        if(c+1<grid.length && grid[r][c+1]){

            weightedQuickUnionUF.union(xyTo1D(r,c+1,grid.length),xyTo1D(r,c,grid.length));
        }
        if(r==0){
            weightedQuickUnionUF.union(xyTo1D(r,c,grid.length),top);
        }
        if(r==grid.length-1){
            weightedQuickUnionUF.union(xyTo1D(r,c,grid.length),bottom);
        }
        totalOpenSites++;
    }

    public boolean isOpen(int r, int c){
        return grid[r][c];
    }

    public boolean isFull(int r, int c){
        return weightedQuickUnionUF.connected(xyTo1D(r,c,grid.length),top);
    }

    public int numberOfOpenSites(){
        return totalOpenSites;
    }

    public boolean percolates(){
        return weightedQuickUnionUF.connected(top,bottom);
    }

    public static void main(String args[]){
    }

}
