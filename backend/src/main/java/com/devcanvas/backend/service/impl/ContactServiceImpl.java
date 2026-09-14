package com.devcanvas.backend.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.devcanvas.backend.dto.ContactMessageDto;
import com.devcanvas.backend.entity.ContactMessage;
import com.devcanvas.backend.mapper.ContactMessageMapper;
import com.devcanvas.backend.repo.ContactMessageRepository;
import com.devcanvas.backend.service.ContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService  {

    private final ContactMessageRepository repository;

    @Operation(
    	    summary = "Send contact message",
    	    description = "Allows visitors to send a message"
    	)
    	@ApiResponse(
    	    responseCode = "201",
    	    description = "Message successfully created"
    	)
    	@ApiResponse(
    	    responseCode = "400",
    	    description = "Invalid contact information"
    	)
    @Override
    public ContactMessageDto createMessage(ContactMessageDto dto) {

        ContactMessage message = ContactMessage.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .subject(dto.getSubject())
                .message(dto.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        ContactMessage saved = repository.save(message);

        return ContactMessageMapper.toDto(saved);
    }
}
