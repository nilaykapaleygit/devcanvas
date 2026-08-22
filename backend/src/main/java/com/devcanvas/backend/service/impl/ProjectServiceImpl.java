package com.devcanvas.backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devcanvas.backend.dto.ProjectDto;
import com.devcanvas.backend.entity.Project;
import com.devcanvas.backend.mapper.ProjectMapper;
import com.devcanvas.backend.repo.ProjectRepository;
import com.devcanvas.backend.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepository projectRepository;
	
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		 this.projectRepository = projectRepository;
	}
	
	@Override
	public List<ProjectDto> getAllProjects() {
		// TODO Auto-generated method stub		
		return projectRepository.findAll().stream().map(ProjectMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public ProjectDto getProject(Long id) {
		// TODO Auto-generated method stub
		
		Project project = projectRepository.findById(id)
				.orElseThrow(()->  new RuntimeException("Project Not Found"));
		
		return ProjectMapper.toDto(project);
	}

	@Override
	public ProjectDto createProject(ProjectDto dto) {
		// TODO Auto-generated method stub
		
		Project project = ProjectMapper.toEntity(dto);
		Project savedProject = projectRepository.save(project);
		
		return ProjectMapper.toDto(savedProject);
	}

	@Override
	public ProjectDto updateProject(Long id, ProjectDto dto) {
		// TODO Auto-generated method stub
		
		Project project = projectRepository.findById(id).orElseThrow(
				()-> new RuntimeException());
		
		project.setTitle(dto.getTitle());
		project.setDescription(dto.getDescription());
		project.setTechnologies(dto.getTechnologies());
		project.setGithubUrl(dto.getGithubUrl());
		project.setLiveUrl(dto.getLiveUrl());
		project.setImageUrl(dto.getImageUrl());
		project.setFeatured(dto.getFeatured());
		
		Project savedProject = projectRepository.save(project);
		
		return ProjectMapper.toDto(savedProject);
	}

	@Override
	public void deleteProject(Long id) {
		// TODO Auto-generated method stub

        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }

        projectRepository.deleteById(id);
	}

}
