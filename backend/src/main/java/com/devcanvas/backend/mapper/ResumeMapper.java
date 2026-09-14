package com.devcanvas.backend.mapper;

import org.springframework.stereotype.Component;

import com.devcanvas.backend.dto.ResumeDto;
import com.devcanvas.backend.entity.Resume;

@Component
public class ResumeMapper {

	   public static ResumeDto toDto(Resume resume) {

	        if (resume == null) {
	            return null;
	        }

	        ResumeDto dto = new ResumeDto();

	        dto.setId(resume.getId());
	        dto.setName(resume.getName());
	        dto.setJobTitle(resume.getJobTitle());
	        dto.setExperience(resume.getExperience());
	        dto.setSkills(resume.getSkills());
	        dto.setFileName(resume.getFileName());
	        dto.setFilePath(resume.getFilePath());
	        dto.setPublicResume(resume.getPublicResume());
	        dto.setUploadedAt(resume.getUploadedAt());

	        return dto;
	    }

	    public static Resume toEntity(ResumeDto dto) {

	        if (dto == null) {
	            return null;
	        }

	        Resume resume = new Resume();

	        resume.setId(dto.getId());
	        resume.setName(dto.getName());
	        resume.setJobTitle(dto.getJobTitle());
	        resume.setExperience(dto.getExperience());
	        resume.setSkills(dto.getSkills());
	        resume.setFileName(dto.getFileName());
	        resume.setFilePath(dto.getFilePath());
	        resume.setPublicResume(dto.getPublicResume());
	        resume.setUploadedAt(dto.getUploadedAt());

	        return resume;
	    }

	    public static void updateEntity(ResumeDto dto, Resume resume) {

	        if (dto == null || resume == null) {
	            return;
	        }

	        resume.setName(dto.getName());
	        resume.setJobTitle(dto.getJobTitle());
	        resume.setExperience(dto.getExperience());
	        resume.setSkills(dto.getSkills());
	        resume.setFileName(dto.getFileName());
	        resume.setFilePath(dto.getFilePath());
	        resume.setPublicResume(dto.getPublicResume());
	    }
}