package nz.co.fondou.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.dao.DataRetrievalFailureException;

import nz.co.fondou.configuration.FondouConfiguration;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.repository.BranchRepository;

public class BranchServiceImplTest {

	BranchServiceImpl branchService;

	@Mock
	FondouConfiguration fondouConfiguration;
	@Mock
	BranchRepository branchRepository;

	final Long testBranchId = 1L;
	final String testBranchName = "Sample Name";
	final String testAddress = "address";
	final String testMapLink = "address.lnk";
	final String testNewAddress = "NEW address";
	final String testNewMapLink = "differentaddress.lnk";

	@Before
	public void setup() {
		initMocks(this);
		this.branchService = new BranchServiceImpl(fondouConfiguration, branchRepository);
	}

	@Test
	public void testGetBranchByNameWithReturnFromRepository() {
		Branch testBranch = new Branch(testBranchId, testBranchName, testAddress, testMapLink);

		when(branchRepository.findBranchByName(anyString())).thenReturn(testBranch);

		Branch branch = this.branchService.getBranchByName(testBranchName);

		assertEquals(testBranch, branch);
		verify(branchRepository, times(1)).findBranchByName(eq(testBranchName));
	}

	@Test(expected=DataRetrievalFailureException.class)
	public void testGetBranchByNameWithoutReturnFromRepository() {
		when(branchRepository.findBranchByName(anyString())).thenReturn(null);
		
		this.branchService.getBranchByName(testBranchName);
	}
	
	@Test 
	public void testSaveAddress() {
		Branch savedBranch = new Branch(testBranchId, testBranchName, testAddress, testMapLink);
		Branch newBranch = new Branch(null, testBranchName, testNewAddress, testNewMapLink);
		
		when(branchRepository.findBranchByName(newBranch.getName())).thenReturn(savedBranch);
		
		branchService.saveAddress(newBranch);
		
		assertEquals(testNewAddress, savedBranch.getAddress());
		assertEquals(testNewMapLink, savedBranch.getMapLink());
		verify(branchRepository, times(1)).save(eq(savedBranch));
		
	}

}
