package nz.co.fondou.controller.cms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import nz.co.fondou.configuration.ModelAttributeConfiguration;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.service.BranchService;

public class HomeControllerTest {

	HomeController homeController;
	
	@Mock BranchService branchService;
	@Mock Model model;
	
	MockMvc mockMvc;
	
	ModelAttributeConfiguration modelAttributeConfiguration;
	String homeView = "cms/index";
	
	@Before
	public void setUp() {
		initMocks(this);
		modelAttributeConfiguration = ModelAttributeConfiguration.builder().branchKey("branch").build();
		homeController = new HomeController(branchService, modelAttributeConfiguration);
		mockMvc = standaloneSetup(homeController).build();
	}
	
	@Test
	public void homePositiveTest() {
		when(branchService.getMainBranch()).thenReturn(new Branch());
		
		String returnedHomeUrl = homeController.home(model);
		
		assertEquals(homeView, returnedHomeUrl);
		verify(branchService, times(1)).getMainBranch();
		verify(model, times(1)).addAttribute(eq(modelAttributeConfiguration.getBranchKey()), any(Branch.class));
	}
	
	@Test
	public void basicMvcTest() throws Exception {
		when(branchService.getMainBranch()).thenReturn(new Branch());
		
		mockMvc.perform(get("/cms")).andExpect(status().isOk()).andExpect(view().name(homeView));
		mockMvc.perform(get("/cms/")).andExpect(status().isOk()).andExpect(view().name(homeView));
	}
	
	@Test
	public void basicMvcWithIndexTest() throws Exception {
		when(branchService.getMainBranch()).thenReturn(new Branch());
		
		mockMvc.perform(get("/cms/index/")).andExpect(status().isOk()).andExpect(view().name(homeView));
	}
	
}
