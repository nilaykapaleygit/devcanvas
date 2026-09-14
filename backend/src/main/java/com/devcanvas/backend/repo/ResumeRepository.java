package com.devcanvas.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcanvas.backend.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByPublicResumeTrue();

}
