package nz.co.fondou.command.validator;

import static java.lang.Integer.valueOf;
import static org.apache.commons.lang3.StringUtils.isBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nz.co.fondou.command.TimePeriod;

public class TimePeriodValidator implements ConstraintValidator<ValidateTimePeriod,TimePeriod> {

	private final String timeSeparator = ":";
	private final int missingIndex = -1;
	
	@Override
	public void initialize(ValidateTimePeriod constraintAnnotation) {
	}

	@Override
	public boolean isValid(TimePeriod value, ConstraintValidatorContext context) {
		
		if (isBlank(value.getStartTime()) && isBlank(value.getEndTime())) {
			return true;
		} else if ((isBlank(value.getStartTime()) && !isBlank(value.getEndTime())) || 
				(!isBlank(value.getStartTime()) && isBlank(value.getEndTime()))) {
			return false;
		}
		
		try {
			int startHour = getHour(value.getStartTime());
			int endHour = getHour(value.getEndTime());
			int startMinute = getMinute(value.getStartTime());
			int endMinute = getMinute(value.getEndTime());
			if (startHour < endHour || (startHour == endHour && startMinute < endMinute)) {
				return true;
			}
		} catch (Exception exception) {
			
		}
		
		return false;
	}

	private int getHour(String time) {
		int separatorIndex = time.indexOf(timeSeparator);
		if (separatorIndex == missingIndex) {
			throw new IllegalArgumentException("Invalid time.");
		}
		return valueOf(time.substring(0, separatorIndex));
	}

	private int getMinute(String time) {
		int separatorIndex = time.indexOf(timeSeparator);
		if (separatorIndex == missingIndex) {
			throw new IllegalArgumentException("Invalid time.");
		}
		return valueOf(time.substring(separatorIndex+1, time.length()));
	}

}
