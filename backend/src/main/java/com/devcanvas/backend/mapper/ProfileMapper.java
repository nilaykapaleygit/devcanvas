package com.devcanvas.backend.mapper;

import com.devcanvas.backend.dto.ProfileDto;
import com.devcanvas.backend.entity.Profile;

public class ProfileMapper {

    private ProfileMapper() {
    }

    public static ProfileDto toDto(Profile profile) {

        if (profile == null) {
            return null;
        }

        return ProfileDto.builder()
                .id(profile.getId())
                .name(profile.getName())
                .title(profile.getTitle())
                .bio(profile.getBio())
                .email(profile.getEmail())
                .githubUrl(profile.getGithubUrl())
                .linkedinUrl(profile.getLinkedinUrl())
                .resumeUrl(profile.getResumeUrl())
                .build();
    }

    public static Profile toEntity(ProfileDto dto) {

        if (dto == null) {
            return null;
        }

        return Profile.builder()
                .id(dto.getId())
                .name(dto.getName())
                .title(dto.getTitle())
                .bio(dto.getBio())
                .email(dto.getEmail())
                .githubUrl(dto.getGithubUrl())
                .linkedinUrl(dto.getLinkedinUrl())
                .resumeUrl(dto.getResumeUrl())
                .build();
    }
}