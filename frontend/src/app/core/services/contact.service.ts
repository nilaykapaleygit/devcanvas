import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Observable } from "rxjs";

export interface ContactMessage {
  name: string;
  email: string;
  subject: string;
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class ContactService {


  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8083/api/contact';

  sendMessage(
    message: ContactMessage
  ): Observable<void> {

    return this.http.post<void>(
      this.apiUrl,
      message
    );
  }
}