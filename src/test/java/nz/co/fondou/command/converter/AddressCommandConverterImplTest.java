package nz.co.fondou.command.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nz.co.fondou.domain.Branch;
import nz.co.fondou.service.AddressCommand;

public class AddressCommandConverterImplTest {

	AddressCommandConverterImpl addressCommandConverter;
	
	final String testBranchName = "Sample";
	final String testAddress = "420 Queen Street, Auckland CBD, Auckland, New Zealand";
	final String testMapLink = "https://goo.gl/maps/YXszxYWh44k";
	
	@Before
	public void setup(){
		addressCommandConverter = new AddressCommandConverterImpl();
	}
	
	@Test
	public void testToCommand(){
		Branch branch = Branch.builder()
					.name(testBranchName)
					.address(testAddress)
					.mapLink(testMapLink)
					.build();
		
		 AddressCommand addressCommand = addressCommandConverter.toCommand(branch);
		
		 assertEquals(testBranchName, addressCommand.getBranchName());
		 assertEquals(testAddress, addressCommand.getAddress());
		 assertEquals(testMapLink, addressCommand.getMapLink());
	}

	@Test
	public void testToEntity(){
		AddressCommand addressCommand = AddressCommand.builder()
				.branchName(testBranchName)
				.address(testAddress)
				.mapLink(testMapLink)
				.build();
		
		Branch branch = addressCommandConverter.toEntity(addressCommand);
		
		assertEquals(testBranchName, branch.getName());
		assertEquals(testAddress, branch.getAddress());
		assertEquals(testMapLink, branch.getMapLink());
	}

}
