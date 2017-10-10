package nz.co.fondou.service;

import nz.co.fondou.domain.Branch;

public interface BranchService {

	public Branch getMainBranch();
	public Branch getBranchByName(String branchName);
	
	public void saveAddress(Branch branch);
	
}
