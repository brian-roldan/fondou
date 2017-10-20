package nz.co.fondou.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import nz.co.fondou.command.converter.AddressCommandConverter;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.repository.BranchRepository;

@Service
@AllArgsConstructor
@Transactional(readOnly=true)
public class AddressServiceImpl implements AddressService {

	private final BranchRepository branchRepository;
	private final AddressCommandConverter addressCommandConverter;
	
	@Override
	public AddressCommand getAddressByBranchName(String branchName) {
		return addressCommandConverter.toCommand(branchRepository.findBranchByName(branchName));
	}

	@Transactional
	@Override
	public void saveAddress(AddressCommand addressCommand) {
		Branch branch = branchRepository.findBranchByName(addressCommand.getBranchName());
		
		branch.setAddress(addressCommand.getAddress());
		branch.setMapLink(addressCommand.getMapLink());
		
		branchRepository.save(branch);
	}

}
