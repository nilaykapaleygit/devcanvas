package com.devcanvas.backend.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.devcanvas.backend.dto.AiRequestDto;
import com.devcanvas.backend.dto.AiResponseDto;
import com.devcanvas.backend.dto.OpenAiRequest;
import com.devcanvas.backend.dto.OpenAiResponse;
import com.devcanvas.backend.service.AiService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    private final WebClient webClient;

   // @Value("${ai.api-key}")
   // private String apiKey;

   //@Value("${ai.base-url}")
   // private String baseUrl;
    
    @Override
    public AiResponseDto askQuestion(AiRequestDto request) {

        return new AiResponseDto(
            "Hello! I'm the DevCanvas AI assistant. " +
            "I can answer questions about my skills, projects, " +
            "experience and artwork."
        );
    }

    /*@Override
    public AiResponseDto askQuestion(AiRequestDto request) {

        OpenAiRequest.Content content =
                new OpenAiRequest.Content(
                        "input_text",
                        request.getQuestion()
                );

        OpenAiRequest.Input input =
                new OpenAiRequest.Input(
                        "user",
                        List.of(content)
                );

        OpenAiRequest openAiRequest =
                new OpenAiRequest(
                        "gpt-5-mini",
                        List.of(input)
                );

        OpenAiResponse response = webClient
                .post()
                .uri(baseUrl + "/responses")
                .header(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + apiKey
                )
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(openAiRequest)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        clientResponse ->
                                clientResponse.bodyToMono(String.class)
                                        .map(body -> new RuntimeException(
                                                "OpenAI API Error: " + body
                                        ))
                )
                .bodyToMono(OpenAiResponse.class)
                .block();

        String answer = extractAnswer(response);

        return new AiResponseDto(answer);
    }*/
    

    private String extractAnswer(OpenAiResponse response) {

        if (response == null ||
                response.getOutput() == null) {

            return "Unable to get a response from AI.";
        }

        return response.getOutput()
                .stream()
                .filter(output ->
                        output.getContent() != null)
                .flatMap(output ->
                        output.getContent().stream())
                .filter(content ->
                        "output_text".equals(content.getType()))
                .map(OpenAiResponse.Content::getText)
                .findFirst()
                .orElse("Unable to extract AI response.");
    }
}
