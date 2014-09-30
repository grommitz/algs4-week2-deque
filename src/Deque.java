import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Martin Charlesworth
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
	
	private class Node {
		private Item item;
		private Node next;
		private Node prev;
		Node(Item item, Node prev, Node next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
		@Override
		public String toString() {
			return item.toString();
		}
	}
	
	private Node front, back;
	
	public Deque() { }
	
	public boolean isEmpty() {
		return front == null;
	}

	public int size() {
		int sz = 0;
		Node current = front;
		while (current != null) {
			++sz;
			current = current.next;
		}
		return sz;
	}
	
	// insert the item at the front
	public void addFirst(Item item) {
		throwIfNull(item);
		Node n = new Node(item, null, front);
		if (front == null) {
			front = n;
			back = front;
		} else {
			front.prev = n;
			front = n;
		}
	}

	// insert the item at the end
	public void addLast(Item item) {
		throwIfNull(item);
		Node n = new Node(item, back, null);
		if (front == null) {
			front = n;
			back = front;
		} else {
			back.next = n;
			back = n;
		}
	}

	// delete and return the item at the front
	public Item removeFirst() {
		throwIfEmpty();
		Item item = front.item;
		front = front.next;
		if (front == null) {
			back = null;
		} else {
			front.prev = null;
		}
		return item;
	}

	// delete and return the item at the end
	public Item removeLast() {
		throwIfEmpty();
		Item item = back.item;
		if (back.prev == null) {
			back = null;
			front = null;
		} else {
			back = back.prev;
			back.next = null;
		}
		return item;
	}

	// return an iterator over items in order from front to end
	@Override
	public Iterator<Item> iterator() {
		return new DequeIteraor();
	}

	private void throwIfEmpty() {
		if (size() == 0) {
			throw new NoSuchElementException("Cannot remove an element - the deque is empty");
		}
	}

	private void throwIfNull(Item item) {
		if (item == null) {
			throw new NullPointerException("item may not be null");
		}
	}

	private class DequeIteraor implements Iterator<Item> {

		private Node curr;
		
		public DequeIteraor() {
			curr = front;
		}
		
		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item next = curr.item;
			curr = curr.next;
			return next;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(); 		
		}
	}
	
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		if (front == null && back == null) {
//			sb.append("[ empty ]");
//		}
//		Node n = front;
//		while (n != null) {
//			sb.append(String.format("[<-%s, %s, %s->]", 
//					n.prev==null?"null":"P",
//					n.item,
//					n.next==null?"null":"N"));
//			n = n.next;
//		}
//		return sb.toString();
//	}

	public static void main(String[] args) {

		Deque<String> dq = new Deque<String>();
		dq.addFirst("world");
		dq.addFirst("hello");
		dq.addLast("from");
		dq.addLast("me!");

		Iterator<String> i = dq.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
		
		System.out.println(dq.removeFirst());
		System.out.println(dq.removeFirst());
		System.out.println(dq.removeFirst());
		System.out.println(dq.removeFirst());
		
	}


}
