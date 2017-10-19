package nz.co.fondou.command.converter;

import org.springframework.stereotype.Component;

import nz.co.fondou.domain.Branch;
import nz.co.fondou.service.AddressCommand;

@Component
public class AddressCommandConverterImpl implements AddressCommandConverter{

	@Override
	public AddressCommand toCommand(Branch branch) {
		return AddressCommand.builder()
				.branchName(branch.getName())
				.address(branch.getAddress())
				.mapLink(branch.getMapLink())
				.build();
	}

	@Override
	public Branch toEntity(AddressCommand addressCommand) {
		return Branch.builder()
				.name(addressCommand.getBranchName())
				.address(addressCommand.getAddress())
				.mapLink(addressCommand.getMapLink())
				.build();
	}

}
