package com.devcanvas.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcanvas.backend.dto.ContactMessageDto;
import com.devcanvas.backend.service.ContactService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactMessageController {
	
	private final ContactService contactService;
	
	@PostMapping
	public ResponseEntity<ContactMessageDto> createMessage(
	        @Valid @RequestBody ContactMessageDto dto) {

	    return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body(contactService.createMessage(dto));
	}
}
