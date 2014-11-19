import org.junit.*;

import static org.junit.Assert.*;

public class SetInsertTest {
	private Set set;

	@Before public void setUp() {
		set = new Set();
	}

	// Statement coverage
	@Test public void testInsertEmpty() {
		set.insert(1);
		assertTrue(set.toArray().length == 1);
		assertTrue(set.toArray()[0] == 1);
	}

	@Test public void testInsertNonEmpty() {
		set.insert(2);
		set.insert(1);

		assertTrue(set.toArray().length == 2);
		assertTrue(set.toArray()[0] == 1);
		assertTrue(set.toArray()[1] == 2);
	}

	@Test public void testInsertExists() {
		set.insert(1);
		set.insert(1);

		assertTrue(set.toArray().length == 1);
		assertTrue(set.toArray()[0] == 1);
	}

	// Branch coverage
	@Test public void testInsertAddEnd() {
		set.insert(1);
		set.insert(2);
		set.insert(3);

		assertTrue(set.toArray().length == 3);
		assertTrue(set.toArray()[0] == 1);
		assertTrue(set.toArray()[1] == 2);
		assertTrue(set.toArray()[2] == 3);
	}
}
