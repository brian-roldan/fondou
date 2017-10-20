package nz.co.fondou.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nz.co.fondou.domain.TradingPeriod;

public interface TradingPeriodRepository extends JpaRepository<TradingPeriod, Long>{

	public List<TradingPeriod> findByBranchName(String branchName);
	
}
