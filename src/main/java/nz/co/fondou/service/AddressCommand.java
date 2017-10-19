package nz.co.fondou.service;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddressCommand {

	private String branchName;
	@NotEmpty
	@Size(max=100)
	private String address;
	@NotEmpty
	@Size(max=200)
	private String mapLink;
	
}
