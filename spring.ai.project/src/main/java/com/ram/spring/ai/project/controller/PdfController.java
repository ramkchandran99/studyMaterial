package com.ram.spring.ai.project.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.spring.ai.project.dto.DocumentPlan;
import com.ram.spring.ai.project.dto.PdfRequest;
import com.ram.spring.ai.project.service.AiTemplateGenerator;
import com.ram.spring.ai.project.service.PdfGenerationService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class PdfController {

	private final PdfGenerationService pdfGenerationService;
	private final AiTemplateGenerator templateGenerator;

	@PostMapping("/generate/v2")
	@Operation(summary = "generate pdf for prompt")
	public ResponseEntity<byte[]> generatePdf(@RequestBody PdfRequest request) {
		log.info("generate pdf for prompt");

		DocumentPlan documentPlan = templateGenerator.planDocument(request.getSentence());

		String htmlContent = templateGenerator.generatePdfContent(request.getSentence(), documentPlan);

		byte[] pdfBytes = pdfGenerationService.generatePdf(htmlContent);
		String filename = documentPlan.getTitle() + "_" + System.currentTimeMillis() + ".pdf";
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
				.contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
	}
}