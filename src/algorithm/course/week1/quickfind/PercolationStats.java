package algorithm.course.week1.quickfind;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int trials;

    private static final double CONFIDENCE = 1.96;

    private double [] fractions;
    private int totalOfPositions;
    private double meanResult,stddevResult, confidenceLo,confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if(n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        this.trials = trials;
        this.fractions = new double[trials];
        this.totalOfPositions = n * n;

        int randomicNumber1 = 0;
        int randomicNumber2 = 0;

        for(int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while(!percolation.percolates()) {
                randomicNumber1 = StdRandom.uniform(1, n + 1);
                randomicNumber2 = StdRandom.uniform(1, n + 1);

                percolation.open(randomicNumber1, randomicNumber2);
            }
            double fraction = (double) percolation.numberOfOpenSites() / totalOfPositions;
            this.fractions[i] = fraction;
        }

        meanResult = StdStats.mean(fractions);
        stddevResult = StdStats.stddev(fractions);
        confidenceLo = meanResult - (CONFIDENCE*stddevResult)/Math.sqrt(trials);
        confidenceHi = meanResult + (CONFIDENCE*stddevResult)/Math.sqrt(trials);

    }

    // sample mean of percolation threshold
    public double mean() {

       return meanResult;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {

     return stddevResult;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {

       return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(size, trials);

        StdOut.println(String.format("%-23s = %f", "mean", percolationStats.mean()));
        StdOut.println(String.format("%-23s = %f", "stddev", percolationStats.stddev()));
        StdOut.println(String.format("%-23s = [%f, %f]", "95% confidence interval", percolationStats.confidenceLo(), percolationStats.confidenceHi()));
    }


}
