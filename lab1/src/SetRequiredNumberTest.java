import org.junit.*;

import static org.junit.Assert.*;

public class SetRequiredNumberTest {
	private WorkSchedule schedule;

	/**
	 * Possible to insert hours in the whole available range (0..size-1).
	 */
	@Test public void testCorrectInserted1() {
		int size = 10;
		schedule = new WorkSchedule(size);
		int nemployee = 1;
		int starttime = 0;
		int endtime = size-1;
		schedule.setRequiredNumber(nemployee, starttime, endtime);
		
		checkRequiredNumber(nemployee, starttime, endtime);
	}

	/**
	 * Possible to insert a single hour.
	 */
	@Test public void testCorrectInserted2() {
		int size = 10;
		schedule = new WorkSchedule(size);
		int nemployee = 1;
		int starttime = 2;
		int endtime = 2;
		schedule.setRequiredNumber(nemployee, starttime, endtime);
		
		checkRequiredNumber(nemployee, starttime, endtime);
	}
	
	/**
	 * Should not change any hours outside the specified range.
	 */
	@Test public void testUnchanged1() {
		int size = 10;
		schedule = new WorkSchedule(size);
		int nemployee = 1;
		int starttime = 4;
		int endtime = 7;
		schedule.setRequiredNumber(nemployee, starttime, endtime);
		
		checkRequiredNumber(0, 0, starttime-1);
		checkRequiredNumber(0, endtime+1, size-1);
	}

	/**
	 * Should not change any hours outside the specified range (when hours are non-zero).
	 */
	@Test public void testUnchanged2() {
		int size = 10;
		schedule = new WorkSchedule(size);
		int nemployee = 1;
		int starttime = 3;
		int endtime = 5;
		schedule.setRequiredNumber(nemployee, 0, size-1);
		schedule.setRequiredNumber(0, starttime, endtime);
		
		checkRequiredNumber(nemployee, 0, starttime-1);
		checkRequiredNumber(nemployee, endtime+1, size-1);
	}
	
	/**
	 * Checks value of required employee amount for hours in range starttime...endtime.
	 */
	public void checkRequiredNumber(int nemployee, int starttime, int endtime) {
		for(int i = starttime; i <= endtime; i++) {
			WorkSchedule.Hour h = schedule.readSchedule(i);
			assertTrue(h.requiredNumber == nemployee);
		}
	}
}
