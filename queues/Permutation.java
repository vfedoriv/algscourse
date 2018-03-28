import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);
		while (!StdIn.isEmpty()) {
			String current = StdIn.readString();
			rq.enqueue(current);
		}
		
		int i = 1;
		for (String str : rq) {
			if (i > k) break;
			StdOut.println(str);
			i++;
		}
	}
}