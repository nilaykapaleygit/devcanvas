import { Component, inject } from '@angular/core';
import { ArtworkInf, ArtworkService } from '../../core/services/artwork.service';
import { ArtworkModel } from '../../core/models/artwork.model';

@Component({
  selector: 'app-artwork',
  imports: [],
  templateUrl: './artwork.html',
  styleUrl: './artwork.css',
})
export class Artwork {
  private artworkService = inject(ArtworkService);

  artworks: ArtworkModel[] = [];

  isLoading = true;

  errorMessage = '';

  ngOnInit(): void {

    this.loadArtworks();
  }

  private loadArtworks(): void {

    this.artworkService
      .getAllArtworks()
      .subscribe({

        next: (data) => {

          this.artworks = data;

          this.isLoading = false;
        },

        error: (error) => {

          console.error(
            'Failed to load artworks',
            error
          );

          this.errorMessage =
            'Unable to load artwork.';

          this.isLoading = false;
        }
      });
  }
}
