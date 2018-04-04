//public class FastCollinearPoints {
//   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
//   public           int numberOfSegments()        // the number of line segments
//   public LineSegment[] segments()      

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
	
	public FastCollinearPoints(Point[] points) {
		if (points==null) throw new IllegalArgumentException("argument cannot be null");
		Arrays.sort(points);
		for (int i=0; i < points.length; i++) {
			if (points[i]== null)  throw new IllegalArgumentException("array item cannot be null");
			if (i> 0) {
				if (points[i-1].equals(points[i])) throw new IllegalArgumentException("array cannot contain duplicates");
			}
		}
	}
	
	public int numberOfSegments() {
		return 0;
	}
	
	public LineSegment[] segments() {
		return null;
	}
	
	public static void main(String[] args) {
	
		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}
	
		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();
	
		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}


}