package com.devcanvas.backend.mapper;

import org.springframework.stereotype.Component;

import com.devcanvas.backend.dto.ContactMessageDto;
import com.devcanvas.backend.entity.ContactMessage;

@Component
public class ContactMessageMapper {

    public static ContactMessageDto toDto(ContactMessage entity) {

        if (entity == null) {
            return null;
        }

        return ContactMessageDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .subject(entity.getSubject())
                .message(entity.getMessage())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static ContactMessage toEntity(ContactMessageDto dto) {

        if (dto == null) {
            return null;
        }

        return ContactMessage.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .subject(dto.getSubject())
                .message(dto.getMessage())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    public static void updateEntity(ContactMessage entity, ContactMessageDto dto) {

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setSubject(dto.getSubject());
        entity.setMessage(dto.getMessage());
    }
}