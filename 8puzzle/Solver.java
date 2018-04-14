import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	
    private SearchNode lastNode;
    
	public Solver(Board initial) {
		
		MinPQ<SearchNode> searchNodes = new MinPQ<SearchNode>();
        searchNodes.insert(new SearchNode(initial));

        MinPQ<SearchNode> twinNodes = new MinPQ<SearchNode>();
        twinNodes.insert(new SearchNode(initial.twin()));

        while(true) {
        	lastNode = expand(searchNodes);
            if ((lastNode != null) || (expand(twinNodes) != null)) return;
        }
		
	}
	
	public boolean isSolvable() {
		return (lastNode != null);
	}
	
	public int moves() {
		return isSolvable() ? lastNode.numMoves : -1;
	}
	
	public Iterable<Board> solution() {
		if (!isSolvable()) return null;
		Stack<Board> nodes = new Stack<Board>();
		while(lastNode != null) {
            nodes.push(lastNode.board);
            lastNode = lastNode.previous;
        }
        return nodes;
	}
	
	
	private class SearchNode implements Comparable<SearchNode> {
        private SearchNode previous;
        private Board board;
        private int numMoves = 0;

        public SearchNode(Board board) {
            this.board = board;
        }

        public SearchNode(Board board, SearchNode previous) {
            this.board = board;
            this.previous = previous;
            this.numMoves = previous.numMoves + 1;
        }

        public int compareTo(SearchNode node) {
        	int val = (this.board.manhattan() + this.numMoves) - (node.board.manhattan() + node.numMoves);
        	if (val == 0) return this.board.manhattan() - node.board.manhattan();
            return val;
        }

    }
	
    private SearchNode expand(MinPQ<SearchNode> nodes) {
        if(nodes.isEmpty()) return null;
        SearchNode bestNode = nodes.delMin();
        if (bestNode.board.isGoal()) return bestNode;
        for (Board neighbor : bestNode.board.neighbors()) {
            if (bestNode.previous == null || !neighbor.equals(bestNode.previous.board)) {
                nodes.insert(new SearchNode(neighbor, bestNode));
            }
        }
        return null;
    }

	
	public static void main(String[] args) {
	    // create initial board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	
	    // solve the puzzle
	    Solver solver = new Solver(initial);
	
	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}

}