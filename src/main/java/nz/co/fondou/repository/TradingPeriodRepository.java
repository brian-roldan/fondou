package nz.co.fondou.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nz.co.fondou.domain.TradingPeriod;
import nz.co.fondou.domain.TradingPeriodId;

public interface TradingPeriodRepository extends JpaRepository<TradingPeriod, TradingPeriodId>{

	public List<TradingPeriod> findByIdBranchName(String branchName);
	
}
