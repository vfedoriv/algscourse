import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
	
	private LineSegment[] segmentsArray;
	private int index_array = 0;
	private Point p, q, r, s, min, max;
	private double old_slope;

	public BruteCollinearPoints(Point[] points) {
		if (points==null) throw new IllegalArgumentException("argument cannot be null");
		Arrays.sort(points);
		for (int i=0; i < points.length; i++) {
			if (points[i]== null)  throw new IllegalArgumentException("array item cannot be null");
			if (i> 0) {
				if (points[i-1].equals(points[i])) throw new IllegalArgumentException("array cannot contain duplicates");
			}
		}
		
		segmentsArray = new LineSegment[points.length*points.length];

		old_slope = points[0].slopeTo(points[1]);
//		loSegmentPoint = points[0];
	
		

		for (int i=0; i < points.length ; i++) {
			p = points[i];
			
			for (int j=i+1; j < points.length ; j++) {
				q = points[j];
				
//				if (!(loSegmentPoint.slopeTo(q)==p.slopeTo(q))) {
//					loSegmentPoint = p;
//					if (!(segmentsArray[index_array]==null)) index_array++;
//				}

				for (int k=j+1; k < points.length; k++) {
					r = points[k];
					
					if (!(p.slopeTo(q)==p.slopeTo(r))) continue;
					
					for (int l=k+1; l < points.length; l++) {
						s = points[l];
						if ((p.slopeTo(q)==p.slopeTo(r)) && (p.slopeTo(q)==p.slopeTo(s))) {
							
							if (min == null) min = p;
							if (max == null) max = s;
							
							if (!(old_slope==p.slopeTo(q))) {
								min = p;
								max = s;
								
								if (!(segmentsArray[index_array]==null)) index_array++;
							}
							
							
							if ((min.compareTo(p) > 0) && old_slope==p.slopeTo(q)) min = p;
							if ((max.compareTo(s) < 0) && old_slope==p.slopeTo(q)) max = s;
							
							System.out.println("add in [" + index_array + "] :" + min + " ; " + max);
							segmentsArray[index_array] = new LineSegment(min, max);
							
							old_slope = p.slopeTo(q);

						};
						
						
					}
				}
			}
			
		} // external for
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