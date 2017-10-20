package nz.co.fondou.command.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import nz.co.fondou.command.TimePeriod;

public class TimePeriodValidatorTest {

	TimePeriodValidator validator;
	
	@Mock
	TimePeriod timePeriod;
	@Mock
	ConstraintValidatorContext context;
	
	@Before
	public void setup() {
		initMocks(this);
		validator = new TimePeriodValidator();
	}
	
	@Test
	public void testIsValid() throws Exception {
		setTimes("00:00", "12:30");
		assertTrue(validator.isValid(timePeriod, context));
	}
	
	@Test
	public void testIsValidBlankInput() throws Exception {
		setTimes(" ", " ");
		assertTrue(validator.isValid(timePeriod, context));
		setTimes("", "");
		assertTrue(validator.isValid(timePeriod, context));
		setTimes(null, null);
		assertTrue(validator.isValid(timePeriod, context));
	}
	
	@Test
	public void testIsValidOneValidInput() throws Exception {
		setTimes("00:00", null);
		assertFalse(validator.isValid(timePeriod, context));
		setTimes(null, "12:30");
		assertFalse(validator.isValid(timePeriod, context));
	}
	
	@Test
	public void testIsValidInvalidTime() throws Exception {
		setTimes(":00", "10:30");
		assertFalse(validator.isValid(timePeriod, context));
		setTimes("10:", "10:30");
		assertFalse(validator.isValid(timePeriod, context));
		setTimes("b:30", "10:30");
		assertFalse(validator.isValid(timePeriod, context));
		setTimes("10:a", "10:30");
		assertFalse(validator.isValid(timePeriod, context));
	}
	
	@Test
	public void testIsValidEqual() throws Exception {
		setTimes("01:00", "01:00");
		assertFalse(validator.isValid(timePeriod, context));
	}
	
	@Test
	public void testIsValidSameHour() throws Exception {
		setTimes("01:29", "01:30");
		assertTrue(validator.isValid(timePeriod, context));
		setTimes("01:30", "01:29");
		assertFalse(validator.isValid(timePeriod, context));
	}
	
	private void setTimes(String startTime, String endTime) {
		when(timePeriod.getStartTime()).thenReturn(startTime);
		when(timePeriod.getEndTime()).thenReturn(endTime);
	}

}
