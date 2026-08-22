package com.devcanvas.backend.service;

import java.util.List;

import com.devcanvas.backend.dto.ArtworkDto;

public interface ArtworkService {

    List<ArtworkDto> getAllArtworks();

    ArtworkDto getArtwork(Long id);

    ArtworkDto createArtwork(ArtworkDto dto);

    ArtworkDto updateArtwork(Long id, ArtworkDto dto);

    void deleteArtwork(Long id);
}