package nz.co.fondou.command.converter;

import static nz.co.fondou.domain.DayOfWeek.MONDAY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import nz.co.fondou.command.TradingPeriodCommand;
import nz.co.fondou.domain.DayOfWeek;
import nz.co.fondou.domain.TradingPeriod;

public class TradingPeriodCommandConverterTest {

	TradingPeriodCommandConverter converter;

	DayOfWeek testDayOfWeek = MONDAY;
	String testRawOpeningTime = "9:30";
	String testRawClosingTime = "20:30";
	LocalTime testOpeningTime = new LocalTime(9, 30);
	LocalTime testClosingTime = new LocalTime(20, 30);
	
	@Before
	public void setup() {
		converter = new TradingPeriodCommandConverter();
	}
	
	@Test
	public void testToCommand() throws Exception {
		TradingPeriod tradingPeriod = TradingPeriod.builder().openingTime(testOpeningTime).closingTime(testClosingTime).dayOfWeek(testDayOfWeek).build();
		
		TradingPeriodCommand command = converter.toCommand(tradingPeriod);
		
		assertNotNull(command);
		assertEquals(tradingPeriod.getDayOfWeek().ordinal(), command.getDayOfWeek());
		assertEquals(testRawOpeningTime, command.getOpeningTime());
		assertEquals(testRawClosingTime, command.getClosingTime());
	}

	@Test
	public void testToEntity() throws Exception {
		TradingPeriodCommand command = TradingPeriodCommand.builder().openingTime(testRawOpeningTime).closingTime(testRawClosingTime).dayOfWeek(testDayOfWeek.ordinal()).build();
		
		TradingPeriod tradingPeriod = converter.toEntity(command);
		
		assertNotNull(tradingPeriod);
		assertEquals(command.getDayOfWeek(), tradingPeriod.getDayOfWeek().ordinal());
		assertEquals(testOpeningTime, tradingPeriod.getOpeningTime());
		assertEquals(testClosingTime, tradingPeriod.getClosingTime());
	}

	@Test(expected=RuntimeException.class)
	public void testToEntityInvalidDayOfWeek() throws Exception {
		TradingPeriodCommand command = TradingPeriodCommand.builder().openingTime(testRawOpeningTime).closingTime(testRawClosingTime).dayOfWeek(-20).build();
		
		converter.toEntity(command);
	}

}
