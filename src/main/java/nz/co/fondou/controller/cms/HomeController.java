package nz.co.fondou.controller.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nz.co.fondou.configuration.ModelAttributeConfiguration;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.service.BranchService;

@Controller
public class HomeController {
	
	private final BranchService branchService;
	private final ModelAttributeConfiguration modelAttributeConfiguration;
	
	public HomeController(BranchService branchService, ModelAttributeConfiguration modelAttributeConfiguration) {
		this.branchService = branchService;
		this.modelAttributeConfiguration = modelAttributeConfiguration;
	}

	@GetMapping(value={"/cms", "/cms/index"})
	public String home(Model model){
		Branch mainBranch = branchService.getMainBranch();
		model.addAttribute(modelAttributeConfiguration.getBranchKey(), mainBranch);
		return "cms/index";
	}
	
}
