package nz.co.fondou.service;

public interface AddressService {

	public AddressCommand getAddressByBranchName(String branchName);
	public void saveAddress(AddressCommand addressCommand);
	
}
