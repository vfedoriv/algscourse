//public class BruteCollinearPoints {
//   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
//   public           int numberOfSegments()        // the number of line segments
//   public LineSegment[] segments()     

import java.util.Arrays;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
	
	private LineSegment[] segmentsArray;
	private int index_array = 0;
	Point p, q, r, s, loSegmentPoint;
	double current_slope;
	

	public BruteCollinearPoints(Point[] points) {
		if (points==null) throw new IllegalArgumentException("argument cannot be null");
		// Arrays.sort(points);
		for (int i=0; i < points.length; i++) {
			if (points[i]== null)  throw new IllegalArgumentException("array item cannot be null");
			if (i> 0) {
				if (points[i-1].equals(points[i])) throw new IllegalArgumentException("array cannot contain duplicates");
			}
		}
		// segmentsArray = new LineSegment[points.length];
		segmentsArray = new LineSegment[4096];
		current_slope = points[0].slopeTo(points[1]);
		loSegmentPoint = points[0];
		
		for (int i=0; i < points.length; i++) {
			p = points[i];
			for (int j=0; j < points.length; j++) {
			// for (int j=i+1; j < points.length; j++) {
				q = points[j];
				
				if (!(current_slope==p.slopeTo(q))) {
					loSegmentPoint = p;
				}
				
				current_slope = p.slopeTo(q);
				
				// for (int k=j+1; k < points.length; k++) {
				for (int k=0; k < points.length; k++) {
					r = points[k];
					// for (int l=k+1; l < points.length; l++) {
					for (int l=0; l < points.length; l++) {
						s = points[l];
						System.out.println("====================================");
						System.out.println("p=" + p);
						System.out.println("q=" + q);
						System.out.println("p.slopeTo(q) : " + p.slopeTo(q));
						System.out.println("r=" + r);
						System.out.println("p.slopeTo(r) : " + p.slopeTo(r));
						System.out.println("s=" + s);
						System.out.println("p.slopeTo(s) : " + p.slopeTo(s));
						if ((p.slopeTo(q)==p.slopeTo(r)) && (p.slopeTo(q)==p.slopeTo(s))) {
							
							segmentsArray[index_array] = new LineSegment(p, s);
							index_array++;
				
//							if (p.slopeTo(s)==current_slope) {
//								segmentsArray[index_array] = new LineSegment(loSegmentPoint, s);
//							} else {
//								index_array++;
//								segmentsArray[index_array] = new LineSegment(p, s);
//							}

						}
					}
				}
			}
			
		} // external for
		
		
		
	}
	
//	private void extendSegment() {
//		
//	}
	
	public int numberOfSegments() {
		if (segmentsArray[0]==null) return 0;
		return index_array+1;
	}
	
	public LineSegment[] segments() {
		return Arrays.copyOf(segmentsArray, numberOfSegments());
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
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}

}