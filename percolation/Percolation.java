import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] grid;
	
	public Percolation(int n) {
		grid = new int[n][n];
		int cell_value = 1;
		for (int i=0; i < n ; i++) {
			for (int j=0; j<n; j++, cell_value++) {
				// grid[i][j]=i*10 +j +1;
				grid[i][j]=cell_value;
			}
		}
		
		
	};
	
	public void open(int row, int col) {
		
	};
	
	public boolean isOpen(int row, int col) {
		return false;
	};
	
	public boolean isFull(int row, int col) {
		return false;
	};
	
	public int numberOfOpenSites() {
		return 0;
	};
	
	public boolean percolates() {
		return false;
	};
	
	public static void main(String[] args) {
		Percolation perc = new Percolation(10);
		for (int i=0; i < 10 ; i++) {
			for (int j=0; j<10; j++) {
				StdOut.println(perc.grid[i][j]);
			}
		}
		
		
	};
}