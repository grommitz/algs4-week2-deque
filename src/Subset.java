import java.util.Iterator;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class Subset {

	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.err.println("Usage: java Subset <K>");
			return;
		}
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<>();

		// when running interactively use CTRL+Z to end the input.
		while (!StdIn.isEmpty()) {
			rq.enqueue(StdIn.readString());
		}
		
		Iterator<String> it = rq.iterator();
		for (int i = 0; i < k && it.hasNext(); ++i) {
			System.out.println(it.next());
		}
		
	}
	
}
