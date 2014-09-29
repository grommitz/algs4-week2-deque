import java.util.Iterator;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class Subset {

	public static void main(String[] args) {
		
		if (args.length < 2) {
			System.err.println("Usage: java Subset k <strings>");
			return;
		}
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<>();
		for (int i = 1; i < args.length; ++i) {
			rq.enqueue(args[i]);
		}
		
		Iterator<String> it = rq.iterator();
		for (int i = 0; i < k && it.hasNext(); ++i) {
			System.out.println(it.next());
		}
		
	}
	
}
