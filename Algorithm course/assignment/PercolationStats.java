
/**
 *
 * @author Jota
 */
public class PercolationStats {

    private double[] attemps;
    private double temp;
    private double mean;

    /**
     * perform T independent computational experiments on an N-by-N grid
     *
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        attemps = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(N);
            int steps = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N) + 1;
                int column = StdRandom.uniform(N) + 1;
                if (!perc.isOpen(row, column)) {
                    perc.open(row, column);
                    steps++;
                }

            }
            attemps[i] = (double) steps / (N*N);
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
     /**
     * returns upper bound of the 95% confidence interval
     * @return confidenceLo
     */
    public double confidenceLo() {
        return mean - temp;
    }
     

    /**
     * returns lower bound of the 95% confidence interval
     *
     * @return confidenceHi
     */
    public double confidenceHi() {
        return mean + temp;
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 100);
        StdOut.print("mean = " + ps.mean() + "\n");
        StdOut.print("std dev = " + ps.stddev() + "\n");
        StdOut.print("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
        System.out.println("");
    }
}
