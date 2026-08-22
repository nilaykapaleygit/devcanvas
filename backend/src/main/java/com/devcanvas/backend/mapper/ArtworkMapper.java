package com.devcanvas.backend.mapper;

import com.devcanvas.backend.dto.ArtworkDto;
import com.devcanvas.backend.entity.Artwork;

public class ArtworkMapper {

    private ArtworkMapper() {
    }

    public static ArtworkDto toDto(Artwork artwork) {

        if (artwork == null) {
            return null;
        }

        return ArtworkDto.builder()
                .id(artwork.getId())
                .title(artwork.getTitle())
                .description(artwork.getDescription())
                .category(artwork.getCategory())
                .imageUrl(artwork.getImageUrl())
                .featured(artwork.getFeatured())
                .build();
    }

    public static Artwork toEntity(ArtworkDto dto) {

        if (dto == null) {
            return null;
        }

        return Artwork.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .imageUrl(dto.getImageUrl())
                .featured(dto.getFeatured())
                .build();
    }
}