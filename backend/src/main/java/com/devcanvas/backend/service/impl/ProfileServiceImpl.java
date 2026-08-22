package com.devcanvas.backend.service.impl;


import org.springframework.stereotype.Service;

import com.devcanvas.backend.dto.ProfileDto;
import com.devcanvas.backend.entity.Profile;
import com.devcanvas.backend.mapper.ProfileMapper;
import com.devcanvas.backend.repo.ProfileRepository;
import com.devcanvas.backend.service.ProfileService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {


    private final ProfileRepository profileRepository;

    @Override
    @Transactional()
    public ProfileDto getProfile() {

        Profile profile = profileRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);

        return ProfileMapper.toDto(profile);
    }

    @Override
    public ProfileDto createProfile(ProfileDto dto) {

        Profile profile = ProfileMapper.toEntity(dto);

        Profile savedProfile = profileRepository.save(profile);

        return ProfileMapper.toDto(savedProfile);
    }

    @Override
    public ProfileDto updateProfile(ProfileDto dto) {

        Profile profile = ProfileMapper.toEntity(dto);

        Profile updatedProfile = profileRepository.save(profile);

        return ProfileMapper.toDto(updatedProfile);
    }
}