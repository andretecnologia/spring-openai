package com.ai.api.controller;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AIController {

    private final OpenAiChatClient chatClient;
    private static final String PROMPT_PADRAO = "Reponda em português: Por favor, me forneça dez hashtags, uma do lado da outra, sem nenhum texto extra, usando o termo a seguir ";

    public AIController(OpenAiChatClient chatClient) { this.chatClient = chatClient; }

    @GetMapping("/hashtags")
    public ResponseEntity<?> hashTags(@RequestParam(defaultValue = "cloud", value = "prompt") String prompt){
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_PADRAO + prompt);
        return ResponseEntity.ok(this.chatClient.call(promptTemplate.create()).getResult().getOutput().getContent());
    }

}
