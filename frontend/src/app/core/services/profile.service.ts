import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Profile } from "../models/profile.model";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8083/api/profile';

  getProfile(): Observable<Profile> {
    return this.http.get<Profile>(this.apiUrl);
  }
}