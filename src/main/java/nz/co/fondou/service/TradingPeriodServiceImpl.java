package nz.co.fondou.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.fondou.command.BranchTradingPeriodCommand;
import nz.co.fondou.command.converter.BranchTradingPeriodCommandConverter;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.repository.BranchRepository;
import nz.co.fondou.repository.TradingPeriodRepository;

@Service
@Transactional
public class TradingPeriodServiceImpl implements TradingPeriodService {

	private final BranchRepository branchRepository;
	private final TradingPeriodRepository tradingPeriodRepository;
	private final BranchTradingPeriodCommandConverter branchTradingPeriodConverter;

	public TradingPeriodServiceImpl(BranchRepository branchRepository,
			TradingPeriodRepository tradingPeriodRepository,
			BranchTradingPeriodCommandConverter branchTradingPeriodConverter) {
		super();
		this.branchRepository = branchRepository;
		this.tradingPeriodRepository = tradingPeriodRepository;
		this.branchTradingPeriodConverter = branchTradingPeriodConverter;
	}

	@Override
	public BranchTradingPeriodCommand getTradingPeriods(String branchName) {
		Branch branch = Branch.builder().name(branchName).tradingPeriods(tradingPeriodRepository.findByIdBranchName(branchName)).build();
		return branchTradingPeriodConverter.toCommand(branch);
	}
	
	@Transactional
	@Override
	public void updateTradingHours(BranchTradingPeriodCommand command) {
		Branch branchWithTradingPeriods = branchTradingPeriodConverter.toEntity(command);
		Branch repositoryBranch = branchRepository.findBranchByName(branchWithTradingPeriods.getName());
		branchWithTradingPeriods.getTradingPeriods().forEach(tradingPeriod -> tradingPeriod.getId().setBranch(repositoryBranch));
		repositoryBranch.getTradingPeriods().clear();
		repositoryBranch.getTradingPeriods().addAll(branchWithTradingPeriods.getTradingPeriods());
		branchRepository.save(repositoryBranch);
	}

}
