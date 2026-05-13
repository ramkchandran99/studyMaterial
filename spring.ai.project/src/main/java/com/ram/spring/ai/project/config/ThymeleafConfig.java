package com.ram.spring.ai.project.config;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.springframework.context.annotation.Bean;

@Configuration
public class ThymeleafConfig {

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		StringTemplateResolver templateResolver = new StringTemplateResolver();
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCacheable(false);
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}

}
