package com.devcanvas.backend.service;

import com.devcanvas.backend.dto.AiRequestDto;
import com.devcanvas.backend.dto.AiResponseDto;

public interface AiService {
	AiResponseDto askQuestion(AiRequestDto request);
}
