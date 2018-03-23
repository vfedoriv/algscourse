import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] grid;
<<<<<<< .merge_file_a01996
	private int grid_size;
	
	private boolean inRange(int row, int col) {
		if ((row <=0) || (row > grid_size)) throw new IllegalArgumentException("index out of bounds; expected: 0 < row <= " + grid_size + "; actual: " + row);
		if ((col <=0) || (col > grid_size)) throw new IllegalArgumentException("index out of bounds; expected: 0 < col <= " + grid_size + "; actual: " + col);
		return true;
	}
	
	public Percolation(int n) {
		
		if (!(n > 0)) throw new IllegalArgumentException("expected: argument should be > 0; actual: " + n);
		grid_size = n;
=======
	private int n;
	private int[] openArray;
	private int openSites = 0;
	private WeightedQuickUnionUF wQUF;
	
	private int xyTo1D(int row, int col) {
		// check row col bounds
		System.out.println("xyTo1D: " + (row*n + col + 1));
		return row*n + col + 1;
	};
	
	public Percolation(int n) {
		// add int overflow check
		// n > (Integer.MAX_VALUE-1)/2
		this.n = n;
>>>>>>> .merge_file_a00992
		grid = new int[n][n];
		openArray = new int[n*n];
		for (int i=0; i < n ; i++) {
			for (int j=0; j<n; j++) {
				grid[i][j]= i*n + j + 1;
			}
		}
		wQUF = new WeightedQuickUnionUF(n*n);
	};
	
	public void open(int row, int col) {
<<<<<<< .merge_file_a01996
		inRange(row, col);
=======
		int i = 0;
		if (isFull(row, col)) {
			System.out.println("open: row: " + row + " col : " + col );
			i = xyTo1D(row,col);
			openArray[i-1] = i;
			openSites++;
		if  ((i > 0) && (openArray[i] > 0)) wQUF.union(i-1, i);
		if  (((i-2) > 0) && (openArray[i-2] > 0))
			wQUF.union(i-1, i-2);
		if  (((i-n-1) > 0) && (openArray[i-n-1] > 0)) 
			wQUF.union(i-1, i-n-1);
		if  (((i+n-1) < n*n) && (openArray[i] > 0)) 
			wQUF.union(i-1, i+n-1);
		
		
		}
		
>>>>>>> .merge_file_a00992
		
	};
	
	public boolean isOpen(int row, int col) {
<<<<<<< .merge_file_a01996
		inRange(row, col);
=======
		if (openArray[xyTo1D(row,col)] > 0) return true;
>>>>>>> .merge_file_a00992
		return false;
	};
	
	public boolean isFull(int row, int col) {
<<<<<<< .merge_file_a01996
		inRange(row, col);
=======
		if (openArray[xyTo1D(row,col)] == 0) return true;
>>>>>>> .merge_file_a00992
		return false;
	};
	
	public int numberOfOpenSites() {
		return this.openSites;
	};
	
	public boolean percolates() {
		return false;
	};
	
	public boolean conected() {
		System.out.println(wQUF.connected(xyTo1D(0,0), xyTo1D(0,1)));
		System.out.println(wQUF.connected(xyTo1D(0,1), xyTo1D(1,1)));
		System.out.println(wQUF.connected(xyTo1D(1,1), xyTo1D(1,2)));
		System.out.println(wQUF.connected(xyTo1D(1,2), xyTo1D(2,1)));
		System.out.println(wQUF.connected(xyTo1D(1,2), xyTo1D(2,2)));
		System.out.println(wQUF.connected(xyTo1D(2,1), xyTo1D(3,1)));
		System.out.println(wQUF.connected(xyTo1D(2,2), xyTo1D(3,1)));
		System.out.println(wQUF.connected(xyTo1D(2,2), xyTo1D(3,2)));
		System.out.println(wQUF.connected(xyTo1D(2,2), xyTo1D(3,3)));
		System.out.println(wQUF.connected(xyTo1D(2,3), xyTo1D(3,3)));
		return wQUF.connected(xyTo1D(0,0), xyTo1D(2,2));
		
	};
	
	public static void main(String[] args) {
<<<<<<< .merge_file_a01996
		Percolation perc = new Percolation(10);
		for (int i=0; i < 10 ; i++) {
			for (int j=0; j<10; j++) {
				StdOut.println(perc.grid[i][j]);
			}
		}
		perc.isFull(10, 11);
=======
		int n = 10;
		Percolation perc = new Percolation(n);
		perc.open(0, 0);
		perc.open(1, 1);
		perc.open(2, 2);
		perc.open(3, 3);
		perc.open(0, 1);
		perc.open(1, 2);
		perc.open(2, 3);
		System.out.println(perc.conected());
//		for (int i=0; i < n ; i++) {
//			for (int j=0; j<n; j++) {
//				StdOut.println(perc.grid[i][j]);
//			}
//		}
>>>>>>> .merge_file_a00992
		
		
	};
}