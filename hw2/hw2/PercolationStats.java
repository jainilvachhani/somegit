package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double cnt;
    private double mean;
    private double stdDev;
    private double confidenceLow;
    private double confidenceHigh;
    private double[] numberOfOpenSites;

    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N<=0 || T<=0){
            throw new IllegalArgumentException();
        }
        int i;
        numberOfOpenSites = new double[T];
        for(i=0;i<T;i++){
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()){
                percolation.open(StdRandom.uniform(N),StdRandom.uniform(N));
            }
            cnt += percolation.numberOfOpenSites();
            numberOfOpenSites[i] = percolation.numberOfOpenSites()/(N*N);
        }
        mean = StdStats.mean(numberOfOpenSites);
        stdDev = StdStats.stddev(numberOfOpenSites);
        confidenceLow = mean - ((1.96* Math.sqrt(stdDev))/(Math.sqrt(T)));
        confidenceHigh = mean + ((1.96* Math.sqrt(stdDev))/(Math.sqrt(T)));
    }

    public double mean(){
        return mean;
    }

    public double stddev(){
        return stdDev;
    }

    public double confidenceLow(){
        return confidenceLow;
    }

    public double confidenceHigh(){
        return confidenceHigh;
    }
}
