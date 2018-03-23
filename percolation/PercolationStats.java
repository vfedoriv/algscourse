import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
	
	private Percolation perc;
	private int n;
	int[] array1;
	
	
	private int getRandomCell(int row, int col) {
		return 0;
		
	};
	
	public PercolationStats(int n, int trials) {
		array1 = new int[n*2];
		int row, col;
		perc = new Percolation(n);
		while (!perc.percolates()) {
			row = StdRandom.uniform(1,n);
			col = StdRandom.uniform(1,n);
			if (!perc.isFull(row, col)) perc.open(row, col);
		}
		StdOut.println("open sites: " + perc.numberOfOpenSites());
		
	};
	
	public double mean() {
		return 0;
	};
	
	public double stddev() {
		return 0;
	};
	
	public double confidenceLo() {
		return 0;
	};
	
	public double confidenceHi() {
		return 0;
	};
	
	public static void main(String[] args) {
		
	};
		
}