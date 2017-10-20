package nz.co.fondou.controller.cms;

import static java.lang.String.format;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import nz.co.fondou.command.BranchTradingPeriodCommand;
import nz.co.fondou.configuration.ModelAttributeConfiguration;
import nz.co.fondou.service.TradingPeriodService;

@Controller
public class TradingPeriodController {

	private final ModelAttributeConfiguration modelAttributeConfiguration;
	private final TradingPeriodService tradingPeriodService;

	public TradingPeriodController(ModelAttributeConfiguration modelAttributeConfiguration,
			TradingPeriodService tradingPeriodService) {
		this.modelAttributeConfiguration = modelAttributeConfiguration;
		this.tradingPeriodService = tradingPeriodService;
	}

	@GetMapping("/cms/{branchName}/tradingPeriods")
	private String showTradingHours(@PathVariable String branchName, Model model) {
		BranchTradingPeriodCommand command = tradingPeriodService.getTradingPeriods(branchName);
		model.addAttribute(modelAttributeConfiguration.getBranchKey(), branchName);
		model.addAttribute(modelAttributeConfiguration.getCommandKey(), command);
		return "cms/branch-info/trading-periods";
	}

	@PostMapping("/cms/{branchName}/tradingPeriods")
	private String saveTradingHours(Model model, @PathVariable String branchName, @Valid @ModelAttribute("command") BranchTradingPeriodCommand command, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute(modelAttributeConfiguration.getBranchKey(), branchName);
			return "cms/branch-info/trading-periods";
		}
		command.setBranchName(branchName);
		tradingPeriodService.updateTradingHours(command);
		return format("redirect:/cms/%s/tradingPeriods", branchName);
	}
	
}
