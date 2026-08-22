package com.devcanvas.backend.service;

import com.devcanvas.backend.dto.ProfileDto;

public interface ProfileService {

    ProfileDto getProfile();

    ProfileDto createProfile(ProfileDto dto);

    ProfileDto updateProfile(ProfileDto dto);
}