package nz.co.fondou.command.converter;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import nz.co.fondou.command.BranchTradingPeriodCommand;
import nz.co.fondou.command.TradingPeriodCommand;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.domain.Branch.BranchBuilder;
import nz.co.fondou.domain.TradingPeriod;

@Component
@AllArgsConstructor
public class BranchTradingPeriodCommandConverterImpl implements BranchTradingPeriodCommandConverter {

	private final TradingPeriodCommandConverter tradingPeriodCommandConverter;

	@Override
	public BranchTradingPeriodCommand toCommand(Branch branch) {
		BranchTradingPeriodCommand.builder builder = BranchTradingPeriodCommand.builder();
		builder.branchName(branch.getName());
		List<TradingPeriodCommand> tradingPeriodCommands = new ArrayList<TradingPeriodCommand>();
		if(nonNull(branch.getTradingPeriods())) {
			branch.getTradingPeriods().forEach(tradingPeriod -> tradingPeriodCommands.add(tradingPeriodCommandConverter.toCommand(tradingPeriod)));
		}
		builder.tradingPeriods(tradingPeriodCommands);
		return builder.build();
	}

	@Override
	public Branch toEntity(BranchTradingPeriodCommand branchTradingPeriodcommand) {
		BranchBuilder builder = Branch.builder();
		builder.name(branchTradingPeriodcommand.getBranchName());
		List<TradingPeriod> tradingPeriods = new ArrayList<TradingPeriod>();
		if(nonNull(branchTradingPeriodcommand.getTradingPeriods())) {
			branchTradingPeriodcommand.getTradingPeriods().forEach(tradingPeriodCommand -> tradingPeriods.add(tradingPeriodCommandConverter.toEntity(tradingPeriodCommand)));
		}
		builder.tradingPeriods(tradingPeriods);
		return builder.build();
	}

}
