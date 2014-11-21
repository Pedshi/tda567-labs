import java.util.Arrays;

import org.junit.*;

import static org.junit.Assert.*;

public class WorkingEmployeesTest {

	// This test fails. Indicates a bug in the code.
	@Test public void testStartOverEnd() {
		// Should return empty list
		WorkSchedule schedule = new WorkSchedule(10);
		WorkSchedule oldSchedule = new WorkSchedule(10);
		schedule.setRequiredNumber(1, 4, 6);
		schedule.addWorkingPeriod("Bob", 4, 6);
		oldSchedule.setRequiredNumber(1, 4, 6);
		oldSchedule.addWorkingPeriod("Bob", 4, 6);

    // Still returns all entries within the interval
    // even when starttime > endtime
		assertTrue(schedule.workingEmployees(6, 4).length == 0);

		compareSchedules(schedule, oldSchedule, 10);

	}

  // Testing a successful run
	@Test public void testSuccess() {
		WorkSchedule schedule = new WorkSchedule(10);
		WorkSchedule oldSchedule = new WorkSchedule(10);
		schedule.setRequiredNumber(1, 4, 6);
		schedule.addWorkingPeriod("Bob", 4, 6);
		oldSchedule.setRequiredNumber(1, 4, 6);
		oldSchedule.addWorkingPeriod("Bob", 4, 6);

		String[] employees = schedule.workingEmployees(4, 7);

		assertTrue(Arrays.asList(employees).contains("Bob"));
		assertTrue(employees.length == 1);

		compareSchedules(schedule, oldSchedule, 10);
	}

  // Helper function that compares two schedules to see if they are equal
	private void compareSchedules(WorkSchedule schedule1, WorkSchedule schedule2, int size) {
		for(int i = 0; i < size; i++) {
			WorkSchedule.Hour h1 = schedule1.readSchedule(i);
			WorkSchedule.Hour h2 = schedule2.readSchedule(i);
			assertTrue(h1.requiredNumber == h2.requiredNumber);
			assertTrue(Arrays.deepEquals(h1.workingEmployees, h2.workingEmployees));
		}
	}
}
