package nz.co.fondou.domain;

public enum DayOfWeek {

	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY,
	SUNDAY;

	public static DayOfWeek getValue(int dayOfWeek) {
		for(DayOfWeek day : DayOfWeek.values()){
			if(day.ordinal() == dayOfWeek) {
				return day;
			}
		}
		
		throw new RuntimeException("Unable to find day of week");
	}
	
}
