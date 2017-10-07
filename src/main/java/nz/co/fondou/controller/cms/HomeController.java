package nz.co.fondou.controller.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value={"/cms", "/cms/index"})
	public String home(){
		return "cms/index";
	}
	
}
