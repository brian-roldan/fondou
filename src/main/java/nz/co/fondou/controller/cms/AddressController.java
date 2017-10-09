package nz.co.fondou.controller.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
		model.addAttribute(modelAttributeConfiguration.getBranchKey(), branch);
		return "cms/branch-info/address";
	}
	
}
