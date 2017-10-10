package nz.co.fondou.command.converter;

import static nz.co.fondou.domain.DayOfWeek.MONDAY;
import static nz.co.fondou.domain.DayOfWeek.TUESDAY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nz.co.fondou.command.BranchTradingPeriodCommand;
import nz.co.fondou.command.TradingPeriodCommand;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.domain.TradingPeriod;

public class BranchTradingPeriodCommandConverterTest {

	BranchTradingPeriodCommandConverter converter;
	
	@Mock TradingPeriodCommandConverter tradingPeriodCommandConverter;
	
	String testBranchName = "testBranchName";
	LocalTime testOpeningTime = new LocalTime(9, 29);
	LocalTime testClosingTime = new LocalTime(20, 29);
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		converter = new BranchTradingPeriodCommandConverter(tradingPeriodCommandConverter);
	}
	
	@Test
	public void testToCommand() throws Exception {
		Branch branch = Branch.builder().name(testBranchName).tradingPeriods(
				Arrays.asList(
						TradingPeriod.builder()
						.dayOfWeek(MONDAY)
						.openingTime(testOpeningTime)
						.closingTime(testClosingTime).build(),
						TradingPeriod.builder()
						.dayOfWeek(TUESDAY)
						.openingTime(testOpeningTime)
						.closingTime(testClosingTime).build())).build();
		when(tradingPeriodCommandConverter.toCommand(any(TradingPeriod.class))).thenReturn(new TradingPeriodCommand());
		
		BranchTradingPeriodCommand command = converter.toCommand(branch);
		
		assertNotNull(command);
		assertEquals(branch.getName(), command.getBranchName());
		assertEquals(branch.getTradingPeriods().size(), command.getTradingPeriods().size());
	}
	
	@Test
	public void testToCommandNullTradingPeriod() throws Exception {
		Branch branch = Branch.builder().name("testBranchName").tradingPeriods(null).build();
		
		BranchTradingPeriodCommand command = converter.toCommand(branch);
		
		assertNotNull(command);
		assertEquals(branch.getName(), command.getBranchName());
		assertNotNull(command.getTradingPeriods());
		assertTrue(command.getTradingPeriods().isEmpty());
	}

	@Test
	public void testToEntity() throws Exception {
		BranchTradingPeriodCommand command = BranchTradingPeriodCommand.builder().branchName(testBranchName).tradingPeriods(
				Arrays.asList(
						TradingPeriodCommand.builder()
						.dayOfWeek(MONDAY.ordinal())
						.openingTime(testOpeningTime)
						.closingTime(testClosingTime).build(),
						TradingPeriodCommand.builder()
						.dayOfWeek(TUESDAY.ordinal())
						.openingTime(testOpeningTime)
						.closingTime(testClosingTime).build())).build();
		when(tradingPeriodCommandConverter.toEntity(any(TradingPeriodCommand.class))).thenReturn(new TradingPeriod());
		
		Branch branch = converter.toEntity(command);
		
		assertNotNull(branch);
		assertEquals(command.getBranchName(), branch.getName());
		assertEquals(command.getTradingPeriods().size(), branch.getTradingPeriods().size());
	}
	
	@Test
	public void testToEntityNullTradingPeriod() {
		BranchTradingPeriodCommand command = BranchTradingPeriodCommand.builder().branchName(testBranchName).tradingPeriods(null).build();
		
		Branch branch = converter.toEntity(command);
		
		assertNotNull(branch);
		assertEquals(command.getBranchName(), branch.getName());
		assertNotNull(branch.getTradingPeriods());
		assertTrue(branch.getTradingPeriods().isEmpty());
	}

}
