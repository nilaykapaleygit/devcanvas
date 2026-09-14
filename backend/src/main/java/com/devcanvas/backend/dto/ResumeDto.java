package com.devcanvas.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDto {
    private Long id;

    private String name;

    private String jobTitle;

    private String experience;

    private String skills;

    private String fileName;

    private String filePath;

    private Boolean publicResume;

    private LocalDateTime uploadedAt;
}
