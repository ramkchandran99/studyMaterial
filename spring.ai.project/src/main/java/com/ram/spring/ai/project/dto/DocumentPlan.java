package com.ram.spring.ai.project.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentPlan {
	
	private String title;
	private String documentType;
	private List<String> sections;
	private List<String> specialRequirements;
}
