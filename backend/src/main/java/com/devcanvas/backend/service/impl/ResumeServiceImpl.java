package com.devcanvas.backend.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devcanvas.backend.dto.ResumeDto;
import com.devcanvas.backend.entity.Resume;
import com.devcanvas.backend.mapper.ResumeMapper;
import com.devcanvas.backend.repo.ResumeRepository;
import com.devcanvas.backend.service.ResumeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
	
    private final ResumeRepository resumeRepository;

    private static final String UPLOAD_DIR = "uploads/resumes/";

    
    @Override
    public ResumeDto uploadResume(
            String name,
            String jobTitle,
            String experience,
            String skills,
            MultipartFile file) {

        if (name == null || name.isBlank()) {
            throw new RuntimeException("Name is required");
        }

        if (jobTitle == null || jobTitle.isBlank()) {
            throw new RuntimeException("Job title is required");
        }

        if (experience == null || experience.isBlank()) {
            throw new RuntimeException("Experience is required");
        }

        if (skills == null || skills.isBlank()) {
            throw new RuntimeException("Skills are required");
        }

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Resume file is required");
        }

        String contentType = file.getContentType();

        if (!"application/pdf".equalsIgnoreCase(contentType)) {
            throw new RuntimeException("Only PDF files are allowed");
        }

        try {

            Path uploadPath = Paths.get(UPLOAD_DIR);

            Files.createDirectories(uploadPath);

            String fileName =
                    System.currentTimeMillis()
                    + "_"
                    + file.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    filePath
            );

            Resume resume = new Resume();

            resume.setName(name);
            resume.setJobTitle(jobTitle);
            resume.setExperience(experience);
            resume.setSkills(skills);

            resume.setFileName(
                    file.getOriginalFilename()
            );

            resume.setFilePath(
                    filePath.toString()
            );

            resume.setPublicResume(false);

            Resume savedResume =
                    resumeRepository.save(resume);

            return ResumeMapper.toDto(savedResume);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to upload resume",
                    e
            );
        }
    }

	@Override
	public List<ResumeDto> getAllResumes() {
		// TODO Auto-generated method stub

        return resumeRepository.findAll()
                .stream()
                .map(ResumeMapper::toDto)
                .toList();
	}

	@Override
	public ResumeDto getResume(Long id) {
		// TODO Auto-generated method stub
		Resume resume = resumeRepository.findById(id).orElseThrow(()-> new RuntimeException("Resume not found with id:"+id));
		
		return ResumeMapper.toDto(resume);
	}

	@Override
	public ResumeDto getPublicResume() {
		// TODO Auto-generated method stub

        Resume resume = resumeRepository.findByPublicResumeTrue()
                .orElseThrow(() ->
                        new RuntimeException(
                                "No public resume available"
                        ));

        return  ResumeMapper.toDto(resume);
	}

	@Override
	public ResumeDto selectPublicResume(Long id) {
		// TODO Auto-generated method stub
		   Resume selectedResume = resumeRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException(
	                                "Resume not found with id: " + id
	                        ));
		   
		List<Resume> resumes = resumeRepository.findAll();
		
		for(Resume resume: resumes) {
			resume.setPublicResume(false);
		}
		
		selectedResume.setPublicResume(true);
		resumeRepository.saveAll(resumes);

		return ResumeMapper.toDto(selectedResume);
	}

	@Override
	public void deleteResume(Long id) {
		// TODO Auto-generated method stub

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Resume not found with id: " + id
                        ));
        
        try {
        	Files.deleteIfExists(Paths.get(resume.getFilePath()));
        }catch(IOException e) {
        	throw new RuntimeException("Failed to delete resume file",e);
        }
        
        resumeRepository.delete(resume);
	}

}
