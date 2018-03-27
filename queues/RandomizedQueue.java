import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	@SuppressWarnings("unchecked")
	private Item[] arr = (Item[]) new Object[16];
	int last = 0;
	
	public RandomizedQueue() {
		
	}
	
	private void resizeArray(int current_size) {
		if (current_size==arr.length) {
			@SuppressWarnings("unchecked")
			Item[] new_arr = (Item[]) new Object[current_size*2];
			for (int i=0; i <= current_size; i++) {
				new_arr[i]=arr[i];
			}
			arr = new_arr;
		}
	}
	
	// is the randomized queue empty?
	public boolean isEmpty() {
		return arr[last]==null;
	}
	
	// return the number of items on the randomized queue
	public int size() {
		if (isEmpty()) return 0; 
		return last+1;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) throw new IllegalArgumentException("argument cannot be null");
		if (isEmpty()) {
			arr[0] = item;
		} else { 
			resizeArray(last);
			arr[last+1] = item;
			last++;
		}
		
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException("Queue is empty");
		int rnd_index = StdRandom.uniform(0,last+1);
		Item item = arr[rnd_index];
		arr[rnd_index] = arr[last];
		arr[last] = null;
		if (last> 0) last--;
		return item;
	}
	
	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty()) throw new NoSuchElementException("Queue is empty");
		return arr[StdRandom.uniform(0,last+1)];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedIterator();
	}
	
	private class RandomizedIterator implements Iterator<Item> {
		
		int index = 0;
		Item[] iterator_array;
		
		@SuppressWarnings("unchecked")
		public RandomizedIterator() {
			// @SuppressWarnings("unchecked")
			iterator_array = (Item[]) new Object[size()];
			
			
			StdRandom.shuffle(arr, 0, size());
//			for (Item k : arr) {
//				StdOut.println("  array k: " + k);
//			}
			for(int i=0;i <= last; i++) {
				iterator_array[i]=arr[i]; 
			}
			
			for (Item k : iterator_array) {
			StdOut.println("  iterator_array k: " + k);
			}
			
		}

		public boolean hasNext() {
			// return arr[index] != null;
			return iterator_array[index] != null;
		}

		public Item next() {
//			if (arr[index]==null) throw new NoSuchElementException("No more elements in Deque");
//			return arr[index++];
			if (iterator_array[index]==null) throw new NoSuchElementException("No more elements in Deque");
			return iterator_array[index++];
		}
		
		public void remove() {
			throw new UnsupportedOperationException("remove() operation not implemented");
		}
		
	}
	
	
	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

		rq.enqueue(1);

		rq.enqueue(2);

		rq.enqueue(3);

		rq.enqueue(4);

		rq.enqueue(5);

		rq.enqueue(6);
//		rq.dequeue();
//		rq.dequeue();
//		rq.dequeue();
//		rq.dequeue();
//		rq.dequeue();
//		rq.dequeue();

//		rq.enqueue(6);
//		rq.enqueue(66);
//		rq.enqueue(666);
//		rq.enqueue(6666);

		for (Integer i: rq) {
			StdOut.println("item i: " + i);
			for (Integer j : rq) {
				StdOut.println("  item j: " + j);
			}
			// StdOut.println("item: " + i);
		}
		
//		for (int i=0; i<100; i++) {
//			StdOut.println("random item: " + rq.sample());
//		}
		
		StdOut.println("end");
	}
}