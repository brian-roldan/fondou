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
	LocalTime testOpeningTime = new LocalTime(9, 29);
	LocalTime testClosingTime = new LocalTime(20, 29);
	
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
		assertEquals(tradingPeriod.getOpeningTime(), command.getOpeningTime());
		assertEquals(tradingPeriod.getClosingTime(), command.getClosingTime());
	}

	@Test
	public void testToEntity() throws Exception {
		TradingPeriodCommand command = TradingPeriodCommand.builder().openingTime(testOpeningTime).closingTime(testClosingTime).dayOfWeek(testDayOfWeek.ordinal()).build();
		
		TradingPeriod tradingPeriod = converter.toEntity(command);
		
		assertNotNull(tradingPeriod);
		assertEquals(command.getOpeningTime(), tradingPeriod.getOpeningTime());
		assertEquals(command.getClosingTime(), tradingPeriod.getClosingTime());
		assertEquals(command.getDayOfWeek(), tradingPeriod.getDayOfWeek().ordinal());
	}

	@Test(expected=RuntimeException.class)
	public void testToEntityInvalidDayOfWeek() throws Exception {
		TradingPeriodCommand command = TradingPeriodCommand.builder().openingTime(testOpeningTime).closingTime(testClosingTime).dayOfWeek(-20).build();
		
		converter.toEntity(command);
	}

}
