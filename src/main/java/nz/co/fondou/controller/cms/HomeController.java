package nz.co.fondou.controller.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nz.co.fondou.domain.Branch;
import nz.co.fondou.service.BranchService;

@Controller
public class HomeController {
	
	private final BranchService branchService;
	private final String branchAttributeKey;
	
	public HomeController(BranchService branchService) {
		this.branchService = branchService;
		branchAttributeKey = "branch";
	}

	@GetMapping(value={"/cms", "/cms/index"})
	public String home(Model model){
		Branch mainBranch = branchService.getMainBranch();
		model.addAttribute(branchAttributeKey, mainBranch.getName());
		return "cms/index";
	}
	
}
