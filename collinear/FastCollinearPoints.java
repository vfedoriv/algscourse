import java.util.ArrayList;
import java.util.Arrays;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
	
	private Point[] pointsArray;
	
	private ArrayList<LineSegment> segmentsList = new ArrayList<>();
	
	public FastCollinearPoints(Point[] points) {
		
		if (points==null) throw new IllegalArgumentException("argument cannot be null");
		
		pointsArray = Arrays.copyOf(points, points.length);

		for (int i=0; i < pointsArray.length; i++) {
			if (pointsArray[i]== null)  throw new IllegalArgumentException("array item cannot be null");
		}
		
		Arrays.sort(pointsArray);
		for (int i=0; i < pointsArray.length-1; i++) {
			if (pointsArray[i].equals(pointsArray[i+1])) throw new IllegalArgumentException("array cannot contain duplicates");
		}
		
        for (int i = 0; i < pointsArray.length - 3; i++) {
            Arrays.sort(pointsArray);
            Arrays.sort(pointsArray, pointsArray[i].slopeOrder());

            for (int p = 0, first = 1, last = 2; last < pointsArray.length; last++) {
                while (last < pointsArray.length
                        && Double.compare(pointsArray[p].slopeTo(pointsArray[first]), pointsArray[p].slopeTo(pointsArray[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && pointsArray[p].compareTo(pointsArray[first]) < 0) {
                	segmentsList.add(new LineSegment(pointsArray[p], pointsArray[last - 1]));
                }
                first = last;
            }
        }
		
	}
	
	public int numberOfSegments() {
		 return segmentsList.size();
	}
	
	public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[segmentsList.size()];
        return segmentsList.toArray(segments);

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