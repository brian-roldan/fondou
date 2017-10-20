package nz.co.fondou.service;

import nz.co.fondou.command.BranchTradingPeriodCommand;

public interface TradingPeriodService {

	public BranchTradingPeriodCommand getTradingPeriods(String branchName);
	public void updateTradingHours(BranchTradingPeriodCommand command);
	
}
