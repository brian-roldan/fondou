package nz.co.fondou.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.fondou.command.BranchTradingPeriodCommand;
import nz.co.fondou.command.converter.BranchTradingPeriodCommandConverter;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.repository.TradingPeriodRepository;

@Service
@Transactional(readOnly=true)
public class TradingPeriodServiceImpl implements TradingPeriodService {

	private final TradingPeriodRepository tradingPeriodRepository;
	private final BranchTradingPeriodCommandConverter branchTradingPeriodConverter;

	public TradingPeriodServiceImpl(TradingPeriodRepository tradingPeriodRepository,
			BranchTradingPeriodCommandConverter branchTradingPeriodConverter) {
		super();
		this.tradingPeriodRepository = tradingPeriodRepository;
		this.branchTradingPeriodConverter = branchTradingPeriodConverter;
	}

	@Transactional
	@Override
	public BranchTradingPeriodCommand getTradingPeriods(String branchName) {
		Branch branch = Branch.builder().name(branchName).tradingPeriods(tradingPeriodRepository.findByBranchName(branchName)).build();
		return branchTradingPeriodConverter.toCommand(branch);
	}

}
