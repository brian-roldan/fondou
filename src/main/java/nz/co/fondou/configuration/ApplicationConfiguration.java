package nz.co.fondou.configuration;

import static org.apache.commons.lang3.StringUtils.isBlank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public FondouConfiguration fondouConfiguration(@Value("${fondou.branch.main.name}") String mainBranchName) {
		if (isBlank(mainBranchName)) {
			throw new RuntimeException("Main branch name is missing.");
		}
		return new FondouConfiguration(mainBranchName);
	}
	
	@Bean
	public ModelAttributeConfiguration modelAttributeConfiguration(){
		return ModelAttributeConfiguration.builder()
				.branchKey("branch")
				.commandKey("command")
				.build();
	}

}
