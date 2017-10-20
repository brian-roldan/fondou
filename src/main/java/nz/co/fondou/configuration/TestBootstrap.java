package nz.co.fondou.configuration;

import static nz.co.fondou.domain.DayOfWeek.*;

import java.util.List;

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
		
		List<TradingPeriod> tradingPeriods = branch.getTradingPeriods();
		tradingPeriods.add(TradingPeriod.builder().dayOfWeek(MONDAY).branch(branch).openingTime(new LocalTime(9, 30)).closingTime(new LocalTime(20, 30)).build());
		tradingPeriods.add(TradingPeriod.builder().dayOfWeek(TUESDAY).branch(branch).openingTime(new LocalTime(9, 30)).closingTime(new LocalTime(20, 30)).build());
		tradingPeriods.add(TradingPeriod.builder().dayOfWeek(WEDNESDAY).branch(branch).openingTime(new LocalTime(9, 30)).closingTime(new LocalTime(20, 30)).build());
		tradingPeriods.add(TradingPeriod.builder().dayOfWeek(THURSDAY).branch(branch).openingTime(new LocalTime(9, 30)).closingTime(new LocalTime(20, 30)).build());
		tradingPeriods.add(TradingPeriod.builder().dayOfWeek(FRIDAY).branch(branch).openingTime(new LocalTime(9, 30)).closingTime(new LocalTime(20, 30)).build());
		tradingPeriods.add(TradingPeriod.builder().dayOfWeek(SATURDAY).branch(branch).openingTime(new LocalTime(9, 30)).closingTime(new LocalTime(20, 30)).build());
		tradingPeriods.add(TradingPeriod.builder().dayOfWeek(SUNDAY).branch(branch).openingTime(new LocalTime(9, 30)).closingTime(new LocalTime(20, 30)).build());
		
		branchRepository.save(branch);
		
		completed = true;
		
	}
	
	
}
