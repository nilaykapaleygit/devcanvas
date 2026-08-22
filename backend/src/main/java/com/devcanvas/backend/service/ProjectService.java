package com.devcanvas.backend.service;

import java.util.List;

import com.devcanvas.backend.dto.ProjectDto;

public interface ProjectService {

    List<ProjectDto> getAllProjects();

    ProjectDto getProject(Long id);

    ProjectDto createProject(ProjectDto dto);

    ProjectDto updateProject(Long id, ProjectDto dto);

    void deleteProject(Long id);
}