import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] grid;
	private int grid_size;
	
	private boolean inRange(int row, int col) {
		if ((row <=0) || (row > grid_size)) throw new IllegalArgumentException("index out of bounds; expected: 0 < row <= " + grid_size + "; actual: " + row);
		if ((col <=0) || (col > grid_size)) throw new IllegalArgumentException("index out of bounds; expected: 0 < col <= " + grid_size + "; actual: " + col);
		return true;
	}
	
	public Percolation(int n) {
		
		if (!(n > 0)) throw new IllegalArgumentException("expected: argument should be > 0; actual: " + n);
		grid_size = n;
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
		inRange(row, col);
		
	};
	
	public boolean isOpen(int row, int col) {
		inRange(row, col);
		return false;
	};
	
	public boolean isFull(int row, int col) {
		inRange(row, col);
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
		perc.isFull(10, 11);
		
		
	};
}