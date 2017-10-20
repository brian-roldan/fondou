package nz.co.fondou.command;

import java.util.List;

import javax.validation.Valid;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BranchTradingPeriodCommand extends BaseBranchCommand{

	@Valid
	private List<TradingPeriodCommand> tradingPeriods;

	@Builder(builderClassName="builder")
	public BranchTradingPeriodCommand(String branchName, List<TradingPeriodCommand> tradingPeriods) {
		super(branchName);
		this.tradingPeriods = tradingPeriods; 
	}
	
}
