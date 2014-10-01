import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;


public class RandomizedQueueTest {

	RandomizedQueue<Character> rq = new RandomizedQueue<>();
	
	@Test
	public void test13() {
		int stats[] = new int[3];
		for (int i=0; i<3000; ++i) {
			rq = new RandomizedQueue<Character>();
			rq.enqueue('A');
			rq.enqueue('B');
			rq.enqueue('C');
			int pops = 0;
			Character item = null;
			do {
				item = rq.dequeue();
				++pops;
			} while (!item.equals('A'));
			stats[pops-1]++;
		}
		System.out.println(String.format("1 pop = %d/3000, 2 pops = %d/3000, 3 pops = %d/3000", 
				stats[0], stats[1], stats[2]));
		for (int i = 0; i < 2; ++i) {
			double pct = (double)stats[i]/3000;
			assertTrue(pct > 0.31 && pct < 0.35);
		}
	}
	

}
