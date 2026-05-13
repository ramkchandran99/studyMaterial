package com.ram.spring.ai.project.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ram.spring.ai.project.dto.DocumentPlan;
import com.ram.spring.ai.project.exception.CustomRunTimeException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AiTemplateGeneratorImpl implements AiTemplateGenerator {

	private final ObjectMapper objectMapper;
	private final GroqChatService groqChatService;

	public String generatePdfContent(String userPrompt, DocumentPlan plan) {

		String generationPrompt = String.format("""
				You are an expert XHTML template generator for PDF rendering engines.

				Generate a COMPLETE VALID XHTML document.

				USER REQUEST:
				"%s"

				DOCUMENT PLAN:
				Title: %s
				Type: %s
				Sections: %s
				Special Requirements: %s

				STRICT RULES (VERY IMPORTANT):

				1. Return ONLY XHTML
				2. DO NOT use markdown
				3. DO NOT use triple backticks
				4. DO NOT include explanations
				5. Generate STRICTLY VALID XHTML
				6. Every tag MUST be properly closed
				7. Use lowercase tags only
				8. Properly nest all elements
				9. Include:
				   <!DOCTYPE html>
				10. Root tag MUST be:
				   <html xmlns="http://www.w3.org/1999/xhtml">
				11. Include:
				   <head>
				   <meta charset="UTF-8" />
				   <title></title>
				12. CSS MUST be inside:
				   <style type="text/css">
				13. DO NOT use:
				   - JavaScript
				   - SVG
				   - Canvas
				   - iframe
				   - video
				   - form
				   - position: fixed
				   - sticky
				   - unsupported CSS
				14. Use ONLY PDF-safe CSS
				15. Use tables for tabular content
				16. Use inline-safe layouts
				17. Avoid complex flex/grid combinations
				18. Ensure print-friendly formatting
				19. Use page-break-before/page-break-after where needed
				20. Image tags MUST be self-closed:
				   <img src="" alt="" />
				21. Meta tags MUST be self-closed
				22. BR tags MUST be:
				   <br />
				23. HR tags MUST be:
				   <hr />
				24. Ensure all attribute quotes are closed
				25. DO NOT leave empty attributes
				26. Generate professional styling
				27. Avoid overlapping layouts
				28. Keep CSS simple and renderer-compatible
				29. Ensure valid table structure:
				   table > tbody > tr > td
				30. Return a COMPLETE standalone document
				31. apply page break if necessary
				32. keep it professional, minimalistic and avoid too much white space

				SAFE CSS ONLY:
				- font-family
				- font-size
				- margin
				- padding
				- border
				- border-collapse
				- width
				- height
				- text-align
				- background-color
				- color
				- display:block
				- display:inline-block

				MANDATORY DOCUMENT STRUCTURE:

				<!DOCTYPE html>
				<html xmlns="http://www.w3.org/1999/xhtml">
				<head>
				    <meta charset="UTF-8" />
				    <title>Document</title>
				    <style type="text/css">

				    </style>
				</head>
				<body>

				</body>
				</html>

				Generate the XHTML document now.
				""", userPrompt, plan.getTitle(), plan.getDocumentType(), plan.getSections(),
				plan.getSpecialRequirements());

		String htmlContent = groqChatService.generateResponse(generationPrompt);

		return cleanHtmlResponse(htmlContent);
	}

	public DocumentPlan planDocument(String userPrompt) {
		String planningPrompt = String.format("""
				You are a professional document planner.

				Analyze the following user request and return ONLY valid JSON.

				USER REQUEST:
				"%s"

				RULES:
				1. Return ONLY raw JSON
				2. No markdown
				3. No explanation
				4. No comments
				5. Ensure valid parsable JSON
				6. sections must always be an array
				7. specialRequirements must always be an array

				JSON FORMAT:
				{
				  "title": "string",
				  "documentType": "invoice|report|letter|proposal|receipt|summary|other",
				  "sections": [
				    "section name"
				  ],
				  "specialRequirements": [
				    "table",
				    "list",
				    "grid",
				    "signature",
				    "totals",
				    "pageBreak"
				  ]
				}
				""", userPrompt);

		String response = groqChatService.generateResponse(planningPrompt);
		String cleanJson = response.replaceAll("```json\\n?", "").replaceAll("```\\n?", "").trim();

		try {
			return objectMapper.readValue(cleanJson, DocumentPlan.class);
		} catch (Exception e) {
			throw new CustomRunTimeException("Failed to understand the sentence.");
		}
	}

	private String cleanHtmlResponse(String html) {

		html = html.replaceAll("```html", "").replaceAll("```", "").trim();

		Document document = Jsoup.parse(html);

		document.outputSettings().syntax(Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml)
				.prettyPrint(true);

		return document.html();
	}
}