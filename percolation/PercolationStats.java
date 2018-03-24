import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private Percolation perc;
	private int grid_size;
	private int experiments_count;
	double[] fractions;
	
	private void openRandomSite() {
		int row = StdRandom.uniform(1,grid_size+1);
		int col = StdRandom.uniform(1,grid_size+1);
		if (!perc.isOpen(row, col))
			perc.open(row, col);
	};
	
	public PercolationStats(int n, int trials) {
		if ((n <= 0) || (trials <=0))
			throw new IllegalArgumentException("n and trials should be > 0; " + " actual: n = " + n + " ; trials = " + trials);
		grid_size = n;
		experiments_count = trials;
		fractions = new double[experiments_count];
		
		for(int count = 0; count < experiments_count; count++) {
			perc = new Percolation(grid_size);
			while (!perc.percolates()) {
				openRandomSite();
			}
			fractions[count] = (double) perc.numberOfOpenSites()/(grid_size*grid_size);
		}
	};
	
	public double mean() {
		return StdStats.mean(fractions);
	};
	
	public double stddev() {
		if (experiments_count == 1) return Double.NaN;
		return StdStats.stddev(fractions);
	};
	
	public double confidenceLo() {
		return mean() - 1.96*stddev()/Math.sqrt(experiments_count);
	};
	
	public double confidenceHi() {
		return mean() + 1.96*stddev()/Math.sqrt(experiments_count);
	};
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(n, T);
        StdOut.println("mean                    = " + percStats.mean());
        StdOut.println("stddev                  = " + percStats.stddev());
        StdOut.println("95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
	};
		
}