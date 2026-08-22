package com.devcanvas.backend.service;

import com.devcanvas.backend.dto.ContactMessageDto;

public interface ContactService {
    ContactMessageDto createMessage(ContactMessageDto dto);
}
