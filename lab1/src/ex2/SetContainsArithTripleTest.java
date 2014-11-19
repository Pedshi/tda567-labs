import org.junit.*;

import static org.junit.Assert.*;

public class SetContainsArithTripleTest {
	private Set set;

	@Before public void setUp() {
		set = new Set();
	}

  // Statement and branch coverage
	@Test public void testExists() {
		set.insert(1);
		set.insert(2);
		set.insert(3);

		assertTrue(set.containsArithTriple());
	}

	@Test public void testNotExists() {
		set.insert(1);
		set.insert(3);
		set.insert(4);

		assertFalse(set.containsArithTriple());
	}


}
