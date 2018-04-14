import java.util.LinkedList;

public class Board {
	
	private int[][] tiles;
	private int[][] goal;
	private int dimension, hamming, manhattan;
	
	public Board(int[][] blocks) {
		
		int row, column;
		hamming = 0;
		manhattan = 0;
		
		if ((blocks == null) || (blocks.length == 0)) throw new IllegalArgumentException("argument cannot be null or 0 lenght");
		dimension = blocks.length;
		
		// fill goals
		goal = new int[dimension][dimension];
		int arr_value = 1;
	    for (int i = 0; i < dimension; i++) {
	    	for (int j = 0; j < dimension; j++) {
	    		goal[i][j]= arr_value++;
	    	}
	    }
	    goal[dimension-1][dimension-1]= 0;
		
		// copy argument into inner array
		tiles = new int[dimension][];
	    for (int i = 0; i < dimension; i++) {
	        tiles[i] = blocks[i].clone();
	    }
	    
	    // calculate hamming and manhattan
	    for (int i = 0; i < dimension; i++) {
	    	for (int j = 0; j < dimension; j++) {
	    		if (tiles[i][j]==0) continue;
	    		row = (tiles[i][j]-1) / dimension;
    		    column = (tiles[i][j]-1) % dimension;
    			manhattan = manhattan + Math.abs(i-row)+ Math.abs(j-column);
	    		if (tiles[i][j] != goal[i][j]) {
	    			hamming++;
	    		}

	    	}
	    }
	}
	
	public int dimension() {
		return dimension;
	}
	
	public int hamming() {
		return hamming;
	}
	
	public int manhattan() {
		return manhattan;
	}
	
	public boolean isGoal() {
		return (hamming == 0);
	}
	
	public Board twin() {
        int[][] twinBlock = new int[dimension][];
	    for (int i = 0; i < dimension; i++) {
	    	twinBlock[i] = tiles[i].clone();
	    }
	    int i = 0;
	    int j = 0;
	    while (twinBlock[i][j] == 0 || twinBlock[i][j + 1] == 0) {
	    	j++;
	    	if (j >= dimension - 1) {
	    		i++;
	    		j = 0;
	    	}
	    }
	    return new Board(shiftSpace(i, j, i, j + 1));
	}
	
	public boolean equals(Object y) {
		if (this == y) return true;
		if (y == null) return false;
		if (this.getClass() != y.getClass()) return false;
		Board other = (Board) y;
		return (
				(this.dimension()==other.dimension()) &&
				(this.hamming()==other.hamming()) &&
				(this.manhattan()==other.manhattan())			
				);
	}
	
	public Iterable<Board> neighbors() {
		int spaceRow = 0;
		int spaceCol = 0;
		LinkedList<Board> neighbors = new LinkedList<Board>();

		exit_if_found:  // label for exit from nested loop 
		for (int i=0; i<dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (tiles[i][j] == 0) {
					spaceRow = i;
					spaceCol = j;
					break exit_if_found;
				}
			}
		}
        if (spaceRow > 0) neighbors.add(new Board(shiftSpace(spaceRow, spaceCol, spaceRow - 1, spaceCol)));
        if (spaceRow < dimension() - 1) neighbors.add(new Board(shiftSpace(spaceRow, spaceCol, spaceRow + 1, spaceCol)));
        if (spaceCol > 0) neighbors.add(new Board(shiftSpace(spaceRow, spaceCol, spaceRow, spaceCol - 1)));
        if (spaceCol < dimension() - 1) neighbors.add(new Board(shiftSpace(spaceRow, spaceCol, spaceRow, spaceCol + 1)));
        return neighbors;
	}
	
    private int[][] shiftSpace(int row1, int col1, int row2, int col2) {
    	int[][] shiftArray = new int[dimension][dimension];
        for (int row = 0; row < dimension; row++)
        	for (int col = 0; col < dimension; col++)
        		shiftArray[row][col] = tiles[row][col];
        int tmp = shiftArray[row1][col1];
        shiftArray[row1][col1] = shiftArray[row2][col2];
        shiftArray[row2][col2] = tmp;
        return shiftArray;
    }
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(tiles.length + "\n");
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				s.append(String.format("%2d ", tiles[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		int[][] arr = {	{1,2,3,4},
						{5,6,7,8},
						{9,10,0,12},
						{13,14,15,11}
					};
		
		Board brd = new Board(arr);
		System.out.println(brd.toString());
	}
}