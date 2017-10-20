package nz.co.fondou.controller.cms;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import nz.co.fondou.configuration.ModelAttributeConfiguration;
import nz.co.fondou.service.AddressCommand;
import nz.co.fondou.service.AddressService;

public class AddressControllerTest {

	AddressController addressController;
	
	ModelAttributeConfiguration modelAttributeConfiguration;
	@Mock
	AddressService addressService;
	@Mock
	Model model;
	@Mock
	BindingResult errors;
	
	MockMvc mockMvc;
	
	final String testBranchName = "sample";
	final String testAddress = "320 Queen Street";
	final String testMapLink = "samplemap.lnk";
	
	final String viewAddressView = "cms/branch-info/address";
	
	final String addressUrl = format("/cms/%s/address", testBranchName);
	final String saveAddressRedirectUrl = format("redirect:/cms/%s/address", testBranchName);
	
	@Before
	public void setup() {
		initMocks(this);
		modelAttributeConfiguration = ModelAttributeConfiguration.builder().branchKey("branch").commandKey("command").build();
		addressController = new AddressController(addressService, modelAttributeConfiguration);
		mockMvc = standaloneSetup(addressController).build();
	}

	@Test
	public void testViewAddress() {
		when(addressService.getAddressByBranchName(testBranchName)).thenReturn(new AddressCommand());
		
		String urlReturn = addressController.viewAddress(model, testBranchName);
		
		assertEquals(viewAddressView, urlReturn);
		verify(addressService, times(1)).getAddressByBranchName(eq(testBranchName));
		verify(model, times(1)).addAttribute(eq(modelAttributeConfiguration.getBranchKey()), isA(String.class));
		verify(model, times(1)).addAttribute(eq(modelAttributeConfiguration.getCommandKey()), isA(AddressCommand.class));
	}

	@Test
	public void testSaveAddress() {
		AddressCommand addressCommand = AddressCommand.builder().address(testAddress).mapLink(testMapLink).build();
		
		addressController.saveAddress(model, testBranchName, addressCommand, errors);
		
		assertEquals(testBranchName, addressCommand.getBranchName());
		verify(addressService, times(1)).saveAddress(eq(addressCommand));
	}

	@Test
	public void testSaveAddressWithErrors() {
		AddressCommand addressCommand = AddressCommand.builder().address(testAddress).build();
		when(errors.hasErrors()).thenReturn(true);
		addressController.saveAddress(model, testBranchName, addressCommand, errors);
		verify(addressService, never()).saveAddress(eq(addressCommand));
	}
	
	@Test
	public void testBasicGetViewAddressMvc() throws Exception {
		when(addressService.getAddressByBranchName(testBranchName)).thenReturn(new AddressCommand());
		
		mockMvc.perform(get(addressUrl)).andExpect(status().isOk()).andExpect(view().name(viewAddressView));
	}
	
	@Test
	public void testBlankBranchNameViewAddressMvc() throws Exception {
		mockMvc.perform(get("/cms//address")).andExpect(status().isNotFound());
	}
	
	@Test
	public void testBasicPostViewAddressMvc() throws Exception {
		mockMvc.perform(post(addressUrl)
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("address", testAddress)
                .param("mapLink", testMapLink)).andExpect(status().is3xxRedirection()).andExpect(view().name(saveAddressRedirectUrl));
	}
	
	@Test
	public void testMissingPostParameterViewAddressMvc() throws Exception {
		mockMvc.perform(post(addressUrl)
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("mapLink", testMapLink))
		.andExpect(status().isOk())
		.andExpect(view().name(viewAddressView))
		.andExpect(model().attributeHasFieldErrors("command", "address"));
	}
	
}
