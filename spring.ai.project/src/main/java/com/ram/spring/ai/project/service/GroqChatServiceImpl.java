package com.ram.spring.ai.project.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class GroqChatServiceImpl implements GroqChatService {

	private final ChatClient chatClient;
	
	public GroqChatServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

	public String generateResponse(String userMessage) {
		return chatClient.prompt().user(userMessage).call().content();
	}

	public Flux<String> generateStreamResponse(String userMessage) {
		return chatClient.prompt().user(userMessage).stream().content();
	}
}