package com.ram.spring.ai.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ram.spring.ai.project.service.GroqChatService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

	private final GroqChatService chatService;

	@GetMapping("/chat")
	public String chat(@RequestParam String message) {
		return chatService.generateResponse(message);
	}

	@GetMapping("/chat/stream")
	public Flux<String> chatStream(@RequestParam String message) {
		return chatService.generateStreamResponse(message);
	}

}
