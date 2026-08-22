package com.devcanvas.backend.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMessageDto {

    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    private String subject;

    @NotBlank
    private String message;

    private LocalDateTime createdAt;
}