package nz.co.fondou.command.converter;

import org.springframework.stereotype.Component;

import nz.co.fondou.command.TradingPeriodCommand;
import nz.co.fondou.domain.DayOfWeek;
import nz.co.fondou.domain.TradingPeriod;

@Component
public class TradingPeriodCommandConverter implements CommandConverter<TradingPeriodCommand, TradingPeriod> {
	
	@Override
	public TradingPeriodCommand toCommand(TradingPeriod tradingPeriod) {
		return TradingPeriodCommand.builder()
				.dayOfWeek(tradingPeriod.getDayOfWeek().ordinal())
				.openingTime(tradingPeriod.getOpeningTime())
				.closingTime(tradingPeriod.getClosingTime())
				.build();
	}

	@Override
	public TradingPeriod toEntity(TradingPeriodCommand tradingPeriodCommand) {
		return TradingPeriod.builder()
				.dayOfWeek(DayOfWeek.getValue(tradingPeriodCommand.getDayOfWeek()))
				.openingTime(tradingPeriodCommand.getOpeningTime())
				.closingTime(tradingPeriodCommand.getClosingTime())
				.build();
	}

}
