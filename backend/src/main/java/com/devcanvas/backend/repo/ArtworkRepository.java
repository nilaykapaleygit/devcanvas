package com.devcanvas.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcanvas.backend.entity.Artwork;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
}