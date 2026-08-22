package com.devcanvas.backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcanvas.backend.dto.ProfileDto;
import com.devcanvas.backend.service.ProfileService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileDto> getProfile() {

        return ResponseEntity.ok(
                profileService.getProfile()
        );
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(
            @RequestBody ProfileDto dto) {

        return ResponseEntity.ok(
                profileService.createProfile(dto)
        );
    }

    @PutMapping
    public ResponseEntity<ProfileDto> updateProfile(
            @RequestBody ProfileDto dto) {

        return ResponseEntity.ok(
                profileService.updateProfile(dto)
        );
    }
}