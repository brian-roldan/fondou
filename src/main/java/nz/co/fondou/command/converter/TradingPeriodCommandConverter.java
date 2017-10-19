package nz.co.fondou.command.converter;

import static java.util.Objects.nonNull;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import nz.co.fondou.command.TradingPeriodCommand;
import nz.co.fondou.domain.DayOfWeek;
import nz.co.fondou.domain.TradingPeriod;

@Component
public class TradingPeriodCommandConverter implements CommandConverter<TradingPeriodCommand, TradingPeriod> {
	

	DateTimeFormatter timeFormat = DateTimeFormat.forPattern("H:mm");
	
	@Override
	public TradingPeriodCommand toCommand(TradingPeriod tradingPeriod) {
		return TradingPeriodCommand.builder()
				.dayTitle(tradingPeriod.getDayOfWeek().toString())
				.dayOfWeek(tradingPeriod.getDayOfWeek().ordinal())
				.openingTime(nonNull(tradingPeriod.getOpeningTime()) ? timeFormat.print(tradingPeriod.getOpeningTime()) : null)
				.closingTime(nonNull(tradingPeriod.getClosingTime()) ? timeFormat.print(tradingPeriod.getClosingTime()) : null)
				.build();
	}

	@Override
	public TradingPeriod toEntity(TradingPeriodCommand tradingPeriodCommand) {
		return TradingPeriod.builder()
				.dayOfWeek(DayOfWeek.getValue(tradingPeriodCommand.getDayOfWeek()))
				.openingTime(nonNull(tradingPeriodCommand.getOpeningTime()) ? timeFormat.parseLocalTime(tradingPeriodCommand.getOpeningTime()) : null)
				.closingTime(nonNull(tradingPeriodCommand.getClosingTime()) ? timeFormat.parseLocalTime(tradingPeriodCommand.getClosingTime()) : null)
				.build();
	}

}
