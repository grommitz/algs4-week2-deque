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

	Deque<Integer> dq = new Deque<>();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test3() {
		int sz = 0;
		for (int i=0; i<50; ++i) {
			int rand = StdRandom.uniform(10);
			if (rand < 0 && dq.size() > 0) {
				dq.removeLast();
				--sz;
			} else {
				dq.addFirst(rand);
				++sz;
			}
			assertThat(dq.size(), is(sz));
		}
	}
	
	@Test
	public void test6() {
		int sz = 0;
		for (int i=0; i<50; ++i) {
			int rand = StdRandom.uniform(10);
			if (rand == 0) {
				dq.addFirst(rand);
				++sz;
				System.out.println(i + " addFirst sz = " + sz);
			} else if (rand == 1) {
				dq.addLast(rand);
				++sz;
				System.out.println(i + " addLast sz = " + sz);
			} else if (rand < 6 && sz > 0) {
				dq.removeFirst();
				--sz;
				System.out.println(i + " removeFirst sz = " + sz);
			} else if (sz > 0) {
				dq.removeLast();
				--sz;
				System.out.println(i + " removeLast sz = " + sz);
			}
			assertThat(dq.size(), is(sz));
			assertThat(dq.isEmpty(), is(sz == 0));
		}
	}

//	@Test
//	public void test6redux() {
//		dq.addFirst(1);
//		assertThat(dq.size(), is(1));
//		dq.addFirst(2);
//		assertThat(dq.size(), is(2));
//		dq.removeLast();
//		assertThat(dq.size(), is(1));
//		dq.removeFirst();
//		assertThat(dq.size(), is(0));
//		dq.addFirst(3);
//		assertThat(dq.size(), is(1));
//		dq.removeFirst();
//		assertThat(dq.size(), is(0));
//		
//		dq.addFirst(4);
//		System.out.println(dq.toString());
//		assertThat(dq.size(), is(1));
//		
//		dq.removeFirst();
//		System.out.println(dq.toString());
//		assertThat(dq.size(), is(0));
//		
//		dq.addLast(5);
//		System.out.println(dq.toString());
//		assertThat(dq.size(), is(1));
//		
//		dq.removeLast();
//		System.out.println(dq.toString());
//		assertThat(dq.isEmpty(), is(true));
//		assertThat(dq.size(), is(0));
//	}
	
	@Test
	public void should_remove_last_element() {
		dq.addFirst(1);
		int i = dq.removeLast();
		assertThat(i, is(1));
		assertThat(dq.size(), is(0));
	}

}
