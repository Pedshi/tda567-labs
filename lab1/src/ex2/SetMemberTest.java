import org.junit.*;

import static org.junit.Assert.*;

public class SetMemberTest {
	private Set set;

	@Before public void setUp() {
		set = new Set();
	}

  // Statement and branch coverage
	@Test public void testMemberEmpty() {
		assertFalse(set.member(1));
	}

	@Test public void testMemberNonEmpty() {
		set.insert(1);
		assertTrue(set.member(1));
	}

	@Test public void testMemberMissing() {
		set.insert(1);
		set.insert(3);

		assertFalse(set.member(2));
	}
}
