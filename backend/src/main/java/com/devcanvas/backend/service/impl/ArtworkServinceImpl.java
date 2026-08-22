package com.devcanvas.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devcanvas.backend.dto.ArtworkDto;
import com.devcanvas.backend.entity.Artwork;
import com.devcanvas.backend.mapper.ArtworkMapper;
import com.devcanvas.backend.repo.ArtworkRepository;
import com.devcanvas.backend.service.ArtworkService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtworkServinceImpl implements ArtworkService{

    private final ArtworkRepository artworkRepository;

    @Override
    @Transactional()
    public List<ArtworkDto> getAllArtworks() {

        return artworkRepository.findAll()
                .stream()
                .map(ArtworkMapper::toDto)
                .toList();
    }

    @Override
    @Transactional()
    public ArtworkDto getArtwork(Long id) {

        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Artwork not found with id: " + id));

        return ArtworkMapper.toDto(artwork);
    }

    @Override
    public ArtworkDto createArtwork(ArtworkDto dto) {

        Artwork artwork = ArtworkMapper.toEntity(dto);

        Artwork savedArtwork = artworkRepository.save(artwork);

        return ArtworkMapper.toDto(savedArtwork);
    }

    @Override
    public ArtworkDto updateArtwork(Long id, ArtworkDto dto) {

        Artwork existingArtwork = artworkRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Artwork not found with id: " + id));

        existingArtwork.setTitle(dto.getTitle());
        existingArtwork.setDescription(dto.getDescription());
        existingArtwork.setCategory(dto.getCategory());
        existingArtwork.setImageUrl(dto.getImageUrl());
        existingArtwork.setFeatured(dto.getFeatured());

        Artwork updatedArtwork =
                artworkRepository.save(existingArtwork);

        return ArtworkMapper.toDto(updatedArtwork);
    }

    @Override
    public void deleteArtwork(Long id) {

        if (!artworkRepository.existsById(id)) {
            throw new RuntimeException(
                    "Artwork not found with id: " + id);
        }

        artworkRepository.deleteById(id);
    }
}
