package com.devcanvas.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcanvas.backend.dto.ArtworkDto;
import com.devcanvas.backend.service.ArtworkService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/artworks")
@RequiredArgsConstructor
public class ArtworkController {

    private final ArtworkService artworkService;

    @GetMapping
    public ResponseEntity<List<ArtworkDto>> getAllArtworks() {

        return ResponseEntity.ok(
                artworkService.getAllArtworks()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtworkDto> getArtwork(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                artworkService.getArtwork(id)
        );
    }

    @PostMapping
    public ResponseEntity<ArtworkDto> createArtwork(
            @RequestBody ArtworkDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(artworkService.createArtwork(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtworkDto> updateArtwork(
            @PathVariable Long id,
            @RequestBody ArtworkDto dto) {

        return ResponseEntity.ok(
                artworkService.updateArtwork(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(
            @PathVariable Long id) {

        artworkService.deleteArtwork(id);

        return ResponseEntity.noContent().build();
    }
}