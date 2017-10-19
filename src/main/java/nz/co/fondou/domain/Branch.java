package nz.co.fondou.domain;

import static javax.persistence.GenerationType.AUTO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Branch {

	@Id
	@GeneratedValue(strategy=AUTO)
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String name;
	
	@Column(length=100, nullable=false)
	private String address;
	
	@Column(length=200, nullable=false)
	private String mapLink; 
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy="branch")
	private List<TradingPeriod> tradingPeriods;
	
}
