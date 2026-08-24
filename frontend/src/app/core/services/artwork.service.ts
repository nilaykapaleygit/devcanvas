import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Observable } from "rxjs";
import { Artwork } from "../../features/artwork/artwork";
import { ArtworkModel } from "../models/artwork.model";

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

  private apiUrl = 'http://localhost:8083/api/artworks';

  getAllArtworks(): Observable<ArtworkModel[]> {

    return this.http.get<ArtworkModel[]>(
      this.apiUrl
    );
  }

  getArtwork(id: number): Observable<ArtworkModel> {

    return this.http.get<ArtworkModel>(
      `${this.apiUrl}/${id}`
    );
  }
}