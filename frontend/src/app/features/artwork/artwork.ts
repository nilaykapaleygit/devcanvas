import { Component, inject } from '@angular/core';
import { ArtworkInf, ArtworkService } from '../../core/services/artwork.service';

@Component({
  selector: 'app-artwork',
  imports: [],
  templateUrl: './artwork.html',
  styleUrl: './artwork.css',
})
export class Artwork {

  private artworkService = inject(ArtworkService);

  artworks: ArtworkInf[] = [];

  ngOnInit(): void {

    this.artworkService.getAllArtworks().subscribe(data => {
        this.artworks = data;
      });
  }
}
