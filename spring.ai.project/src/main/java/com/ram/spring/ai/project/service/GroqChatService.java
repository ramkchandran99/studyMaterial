package com.ram.spring.ai.project.service;

import reactor.core.publisher.Flux;

public interface GroqChatService {

	String generateResponse(String userMessage);

	Flux<String> generateStreamResponse(String userMessage);

}
