//public class Deque<Item> implements Iterable<Item> {
//public Deque()                           // construct an empty deque
//public boolean isEmpty()                 // is the deque empty?
//public int size()                        // return the number of items on the deque
//public void addFirst(Item item)          // add the item to the front
//public void addLast(Item item)           // add the item to the end
//public Item removeFirst()                // remove and return the item from the front
//public Item removeLast()                 // remove and return the item from the end
//public Iterator<Item> iterator()         // return an iterator over items in order from front to end
//public static void main(String[] args)   // unit testing (optional)
//}
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first = null;
	private int size = 0;
	
	public Deque() {
	}
	
	private class Node {
		Item item;
		Node next;
	}

	private class DequeIterator implements Iterator<Item> {
		
		private Node current = first;
	
		public boolean hasNext() {
			return current != null;
		}
	
		public Item next() {
			if (current == null) throw new NoSuchElementException("No more elements in Deque");
	        Item item = current.item;
	        current   = current.next; 
	        return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException("remove() operation not implemented");
		}
		
	}

	public boolean isEmpty() {
		return (first == null);
	}
	
	// return the number of items on the deque
	public int size() {
		return size;
	}
	
	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) throw new IllegalArgumentException("argument cannot be null"); 
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		if (oldfirst == null) {
			first.next = null;
		} else first.next = oldfirst;
		size++;
		
	}
	
	// add the item to the end
	public void addLast(Item item) {
		if (item == null) throw new IllegalArgumentException("argument cannot be null");
		Node last = new Node();
		last.item = item;
		last.next = null;
		if (first==null) {
			first = last;
		} else {
			Node current = first;
			while (current.next != null) {
				current = current.next;
			}
			current.next = last;
		}
		size++;
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		if (first == null) throw new NoSuchElementException("Deque is empty");
		Node oldfirst = first;
		if (oldfirst.next==null) {
			first = null;			
		} else {
			first = oldfirst.next;
		}
		oldfirst.next = null;
		size--;
		return oldfirst.item;
		
	}
	
	// remove and return the item from the end
	public Item removeLast() {
		if (first == null) throw new NoSuchElementException("Deque is empty");
		Node current = first;
		Node new_last = first;
		
		for (int i = 1; i < size; i++) {
			if ((size-i)==1) new_last = current;
			current = current.next;
		}
		new_last.next = null;
		if (size==1) {
			first = null;
			new_last = null;
		}
		size--;
		return current.item;
	}
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	public static void main(String[] args) {
		Deque<Integer> deq = new Deque<Integer>();
//		deq.addFirst(1);
//		deq.removeLast();
//		deq.addFirst(2);
//		deq.addFirst(6);
//		deq.removeFirst();
//		deq.removeLast();
//		deq.addLast(7);
//		deq.removeFirst();
		deq.addLast(7);
		deq.removeLast();
		StdOut.println(deq.isEmpty());

		for (Integer i : deq) {
			StdOut.println(i);
		}
		
	}


}