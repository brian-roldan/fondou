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

import lombok.AllArgsConstructor;
import nz.co.fondou.configuration.ModelAttributeConfiguration;
import nz.co.fondou.service.AddressCommand;
import nz.co.fondou.service.AddressService;

@Controller
@AllArgsConstructor
public class AddressController {

	private final AddressService addressService;
	private final ModelAttributeConfiguration modelAttributeConfiguration;

	@GetMapping("/cms/{branchName}/address")
	public String viewAddress(Model model, @PathVariable String branchName) {
		AddressCommand addressCommand = addressService.getAddressByBranchName(branchName);
		model.addAttribute(modelAttributeConfiguration.getBranchKey(), branchName);
		model.addAttribute(modelAttributeConfiguration.getCommandKey(), addressCommand);
		return "cms/branch-info/address";
	}

	@PostMapping("/cms/{branchName}/address")
	public String saveAddress(Model model, @PathVariable String branchName, @Valid @ModelAttribute("command") AddressCommand command, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute(modelAttributeConfiguration.getBranchKey(), branchName);
			return "cms/branch-info/address";
		}
		command.setBranchName(branchName);
		addressService.saveAddress(command);
		return format("redirect:/cms/%s/address", branchName);
	}
	
}
