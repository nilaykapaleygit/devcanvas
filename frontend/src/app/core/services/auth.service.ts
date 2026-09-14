import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8083';

  login(username: string, password: string): Observable<any> {

    const credentials = btoa(`${username}:${password}`);

    const headers = new HttpHeaders({
      Authorization: `Basic ${credentials}`
    });

    return this.http.get(
      `${this.apiUrl}/api/resumes`,
      {
        headers,
        responseType: 'text'
      }
    );
  }

  logout(): void {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('password');
    sessionStorage.removeItem('isAdmin');
  }


}