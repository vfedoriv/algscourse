// import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] grid;
	private int n;
	private int[] openArray; // as variant - realization with boolean array can save some memory
	private int openSites = 0;
	private WeightedQuickUnionUF wQUF;

	private boolean inBound(int row, int col) {
		if ((row <= 0) || (row > n))
			throw new IllegalArgumentException("index out of bounds; expected: 0 < row <= " + n + "; actual: " + row);
		if ((col <= 0) || (col > n))
			throw new IllegalArgumentException("index out of bounds; expected: 0 < col <= " + n + "; actual: " + col);
		return true;
	}

	private int xyTo1D(int row, int col) {
		inBound(row, col);
		return (row - 1) * n + col;
	};

	public Percolation(int n) {
		if (!(n > 0) || (n > 4096)) // max size depends of java heap size
			throw new IllegalArgumentException("expected: argument should be between 1 and 4096; actual: " + n);
		this.n = n;

		openArray = new int[n * n + 2];
		openArray[0] = 0; // virtual top site
		openArray[n * n + 1] = n * n + 1; // virtual bottom site

		grid = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = i * n + j + 1;
			}
		}
		// first element - virtual top site, last - virtual bottom site
		wQUF = new WeightedQuickUnionUF(n * n + 2);
	};

	public void open(int row, int col) {
		inBound(row, col);
		int i = 0;
		if (isFull(row, col)) {
//			System.out.println("open: row: " + row + " col : " + col);
			i = xyTo1D(row, col);
			openArray[i] = i;
			openSites++;

			if ((i > 1) && (openArray[i - 1] > 0))
				wQUF.union(i - 1, i); // left neighbor

			if ((i > 0) && (openArray[i + 1] > 0)) // right neighbor
				wQUF.union(i + 1, i);

			if (((i - n) > 0) && (openArray[i - n] > 0)) // top neighbor
				wQUF.union(i - n, i);
			else
				if ((i - n) <= 0) wQUF.union(0, i); // connect to virtual top site

			if (((i + n) < n * n) && (openArray[i + n] > 0)) // bottom neighbor
				wQUF.union(i + n, i);
			else
				if ((i + n) >= n * n) wQUF.union(n * n + 1, i); // connect to virtual bottom site
		}

	};

	public boolean isOpen(int row, int col) {
		inBound(row, col);
		if (openArray[xyTo1D(row, col)] > 0)
			return true;
		return false;
	};

	public boolean isFull(int row, int col) {
		inBound(row, col);
		if (openArray[xyTo1D(row, col)] == 0)
			return true;
		return false;
	};

	public int numberOfOpenSites() {
		return this.openSites;
	};

	public boolean percolates() {
		if (wQUF.connected(0, n * n + 1))
			return true;
		return false;
	};

//	public static void main(String[] args) {
//
//	};
}