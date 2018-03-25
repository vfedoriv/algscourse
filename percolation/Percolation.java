import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] grid;
	private int n; // grid size
	private boolean[] openArray;
	private int openSites = 0;
	private WeightedQuickUnionUF wQUF;
	private int virtualTop;
	private int virtualBottom;

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
		openArray = new boolean[n * n + 1];
		virtualTop = 0;
		virtualBottom = n * n + 1;

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
		if (!isOpen(row, col)) {
			i = xyTo1D(row, col);
			openArray[i] = true;
			openSites++;

			if ((i > 1) && (openArray[i - 1] == true)) // left neighbor
				if (col > 1) wQUF.union(i - 1, i);

			if ((i > 0) && (i < n*n) && (openArray[i + 1] == true)) // right neighbor
				if (col < n) wQUF.union(i + 1, i);

			if (((i - n) > 0) && (openArray[i - n] == true)) // top neighbor
				wQUF.union(i - n, i);
			else
				if ((i - n) <= 0) wQUF.union(virtualTop, i); // connect to virtual top site

			if (((i + n) <= n * n) && (openArray[i + n] == true)) // bottom neighbor
				wQUF.union(i + n, i);
			else
				if ((i + n) > n * n) wQUF.union(virtualBottom, i); // connect to virtual bottom site
		}

	};

	public boolean isOpen(int row, int col) {
		inBound(row, col);
		return openArray[xyTo1D(row, col)];
	};

	public boolean isFull(int row, int col) {
		inBound(row, col);
		return wQUF.connected(xyTo1D(row, col), virtualTop);
	};

	public int numberOfOpenSites() {
		return this.openSites;
	};

	public boolean percolates() {
		return wQUF.connected(virtualTop, virtualBottom);
	};

	public static void main(String[] args) {
		Percolation perc = new Percolation(3);
		perc.open(1, 1);
		perc.open(2, 2);
		perc.open(2, 3);
		perc.open(3, 3);
		perc.open(2, 1);
		StdOut.println("percolates: " + perc.percolates());
	};
}