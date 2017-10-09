package nz.co.fondou.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nz.co.fondou.domain.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long>{

	public Branch findBranchByName(String name);
	
}
