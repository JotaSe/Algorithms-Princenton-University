/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm.course.week1.percolation;

/**
 *
 * @author Jota
 */
public class PercolationStats {

    double[] attemps;
    double temp;
    double mean;
    

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {

        attemps = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(N);
            int steps = 0;
            while (!perc.percolates()) {
                //System.out.println("trying "+i);
                int row = StdRandom.uniform(N) + 1;
                int column = StdRandom.uniform(N) + 1;
               // row = (row == N) ? N-1 : row;
                //column = (column == N) ? N-1 : column;
                if (!perc.isOpen(row, column)) {
                    // System.out.println("opening");
                    perc.open(row, column);
                    steps++;
                }

            }
            //todo
            //System.out.println("steps = " + steps);
            attemps[i] = (double) steps / (perc.startPoint);
        }

        calculate(T);
    }

    private void calculate(int T) {
        mean = mean();
        temp = (1.96 * stddev()) / Math.sqrt(T);
        
    }

    /**
     * sample mean of percolation threshold
     *
     * @return mean
     */
    public double mean() {
        return StdStats.mean(attemps);
    }

    /**
     * sample standard deviation of percolation threshold
     *
     * @return
     */
    public double stddev() {
        return StdStats.stddev(attemps);
    }
    // returns lower bound of the 95% confidence interval

    public double confidenceLo() {
        return mean  - temp;
    }
    // returns upper bound of the 95% confidence interval

    public double confidenceHi() {
        return mean + temp;
    }
    // test client, described below

    public static void main(String[] args) {
        // int N = new Integer(args[0]);
        //int T = new Integer(args[1]);
        PercolationStats ps = new PercolationStats(200 ,100);
        StdOut.print("mean = " + ps.mean() + "\n");
        StdOut.print("std dev = " + ps.stddev() + "\n");
        StdOut.print("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
        System.out.println("");
    }
}
