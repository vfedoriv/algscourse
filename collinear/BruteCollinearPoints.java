import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
	private LineSegment[] segmentsArray;
	private int index_array = 0;
	private Point[] pointsArray;

	public BruteCollinearPoints(Point[] points) {
		Point p, q, r, s;
		
		if (points==null) throw new IllegalArgumentException("argument cannot be null");
		
		pointsArray = Arrays.copyOf(points, points.length);

		for (int i=0; i < pointsArray.length; i++) {
			if (pointsArray[i]== null)  throw new IllegalArgumentException("array item cannot be null");
		}
		
		Arrays.sort(pointsArray);
		for (int i=0; i < pointsArray.length-1; i++) {
			if (pointsArray[i].equals(pointsArray[i+1])) throw new IllegalArgumentException("array cannot contain duplicates");
		}
		
		
		segmentsArray = new LineSegment[pointsArray.length*pointsArray.length];
		for (int i=0; i < pointsArray.length ; i++) {
			p = pointsArray[i];
			
			for (int j=i+1; j < pointsArray.length ; j++) {
				q = pointsArray[j];
				for (int k=j+1; k < pointsArray.length; k++) {
					r = pointsArray[k];
					if (!(p.slopeTo(q)==p.slopeTo(r))) continue;
					for (int l=k+1; l < pointsArray.length; l++) {
						s = pointsArray[l];
						if ((p.slopeTo(q)==p.slopeTo(r)) && (p.slopeTo(q)==p.slopeTo(s))) {
							segmentsArray[index_array] = new LineSegment(p, s);
							index_array++;
						};
					}
				}
			}
		}
		 if (segmentsArray[index_array]==null) index_array--;
	}
	
	
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