//public class RandomizedQueue<Item> implements Iterable<Item> {
//   public RandomizedQueue()                 // construct an empty randomized queue
//   public boolean isEmpty()                 // is the randomized queue empty?
//   public int size()                        // return the number of items on the randomized queue
//   public void enqueue(Item item)           // add the item
//   public Item dequeue()                    // remove and return a random item
//   public Item sample()                     // return a random item (but do not remove it)
//   public Iterator<Item> iterator()         // return an independent iterator over items in random order
//   public static void main(String[] args)   // unit testing (optional)
//}
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	@SuppressWarnings("unchecked")
	private Item[] arr = (Item[]) new Object[16];
	int first = 0;
	int last = 0;
	
	public RandomizedQueue() {
		
	}
	
	// is the randomized queue empty?
	public boolean isEmpty() {
		return ((first==last) && (arr[first]==null));
		
	}
	
	// return the number of items on the randomized queue
	public int size() {
		// if (arr[first]==null) return 0;
		return last - first;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) throw new IllegalArgumentException("argument cannot be null");
		arr[last +1] = item;
		
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException("Queue is empty");
		return null;
	}
	
	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty()) throw new NoSuchElementException("Queue is empty");
		return null;
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedIterator();
	}
	
	private class RandomizedIterator implements Iterator<Item> {
		
		Item current;

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		public Item next() {
			if (current == null) throw new NoSuchElementException("No more elements in Deque");
			// TODO Auto-generated method stub
			return null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException("remove() operation not implemented");
		}
		
	}
	
	
	public static void main(String[] args) {
		
	}
}