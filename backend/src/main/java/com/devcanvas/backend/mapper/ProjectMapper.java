package com.devcanvas.backend.mapper;

import com.devcanvas.backend.dto.ProjectDto;
import com.devcanvas.backend.entity.Project;

public class ProjectMapper {

    private ProjectMapper() {
    }

    public static ProjectDto toDto(Project project) {

        if (project == null) {
            return null;
        }

        return ProjectDto.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .technologies(project.getTechnologies())
                .githubUrl(project.getGithubUrl())
                .liveUrl(project.getLiveUrl())
                .imageUrl(project.getImageUrl())
                .featured(project.getFeatured())
                .build();
    }

    public static Project toEntity(ProjectDto dto) {

        if (dto == null) {
            return null;
        }

        return Project.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .technologies(dto.getTechnologies())
                .githubUrl(dto.getGithubUrl())
                .liveUrl(dto.getLiveUrl())
                .imageUrl(dto.getImageUrl())
                .featured(dto.getFeatured())
                .build();
    }
}