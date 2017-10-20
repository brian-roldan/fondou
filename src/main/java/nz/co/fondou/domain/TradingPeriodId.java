package nz.co.fondou.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TradingPeriodId implements Serializable {

	private static final long serialVersionUID = 1670213453756810005L;
	
	@Enumerated
	private DayOfWeek dayOfWeek;
	@ManyToOne(optional=false)
	@JoinColumn(name="branch_id")
	private Branch branch;
}
