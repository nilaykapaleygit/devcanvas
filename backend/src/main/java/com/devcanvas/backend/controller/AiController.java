package com.devcanvas.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcanvas.backend.dto.AiRequestDto;
import com.devcanvas.backend.dto.AiResponseDto;
import com.devcanvas.backend.service.AiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

	private final AiService aiService;
	
    @PostMapping("/ask")
    public ResponseEntity<AiResponseDto> askQuestion(
            @RequestBody AiRequestDto request) {

        return ResponseEntity.ok(
                aiService.askQuestion(request)
        );
    }
}
