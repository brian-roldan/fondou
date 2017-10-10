package nz.co.fondou.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DayOfWeekTest {

	@Test
	public void testGetValue() throws Exception {
		DayOfWeek result = DayOfWeek.getValue(DayOfWeek.FRIDAY.ordinal());
		assertEquals(DayOfWeek.FRIDAY, result);
	}

	@Test(expected=RuntimeException.class)
	public void testGetValueInvalidValue() throws Exception {
		DayOfWeek.getValue(-1);
	}

}
