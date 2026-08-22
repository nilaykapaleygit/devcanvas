package com.devcanvas.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcanvas.backend.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
