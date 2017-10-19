package nz.co.fondou.command;

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

	private String dayTitle;
	private int dayOfWeek;
	private String openingTime;
	private String closingTime;
	
}
