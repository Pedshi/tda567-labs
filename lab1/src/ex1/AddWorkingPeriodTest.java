import org.junit.*;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AddWorkingPeriodTest {
	private WorkSchedule schedule;

  // Tests when starttime == 0
	@Test public void testStartZero() {
		int size = 10;
		schedule = new WorkSchedule(size);
		schedule.setRequiredNumber(1, 0, 9);
		WorkSchedule oldSchedule = new WorkSchedule(size);
		oldSchedule.setRequiredNumber(1, 0, 9);

		boolean result = schedule.addWorkingPeriod("Bob", -1, 5);
		assertFalse(result);
		compareSchedules(schedule, oldSchedule, size);
	}

  // Tests when endtime > size
	@Test public void testEndOver() {
		int size = 10;
		schedule = new WorkSchedule(size);
		schedule.setRequiredNumber(1, 0, 9);
		WorkSchedule oldSchedule = new WorkSchedule(size);
		oldSchedule.setRequiredNumber(1, 0, 9);

		boolean result = schedule.addWorkingPeriod("Bob", 2, 10);
		assertFalse(result);
		compareSchedules(schedule, oldSchedule, size);
	}

	// FAILS. Tests when starttime > endtime
	@Test public void testStartOverEnd() {
		int size = 10;
		schedule = new WorkSchedule(size);
		schedule.setRequiredNumber(1, 0, 9);
		WorkSchedule oldSchedule = new WorkSchedule(size);
		oldSchedule.setRequiredNumber(1, 0, 9);

		boolean result = schedule.addWorkingPeriod("Bob", 6, 4);
		assertFalse(result);
		compareSchedules(schedule, oldSchedule, size);
	}

  // Tests adding more employees than requiredNumber
	@Test public void testRequiredNumber() {
		int size = 10;
		schedule = new WorkSchedule(size);
		WorkSchedule oldSchedule = new WorkSchedule(size);
		schedule.setRequiredNumber(1, 3, 6);
		schedule.addWorkingPeriod("Alice", 4, 4);
		oldSchedule.setRequiredNumber(1, 3, 6);
		oldSchedule.addWorkingPeriod("Alice", 4, 4);

		boolean result = schedule.addWorkingPeriod("Bob", 3, 6);
		assertFalse(result);
		compareSchedules(schedule, oldSchedule, size);
	}

  // Tests adding an employee more than once
	@Test public void testEmployeeAlreadyExists() {
		int size = 10;
		schedule = new WorkSchedule(size);
		WorkSchedule oldSchedule = new WorkSchedule(size);
		schedule.setRequiredNumber(2, 3, 6);
		schedule.addWorkingPeriod("Bob", 4, 4);
		oldSchedule.setRequiredNumber(2, 3, 6);
		oldSchedule.addWorkingPeriod("Bob", 4, 4);

		boolean result = schedule.addWorkingPeriod("Bob", 3, 6);
		assertFalse(result);
		compareSchedules(schedule, oldSchedule, size);
	}

  // Tests expected success
	@Test public void testSuccess() {
		int size = 10;
		schedule = new WorkSchedule(size);
		WorkSchedule oldSchedule = new WorkSchedule(size);
		schedule.setRequiredNumber(2, 3, 6);
		schedule.addWorkingPeriod("Bob", 4, 4);
		oldSchedule.setRequiredNumber(2, 3, 6);
		oldSchedule.addWorkingPeriod("Bob", 4, 4);

		boolean result = schedule.addWorkingPeriod("Alice", 3, 6);
		assertTrue(result);

		// Initiate magic
		for(int i = 0; i < size; i++) {
			WorkSchedule.Hour h1 = schedule.readSchedule(i);
			WorkSchedule.Hour h2 = oldSchedule.readSchedule(i);
			// Working span
			if(i >= 3 && i <= 6) {
				// Should contain Alice
				assertTrue(Arrays.asList(h1.workingEmployees).contains("Alice"));
				// Should contain all other employees from old schedule
				for(int j = 0; j < h2.workingEmployees.length; j++) {
					assertTrue(Arrays.asList(h1.workingEmployees).contains(h2.workingEmployees[j]));
				}

			} else {
				// Should be unchanged
				assertTrue(h1.requiredNumber == h2.requiredNumber);
				assertTrue(Arrays.deepEquals(h1.workingEmployees, h2.workingEmployees));
			}
		}
	}

  // Compares two schedules for equality
	private void compareSchedules(WorkSchedule schedule1, WorkSchedule schedule2, int size) {
		for(int i = 0; i < size; i++) {
			WorkSchedule.Hour h1 = schedule1.readSchedule(i);
			WorkSchedule.Hour h2 = schedule2.readSchedule(i);
			assertTrue(h1.requiredNumber == h2.requiredNumber);
			assertTrue(Arrays.deepEquals(h1.workingEmployees, h2.workingEmployees));
		}
	}
}
