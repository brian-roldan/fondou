package nz.co.fondou.domain;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TradingPeriod {

	@Id
	@GeneratedValue(strategy=AUTO)
	private Long id;
	@Column(nullable = false)
	@Enumerated
	private DayOfWeek dayOfWeek;
	private LocalTime openingTime;
	private LocalTime closingTime;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="branch_id")
	private Branch branch;
	
}
