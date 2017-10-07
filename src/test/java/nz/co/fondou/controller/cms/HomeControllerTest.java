package nz.co.fondou.controller.cms;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class HomeControllerTest {

	HomeController homeController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp(){
		homeController = new HomeController();
		mockMvc = standaloneSetup(homeController).build();
	}
	
	@Test
	public void basicTest() throws Exception{
		mockMvc.perform(get("/cms")).andExpect(status().isOk()).andExpect(view().name("cms/index"));
		mockMvc.perform(get("/cms/")).andExpect(status().isOk()).andExpect(view().name("cms/index"));
	}
	
	@Test
	public void basicWithIndexTest() throws Exception{
		mockMvc.perform(get("/cms/index/")).andExpect(status().isOk()).andExpect(view().name("cms/index"));
	}
	
}
