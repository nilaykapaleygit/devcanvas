package com.devcanvas.backend.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.devcanvas.backend.dto.ContactMessageDto;
import com.devcanvas.backend.entity.ContactMessage;
import com.devcanvas.backend.mapper.ContactMessageMapper;
import com.devcanvas.backend.repo.ContactMessageRepository;
import com.devcanvas.backend.service.ContactService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService  {

    private final ContactMessageRepository repository;

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
