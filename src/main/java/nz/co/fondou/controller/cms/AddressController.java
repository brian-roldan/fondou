package nz.co.fondou.controller.cms;

import static java.lang.String.format;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import nz.co.fondou.configuration.ModelAttributeConfiguration;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.service.BranchService;

@Controller
public class AddressController {

	private final BranchService branchService;
	private final ModelAttributeConfiguration modelAttributeConfiguration;
	
	public AddressController(BranchService branchService, ModelAttributeConfiguration modelAttributeConfiguration) {
		this.branchService = branchService;
		this.modelAttributeConfiguration = modelAttributeConfiguration;
	}
	
	@GetMapping("/cms/{branchName}/address")
	public String viewAddress(Model model, @PathVariable String branchName) {
		Branch branch = branchService.getBranchByName(branchName);
		model.addAttribute(modelAttributeConfiguration.getBranchKey(), branchName);
		model.addAttribute(modelAttributeConfiguration.getCommandKey(), branch);
		return "cms/branch-info/address";
	}

	@PostMapping("/cms/{branchName}/address")
	public String saveAddress(@PathVariable String branchName, Branch branch) {
		branch.setName(branchName);
		branchService.saveAddressByName(branch);
		return format("redirect:/cms/%s/address", branchName);
	}
	
}
