package com.ram.spring.ai.project.service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.ram.spring.ai.project.exception.CustomRunTimeException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PdfGenerationServiceImpl implements PdfGenerationService {

	public byte[] generatePdf(String htmlContent) {

		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(outputStream);
			return outputStream.toByteArray();

		} catch (Exception e) {
			log.error("PDF generation failed", e);
			throw new CustomRunTimeException("Failed to generate pdf.");
		}
	}
}