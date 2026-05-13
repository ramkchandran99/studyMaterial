package com.ram.spring.ai.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ram.spring.ai.project.service.GroqChatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@Tag(name = "ai Controller", description = "ai APIs")
public class AiController {

	private final GroqChatService chatService;

	@GetMapping("/chat")
	@Operation(summary = "chat", description = "chat")
	public String chat(@RequestParam String message) {
		return chatService.generateResponse(message);
	}

	@GetMapping("/chat/stream")
	@Operation(summary = "chat", description = "chat stream")
	public Flux<String> chatStream(@RequestParam String message) {
		return chatService.generateStreamResponse(message);
	}

}
