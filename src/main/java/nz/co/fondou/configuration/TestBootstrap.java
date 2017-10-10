package nz.co.fondou.configuration;

import static nz.co.fondou.domain.DayOfWeek.FRIDAY;
import static nz.co.fondou.domain.DayOfWeek.MONDAY;
import static nz.co.fondou.domain.DayOfWeek.SATURDAY;
import static nz.co.fondou.domain.DayOfWeek.SUNDAY;
import static nz.co.fondou.domain.DayOfWeek.THURSDAY;
import static nz.co.fondou.domain.DayOfWeek.TUESDAY;
import static nz.co.fondou.domain.DayOfWeek.WEDNESDAY;

import org.joda.time.LocalTime;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import nz.co.fondou.domain.Branch;
import nz.co.fondou.domain.TradingPeriod;
import nz.co.fondou.repository.BranchRepository;

@Component
@Profile("test")
public class TestBootstrap {

	private boolean completed = false;
	
	private final FondouConfiguration fondouConfiguration;
	
	private final BranchRepository branchRepository;

	public TestBootstrap(FondouConfiguration fondouConfiguration, BranchRepository branchRepository) {
		this.fondouConfiguration = fondouConfiguration;
		this.branchRepository = branchRepository;
	}

	@Transactional
	@EventListener(ContextRefreshedEvent.class)
	public void initializeDatabaseValues() {
		
		if(completed) {
			return;
		}
		Branch branch = branchRepository.findBranchByName(fondouConfiguration.getMainBranchName());
		branch.getTradingPeriods().add(new TradingPeriod(null, MONDAY	, new LocalTime(9, 29), new LocalTime(20, 29), branch));
		branch.getTradingPeriods().add(new TradingPeriod(null, TUESDAY	, new LocalTime(9, 29), new LocalTime(20, 29), branch));
		branch.getTradingPeriods().add(new TradingPeriod(null, WEDNESDAY, new LocalTime(9, 29), new LocalTime(20, 29), branch));
		branch.getTradingPeriods().add(new TradingPeriod(null, THURSDAY	, new LocalTime(9, 29), new LocalTime(20, 29), branch));
		branch.getTradingPeriods().add(new TradingPeriod(null, FRIDAY	, new LocalTime(9, 29), new LocalTime(20, 29), branch));
		branch.getTradingPeriods().add(new TradingPeriod(null, SATURDAY	, new LocalTime(9, 29), new LocalTime(20, 29), branch));
		branch.getTradingPeriods().add(new TradingPeriod(null, SUNDAY	, new LocalTime(9, 29), new LocalTime(20, 29), branch));
		
		branchRepository.save(branch);
		
		completed = true;
		
	}
	
	
}
