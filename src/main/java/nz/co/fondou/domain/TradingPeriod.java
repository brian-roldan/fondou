package nz.co.fondou.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.joda.time.LocalTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class TradingPeriod {

	@EmbeddedId
	private TradingPeriodId id;
	private LocalTime openingTime;
	private LocalTime closingTime;
	
	@Builder
	public TradingPeriod(DayOfWeek dayOfWeek, Branch branch,LocalTime openingTime, LocalTime closingTime) {
		this.id = TradingPeriodId.builder().dayOfWeek(dayOfWeek).branch(branch).build();
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}
	
}
