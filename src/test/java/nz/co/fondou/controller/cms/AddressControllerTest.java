package nz.co.fondou.controller.cms;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import nz.co.fondou.configuration.ModelAttributeConfiguration;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.service.BranchService;

public class AddressControllerTest {

	AddressController addressController;
	
	ModelAttributeConfiguration modelAttributeConfiguration;
	@Mock
	BranchService branchService;
	@Mock
	Model model;
	
	MockMvc mockMvc;
	
	private String testBranchName = "sample";
	private String testAddress = "320 Queen Street";
	private String testMapLink = "samplemap.lnk";
	
	String viewAddressView = "cms/branch-info/address";
	
	String addressUrl = format("/cms/%s/address", testBranchName);
	String saveAddressRedirectUrl = format("redirect:/cms/%s/address", testBranchName);
	
	@Before
	public void setUp() {
		initMocks(this);
		modelAttributeConfiguration = ModelAttributeConfiguration.builder().branchKey("branch").build();
		addressController = new AddressController(branchService, modelAttributeConfiguration);
		mockMvc = standaloneSetup(addressController).build();
	}
	
	@Test
	public void viewAddressTest() {
		String urlReturn = addressController.viewAddress(model, testBranchName);
		
		assertEquals(viewAddressView, urlReturn);
		verify(branchService, times(1)).getBranchByName(eq(testBranchName));
		verify(model, times(1)).addAttribute(eq(modelAttributeConfiguration.getBranchKey()), any(Branch.class));
	}
	
	@Test
	public void setAddressTest() {
		
		Branch testBranch = new Branch(null, null, testAddress, testMapLink);
		
		addressController.saveAddress(testBranchName, testBranch);
		
		assertEquals(testBranchName, testBranch.getName());
		verify(branchService, times(1)).saveAddress(eq(testBranch));
	}
	
	@Test
	public void basicGetViewAddressMvcTest() throws Exception {
		when(branchService.getMainBranch()).thenReturn(new Branch());
		
		mockMvc.perform(get(addressUrl)).andExpect(status().isOk()).andExpect(view().name(viewAddressView));
	}
	
	@Test
	public void blankBranchNameViewAddressMvcTest() throws Exception {
		when(branchService.getMainBranch()).thenReturn(new Branch());
		
		mockMvc.perform(get("/cms//address")).andExpect(status().isNotFound());
	}
	
	@Test
	public void basicPostViewAddressMvcTest() throws Exception {
		when(branchService.getMainBranch()).thenReturn(new Branch());
		
		mockMvc.perform(post(addressUrl)
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("address", testAddress)
                .param("mapLink", testMapLink)).andExpect(status().is3xxRedirection()).andExpect(view().name(saveAddressRedirectUrl));
	}
	
	@Ignore
	@Test
	public void missingPostParameterViewAddressMvcTest() throws Exception {
		when(branchService.getMainBranch()).thenReturn(new Branch());
		
		mockMvc.perform(post(addressUrl)
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("mapLink", testMapLink)).andExpect(status().is4xxClientError());
	}
	
}
