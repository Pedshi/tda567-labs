import org.junit.*;

import static org.junit.Assert.*;

public class SetSectionTest {
	private Set set;
	private Set removeSet;

	@Before public void setUp() {
		set = new Set();
		removeSet = new Set();
	}

  // Statement and branch coverage
	@Test public void testSection() {
		set.insert(1);
		removeSet.insert(1);
		set.section(removeSet);
		assertFalse(set.member(1));
		assertTrue(set.toArray().length == 0);
	}

	@Test public void testNoRemove() {
		set.insert(1);
		removeSet.insert(2);
		set.section(removeSet);
		assertTrue(set.member(1));
		assertTrue(set.toArray().length == 1);
	}

	@Test public void testNoRemove2() {
		set.insert(2);
		removeSet.insert(1);
		set.section(removeSet);
		assertTrue(set.member(2));
		assertTrue(set.toArray().length == 1);
	}

}
