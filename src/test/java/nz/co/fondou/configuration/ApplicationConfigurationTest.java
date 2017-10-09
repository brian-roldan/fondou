package nz.co.fondou.configuration;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ApplicationConfigurationTest {

	ApplicationConfiguration applicationConfiguration;
	
	@Before
	public void setup(){
		this.applicationConfiguration = new ApplicationConfiguration();
	}
	
	@Test
	public void testFondouConfigurationWithProperMainBranchName(){
		String mainBranchName = "Sample Name";
		
		FondouConfiguration fondouConfiguration = applicationConfiguration.fondouConfiguration(mainBranchName);
		
		assertEquals(mainBranchName, fondouConfiguration.getMainBranchName());		
	}
	
	@Test(expected=RuntimeException.class)
	public void testFondouConfigurationWithBlankMainBranchName(){
		String mainBranchName = EMPTY;
		
		applicationConfiguration.fondouConfiguration(mainBranchName);
	}
	
	@Test(expected=RuntimeException.class)
	public void testFondouConfigurationWithWhiteSpaceMainBranchName(){
		String mainBranchName = " ";
		
		applicationConfiguration.fondouConfiguration(mainBranchName);
	}
	
	@Test(expected=RuntimeException.class)
	public void testFondouConfigurationWithNullMainBranchName(){
		String mainBranchName = null;
		
		applicationConfiguration.fondouConfiguration(mainBranchName);
	}
	
}
