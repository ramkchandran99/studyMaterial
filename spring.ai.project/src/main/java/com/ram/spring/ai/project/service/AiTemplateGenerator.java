package com.ram.spring.ai.project.service;

import com.ram.spring.ai.project.dto.DocumentPlan;

public interface AiTemplateGenerator {

	String generatePdfContent(String userPrompt, DocumentPlan plan);

	DocumentPlan planDocument(String userPrompt);

}
