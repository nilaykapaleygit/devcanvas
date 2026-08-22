import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Observable } from "rxjs";
import { Artwork } from "../../features/artwork/artwork";

export interface ArtworkInf {

  id: number;
  title: string;
  description: string;
  category: string;
  imageUrl: string;
  featured: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class ArtworkService {

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/api/artworks';

  getAllArtworks(): Observable<ArtworkInf[]> {
    return this.http.get<ArtworkInf[]>(this.apiUrl);
  }

  getArtwork(id: number): Observable<ArtworkInf> {
    return this.http.get<ArtworkInf>(
      `${this.apiUrl}/${id}`
    );
  }
}