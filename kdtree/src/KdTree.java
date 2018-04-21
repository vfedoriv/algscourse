import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	
	private class KDNode {
		private KDNode left;
		private KDNode right;
		private RectHV rect;
		private boolean vertical;
		private Point2D p;
		
		private KDNode(Point2D p) {
			this.left = null;
			this.right = null;
			this.rect = null;
			this.vertical = false;
			this.p = p;
		}
	}
	
	private KDNode root; 
	
	private int n_count = 0;
	
	public KdTree() {

	}
	
	public boolean isEmpty() {
		return (n_count == 0);
	}
	
	public int size() {
		return n_count;
	}
	
	public void insert(Point2D p) {
		if (p == null) throw new IllegalArgumentException("argument cannot be null");
		if (root == null) {
			root = new KDNode(p);
			root.vertical = true;
			root.rect = new RectHV(0, 0, 1, 1);
		}
		else {
			insertInPlace (p, this.root);
		}
		n_count++;
	}
	
	private void insertInPlace (Point2D p, KDNode n) {
		if (p.equals(n.p)) {
			n_count--;
			return;
		}
		
		if (n.vertical) {
			if (n.p.x() > p.x()) {
				if (n.left != null)	 {
					insertInPlace (p, n.left);
				}
				else {
					n.left = new KDNode(p);
					n.left.vertical = false;
				}
			}
			if (n.p.x() <= p.x()) {
				if (n.right != null)	 {
					insertInPlace (p, n.right);
				}
				else {
					n.right = new KDNode(p);
					n.right.vertical = false;
				}
			}

		}
		else {
			if (n.p.y() > p.y()) {
				if (n.left != null)	 {
					insertInPlace (p, n.left);
				}
				else {
					n.left = new KDNode(p);
					n.left.vertical = true;
				}
			}
			if (n.p.y() <= p.y()) {
				if (n.right != null)	 {
					insertInPlace (p, n.right);
				}
				else {
					n.right = new KDNode(p);
					n.right.vertical = true;
				}
			}
		}
		
	}
	
    private RectHV leftRect(final RectHV rect, final KDNode node)
    {
        if (node.vertical)
            return new RectHV(rect.xmin(), rect.ymin(), node.p.x(), rect.ymax());
        else
            return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.p.y());
    }
	
    private RectHV rightRect(final RectHV rect, final KDNode node)
    {
        if (node.vertical)
            return new RectHV(node.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
        else
            return new RectHV(rect.xmin(), node.p.y(), rect.xmax(), rect.ymax());
    }
    
	
	
	public boolean contains(Point2D p) {
		if (p == null) throw new IllegalArgumentException("argument cannot be null");
		return containRecurs(p, this.root);
	}
	
	private boolean containRecurs(Point2D p, KDNode n) {
		
		if (p.equals(n.p)) return true;
		
		if (n.vertical) {
			if (n.p.x() > p.x()) {
				if (n.left != null)	 {
					return containRecurs (p, n.left);
				}
				else return false;
			}
			if (n.p.x() <= p.x()) {
				if (n.right != null)	 {
					return containRecurs (p, n.right);
				}
				else return false;
			}
		}
		else {
			if (n.p.y() > p.y()) {
				if (n.left != null)	 {
					return containRecurs (p, n.left);
				}
				else return false;
			}
			if (n.p.y() <= p.y()) {
				if (n.right != null)	 {
					return containRecurs (p, n.right);
				}
				else return false;
			}
		}
		
		return false;
	}
	
	
	
	public void draw() {
		
        StdDraw.setScale(0, 1);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        root.rect.draw();
        draw(root, root.rect);
		
	}
	
	 private void draw(KDNode node, RectHV rect)
	    {
	        if (node == null) return;

	        // draw the point
	        StdDraw.setPenColor(StdDraw.BLACK);
	        StdDraw.setPenRadius(0.01);
	        new Point2D(node.p.x(), node.p.y()).draw();

	        // get the min and max points of division line
	        Point2D min, max;
	        if (node.vertical) {
	            StdDraw.setPenColor(StdDraw.RED);
	            min = new Point2D(node.p.x(), rect.ymin());
	            max = new Point2D(node.p.x(), rect.ymax());
	        } else {
	            StdDraw.setPenColor(StdDraw.BLUE);
	            min = new Point2D(rect.xmin(), node.p.y());
	            max = new Point2D(rect.xmax(), node.p.y());
	        }

	        // draw that division line
	        StdDraw.setPenRadius();
	        min.drawTo(max);

	        // recursively draw children
	        draw(node.left, leftRect(rect, node));
	        draw(node.right, rightRect(rect, node));
	    }
	
	
	
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new IllegalArgumentException("argument cannot be null");
        final Queue<Point2D> queue = new Queue<Point2D>();
        rangeRecurs(root, root.rect, rect, queue);
        return queue;
	}
	
    private void rangeRecurs(KDNode node, final RectHV nrect,
            final RectHV rect, final Queue<Point2D> queue)
    {
        if (node == null) return;

        if (rect.intersects(nrect)) {
            final Point2D p = new Point2D(node.p.x(), node.p.y());
            if (rect.contains(p)) queue.enqueue(p);
            rangeRecurs(node.left, leftRect(nrect, node), rect, queue);
            rangeRecurs(node.right, rightRect(nrect, node), rect, queue);
        }
    }
	
	
	
	public Point2D nearest(Point2D p) {
		if (p == null) throw new IllegalArgumentException("argument cannot be null");
		return nearestRecurs(root, root.rect, p.x(), p.y(), null);

	}
	
	 private Point2D nearestRecurs(KDNode node, RectHV rect, double x, double y, Point2D candidate) {
	        if (node == null) return candidate;
	        double dqn = 0.0;
	        double drq = 0.0;
	        RectHV left = null;
	        RectHV rigt = null;
	        final Point2D query = new Point2D(x, y);
	        Point2D nearestPoint = candidate;

	        if (nearestPoint != null) {
	            dqn = query.distanceSquaredTo(nearestPoint);
	            drq = rect.distanceSquaredTo(query);
	        }

	        if (nearestPoint == null || dqn > drq) {
	            final Point2D point = new Point2D(node.p.x(), node.p.y());
	            if (nearestPoint == null || dqn > query.distanceSquaredTo(point))
	                nearestPoint = point;

	            if (node.vertical) {
	                left = new RectHV(rect.xmin(), rect.ymin(), node.p.x(), rect.ymax());
	                rigt = new RectHV(node.p.x(), rect.ymin(), rect.xmax(), rect.ymax());

	                if (x < node.p.x()) {
	                    nearestPoint = nearestRecurs(node.left, left, x, y, nearestPoint);
	                    nearestPoint = nearestRecurs(node.right, rigt, x, y, nearestPoint);
	                } else {
	                    nearestPoint = nearestRecurs(node.right, rigt, x, y, nearestPoint);
	                    nearestPoint = nearestRecurs(node.left, left, x, y, nearestPoint);
	                }
	            } else {
	                left = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.p.y());
	                rigt = new RectHV(rect.xmin(), node.p.y(), rect.xmax(), rect.ymax());

	                if (y < node.p.y()) {
	                    nearestPoint = nearestRecurs(node.left, left, x, y, nearestPoint);
	                    nearestPoint = nearestRecurs(node.right, rigt, x, y, nearestPoint);
	                } else {
	                    nearestPoint = nearestRecurs(node.right, rigt, x, y, nearestPoint);
	                    nearestPoint = nearestRecurs(node.left, left, x, y, nearestPoint);
	                }
	            }
	        }

	        return nearestPoint;
	    }
	
	public static void main(String[] args) {

		KdTree tree = new KdTree();

		Point2D point1 = new Point2D(0.372, 0.497);
		Point2D point2 = new Point2D(0.564, 0.413);
		Point2D point3 = new Point2D(0.226, 0.577);
		Point2D point4 = new Point2D(0.144, 0.179);
		Point2D point5 = new Point2D(0.083, 0.510);
		Point2D point6 = new Point2D(0.320, 0.708);
		Point2D point7 = new Point2D(0.417, 0.362);
		Point2D point8 = new Point2D(0.862, 0.825);
		Point2D point9 = new Point2D(0.785, 0.725);
		Point2D point10 = new Point2D(0.499, 0.208);
		
		// duplicates
		Point2D point11 = new Point2D(0.564, 0.413);
		Point2D point12 = new Point2D(0.372, 0.497);
		Point2D point13 = new Point2D(0.320, 0.708);
		
		// not in tree
		Point2D point14 = new Point2D(0.322, 0.728);
		
		tree.insert(point1);
		tree.insert(point2);
		tree.insert(point3);
		tree.insert(point4);
		tree.insert(point5);
		tree.insert(point6);
		tree.insert(point7);
		tree.insert(point8);
		tree.insert(point9);
		tree.insert(point10);
		tree.insert(point11);
		tree.insert(point12);
		tree.insert(point13);
		// tree.insert(point14);
		System.out.println(tree.n_count);
		
		System.out.println (tree.contains(point1));
		System.out.println (tree.contains(point2));
		System.out.println (tree.contains(point10));
		
		System.out.println (tree.contains(point11));
		
		System.out.println (tree.contains(point14));
		
		
		System.out.println (tree.contains(point3));
		System.out.println (tree.contains(point5));
		System.out.println (tree.contains(point4));
		System.out.println (tree.contains(point6));
		System.out.println (tree.contains(point7));
		
		
		

	}

}