package nz.co.fondou.service;

import static java.lang.String.format;
import static java.util.Objects.nonNull;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import nz.co.fondou.configuration.FondouConfiguration;
import nz.co.fondou.domain.Branch;
import nz.co.fondou.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {

	private final FondouConfiguration fondouConfiguration;
	private final BranchRepository branchRepository;
	
	public BranchServiceImpl( FondouConfiguration fondouConfiguration, 
							  BranchRepository branchRepository) {
		super();
		this.fondouConfiguration = fondouConfiguration;
		this.branchRepository = branchRepository;
	}


	@Override
	public Branch getMainBranch() {
		return getBranchByName(fondouConfiguration.getMainBranchName());
	}


	@Override
	public Branch getBranchByName(String branchName) {
		Branch branch = branchRepository.findBranchByName(branchName);
		if(nonNull(branch)) {
			return branch;
		} else {
			throw new DataRetrievalFailureException(format("Unable to find main branch with name %s", branchName));
		}
	}

}
