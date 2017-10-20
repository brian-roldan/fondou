package nz.co.fondou.command;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nz.co.fondou.command.validator.ValidateTimePeriod;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ValidateTimePeriod
public class TradingPeriodCommand implements TimePeriod {

	private String dayTitle;
	private int dayOfWeek;
	@NotBlank(message="Cannot be blank.")
	@Pattern(regexp="^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])$", message="Invalid format.")
	private String openingTime;
	@NotBlank(message="Cannot be blank.")
	@Pattern(regexp="^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])$", message="Invalid format.")
	private String closingTime;
	
	@Override
	public String getStartTime() {
		return this.openingTime;
	}
	@Override
	public String getEndTime() {
		return this.closingTime;
	}
	
}
