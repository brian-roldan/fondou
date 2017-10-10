package nz.co.fondou.command;

import org.joda.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TradingPeriodCommand {

	private int dayOfWeek;
	private LocalTime openingTime;
	private LocalTime closingTime;
	
}
