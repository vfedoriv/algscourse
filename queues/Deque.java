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
		if (first == null) return 0;
		int i = 1;
		Node current = first;
		while (current.next != null) {
			current = current.next;
			i++;
		}
		return i;
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
		return oldfirst.item;
	}
	
	// remove and return the item from the end
	public Item removeLast() {
		Node current = first;
		Node last = first;
		while (current.next.next != null) {
			current = current.next;
		}
		last = current.next;
		current.next = null;
		return last.item;
	}
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	public static void main(String[] args) {
		Deque<Integer> deq = new Deque<Integer>();
		deq.addFirst(1);
		StdOut.println("size: " + deq.size());
		deq.addLast(2);
		StdOut.println("size: " + deq.size());
		deq.addLast(3);
		StdOut.println("size: " + deq.size());
		deq.addFirst(4);
		StdOut.println("size: " + deq.size());
		deq.removeLast();
		StdOut.println("size: " + deq.size());
		deq.removeFirst();
		StdOut.println("size: " + deq.size());
		deq.addFirst(5);
		StdOut.println("size: " + deq.size());
		deq.addLast(6);
		StdOut.println("size: " + deq.size());
		for (Integer i : deq) {
			StdOut.println(i);
		}
		
	}


}