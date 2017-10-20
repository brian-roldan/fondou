package nz.co.fondou.configuration;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModelAttributeConfiguration {

	private final String branchKey;
	private final String commandKey;
	
}
