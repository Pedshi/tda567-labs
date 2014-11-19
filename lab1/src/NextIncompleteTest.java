import java.util.Arrays;

import org.junit.*;

import static org.junit.Assert.*;

public class NextIncompleteTest {
  // Tests expected success
	@Test public void testSuccess(){
		WorkSchedule schedule = new WorkSchedule(10);
		WorkSchedule oldSchedule = new WorkSchedule(10);
		schedule.setRequiredNumber(1, 4, 5);
		schedule.setRequiredNumber(2, 6, 6);
		schedule.addWorkingPeriod("Bob", 4, 6);
		oldSchedule.setRequiredNumber(1, 4, 5);
		oldSchedule.setRequiredNumber(2, 6, 6);
		oldSchedule.addWorkingPeriod("Bob", 4, 6);

		int time = schedule.nextIncomplete(3);
		assertTrue(time == 6);

		compareSchedules(schedule, oldSchedule, 10);
	}

  // Tests searching when there are no open spots
	@Test public void testFail(){
		WorkSchedule schedule = new WorkSchedule(10);
		WorkSchedule oldSchedule = new WorkSchedule(10);
		schedule.setRequiredNumber(1, 4, 6);
		schedule.addWorkingPeriod("Bob", 4, 6);
		oldSchedule.setRequiredNumber(1, 4, 6);
		oldSchedule.addWorkingPeriod("Bob", 4, 6);

		int time = schedule.nextIncomplete(3);
		assertTrue(time == -1);

		compareSchedules(schedule, oldSchedule, 10);
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
