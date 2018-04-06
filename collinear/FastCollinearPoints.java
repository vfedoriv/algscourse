//public class FastCollinearPoints {
//   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
//   public           int numberOfSegments()        // the number of line segments
//   public LineSegment[] segments()      

import java.util.Arrays;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
	
	private Point[] pointsArray;
	private LineSegment[] segmentsArray;
	private int segmentsCounter = 0;
	
	public FastCollinearPoints(Point[] points) {
		
		double start_slope, current_slope;
		int idx = 0;
		int int_idx = 0;
		
		int pointsSegCounter = 0;
		Point minPoint, maxPoint;
		
		pointsArray = points.clone();
		segmentsArray = new LineSegment[points.length];
		

		
		if (pointsArray==null) throw new IllegalArgumentException("argument cannot be null");
		Arrays.sort(pointsArray);
		for (int i=0; i < pointsArray.length; i++) {
			if (pointsArray[i]== null)  throw new IllegalArgumentException("array item cannot be null");
			if (i> 0) {
				if (pointsArray[i-1].equals(pointsArray[i])) throw new IllegalArgumentException("array cannot contain duplicates");
			}
		}
		
		for (int i=0; i < pointsArray.length; i++) {
			// Arrays.sort(pointsArray, pointsArray[i].slopeOrder());
			Arrays.sort(pointsArray, i+1,pointsArray.length-1,pointsArray[i].slopeOrder());

		
			while (idx < pointsArray.length - 3) {
				
				minPoint = pointsArray[idx];
				maxPoint = pointsArray[idx];
				start_slope = pointsArray[idx].slopeTo(pointsArray[idx+1]);
				pointsSegCounter = 0;
				int_idx = idx + 2;
				current_slope = pointsArray[idx].slopeTo(pointsArray[idx+2]);
				System.out.println("Point : " + pointsArray[idx]);
				System.out.println("current_slope "+ current_slope);
	
				
				while ((current_slope == start_slope) && (int_idx < pointsArray.length)) {
					int_idx++;
					if ((int_idx < pointsArray.length)) {
						current_slope = pointsArray[idx].slopeTo(pointsArray[int_idx]);
					}
					maxPoint = pointsArray[int_idx - 1];
					pointsSegCounter = int_idx - idx;
				}
				
				if (pointsSegCounter > 3) {
					System.out.println("pointsSegCounter = " + pointsSegCounter);
					segmentsArray[segmentsCounter] = new LineSegment(minPoint, maxPoint);
					segmentsCounter++;
					idx = int_idx;
				} else {
					if (pointsSegCounter > 0) {
						idx = int_idx;
					}
					else {
						idx++; 
					}
				}
				
			} // while
		
		}	 // for
		
		
		
		

		
		
		
		
		
	}
	
	public int numberOfSegments() {
		System.out.println("segmentsCounter: " + segmentsCounter);
		return segmentsCounter;
	}
	
	public LineSegment[] segments() {
		return Arrays.copyOf(segmentsArray, numberOfSegments());
		// return segmentsArray;
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