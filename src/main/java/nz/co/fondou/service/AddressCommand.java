package nz.co.fondou.service;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
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
	@NotBlank(message="Address cannot be empty.")
	@NotEmpty(message="Address cannot be empty.")
	@Size(max=100, message="Address can only be 100 in length.")
	private String address;
	@NotBlank(message="Map link cannot be empty.")
	@NotEmpty(message="Map link cannot be empty.")
	@Size(max=200, message="Map link can only be 200 in length.")
	private String mapLink;
	
}
