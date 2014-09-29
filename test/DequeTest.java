import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Martin Charlesworth
 *
 */

public class DequeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test3() {
		Deque<Integer> dq = new Deque<>();
		int sz = 0;
		for (int i=0; i<50; ++i) {
			int rand = StdRandom.uniform(10);
			if (rand == 0) {
				dq.removeLast();
				--sz;
			} else {
				dq.addFirst(rand);
				++sz;
			}
			assertThat(dq.size(), is(sz));
		}
		
	}

}
