package com.devcanvas.backend.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.devcanvas.backend.dto.ResumeDto;

public interface ResumeService {

	ResumeDto uploadResume(
	        String name,
	        String jobTitle,
	        String experience,
	        String skills,
	        MultipartFile file
	);

    List<ResumeDto> getAllResumes();

    ResumeDto getResume(Long id);

    ResumeDto getPublicResume();

    ResumeDto selectPublicResume(Long id);

    void deleteResume(Long id);
}
