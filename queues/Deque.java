import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first = null;
	private Node last = null;
	private int size = 0;
	
	public Deque() {
	}
	
	private class Node {
		Item item;
		Node next;
		Node prev;
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
		first.prev = null;
		if (oldfirst == null) {
			first.next = null;
			last = first;
		} else {
			first.next = oldfirst;
			oldfirst.prev = first;
		}
		size++;
	}
	
	// add the item to the end
	public void addLast(Item item) {
		if (item == null) throw new IllegalArgumentException("argument cannot be null");
		Node new_last = new Node();
		new_last.item = item;
		new_last.next = null;
		
		if (first==null) {
			first = new_last;
			last = new_last; 
		} else {
			new_last.prev = last;
			last.next = new_last;
			last = new_last;
		}
		size++;
	}
		
	// remove and return the item from the front
	public Item removeFirst() {
		if (first == null) throw new NoSuchElementException("Deque is empty");
		Node old_first = first;
		if (old_first.next==null) {
			first = null;
			last = null;
		} else {
			first = old_first.next;
			first.prev = null;
		}
		old_first.next = null;
		size--;
		return old_first.item;
	}
	
	// remove and return the item from the end
	public Item removeLast() {
		if (first == null) throw new NoSuchElementException("Deque is empty");
		Item last_item = last.item;
		if (last.prev==null) {
			first = null;
			last = null;
		} else {
			last = last.prev;
			last.next = null;
		}
		size--;
		return last_item;
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	public static void main(String[] args) {
		Deque<Integer> deq = new Deque<Integer>();
		deq.addFirst(1);
		deq.removeLast();
		deq.addFirst(2);
		deq.addFirst(6);
		deq.removeFirst();
		deq.removeLast();
		deq.addLast(7);
		deq.removeFirst();
		deq.addLast(7);
		deq.removeLast();
		
		StdOut.println(deq.isEmpty());

		for (Integer i : deq) {
			StdOut.println(i);
		}
		
	}


}