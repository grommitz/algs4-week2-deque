import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import jdk.nashorn.internal.runtime.Logging;

/**
 * 
 * @author Martin Charlesworth
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] q;
	private int last = 0;
	private static final Logger logger = Logging.getLogger("RQ");
	
	public RandomizedQueue() {
		q = (Item[]) new Object[4];
	}
	
	public boolean isEmpty() {
		return last == 0;
	}
	
	public int size() {
		return last;
	}
	
	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}
		grow();
		q[last++] = item;
		logger.fine("enqueue last = " + last + " item = " + item);
	}
	
	// delete and return a random item
	public Item dequeue() {
		throwIfEmpty();
		int r = StdRandom.uniform(last);
		logger.fine("dequeue last = " + last + " r = " + r);
		final Item result = q[r];
		--last;
		exch(r, last, q);
		q[last] = null;
		shrink();
		return result;
	}
	
	// return (but do not delete) a random item
	public Item sample() {
		throwIfEmpty();
		return q[StdRandom.uniform(last)];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private void throwIfEmpty() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
	}

	private void grow() {
		if (last == q.length - 1) {
			//System.out.println("growing from " + q.length + " to " + q.length * 2);
			@SuppressWarnings("unchecked")
			Item[] q2 = (Item[]) new Object[q.length * 2];
			copy(q, q2);
			q = q2;
		}
	}

	private void shrink() {
		if (last < q.length / 4) {
			//System.out.println("shrinking from " + q.length + " to " + q.length / 2);
			@SuppressWarnings("unchecked")
			Item[] q2 = (Item[]) new Object[q.length / 2];
			copy(q, q2);
			q = q2;
		}
	}

	private void copy(Item[] src, Item[] dest) {
		for (int i = 0; i <= last; ++i) {
			dest[i] = src[i];
		}
	}

	private void exch(int i, int j, Item[] arr) {
		Item tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {

		private Item[] shuffled;
		private int iLast = 0;
		
		public RandomizedQueueIterator() {
			shuffled = (Item[]) new Object[last];
			for (int i = 0; i < shuffled.length; ++i) {
				shuffled[i] = q[i];
			}
			for (int i = 0; i < shuffled.length; ++i) {
				int r = StdRandom.uniform(i+1);
				exch(i, r, shuffled);			
			}
			iLast = shuffled.length;
		}
		
		@Override
		public boolean hasNext() {
			return iLast > 0;
		}

		@Override
		public Item next() {
			Item result = shuffled[iLast - 1];
			shuffled[--iLast] = null;
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

	public static void main(String[] args) {
		testEnqueueDequeue();
		testIterator();
	}

	private static void testIterator() {
		RandomizedQueue<Integer> rq = initialised(100);
		Iterator<Integer> it = rq.iterator();
		int i = 0;
		while (it.hasNext()) {
			System.out.println("it #" + (i++) + " -> " + it.next());
		}
	}

	private static void testEnqueueDequeue() {
		RandomizedQueue<Integer> rq = initialised(100);
		for (int i = 0; i < 100; ++i) {
			System.out.println("#" + i + " -> " + rq.dequeue() + " (size = " + rq.size() + ")");
		}
	}

	private static RandomizedQueue<Integer> initialised(int n) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		for (int i = 0; i < n; ++i) {
			rq.enqueue(i);			
		}
		return rq;
	}
	
}