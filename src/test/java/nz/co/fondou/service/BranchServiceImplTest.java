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

	@Before
	public void setup() {
		initMocks(this);
		this.branchService = new BranchServiceImpl(fondouConfiguration, branchRepository);
	}

	@Test
	public void testGetMainBranchWithReturnFromRepository() {
		Branch testBranch = new Branch(testBranchId, testBranchName);

		when(fondouConfiguration.getMainBranchName()).thenReturn(testBranchName);
		when(branchRepository.findBranchByName(anyString())).thenReturn(testBranch);

		Branch branch = this.branchService.getMainBranch();

		assertEquals(testBranch, branch);
		verify(branchRepository, times(1)).findBranchByName(eq(testBranchName));
	}

	@Test(expected=DataRetrievalFailureException.class)
	public void testGetMainBranchWithoutReturnFromRepository() {
		when(branchRepository.findBranchByName(anyString())).thenReturn(null);
		
		this.branchService.getMainBranch();
	}

}
