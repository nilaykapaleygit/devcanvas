import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ResumeInf {

  id: number;

  name: string;

  jobTitle: string;

  experience: string;

  skills: string;

  fileName: string;

  filePath: string;

  publicResume: boolean;

  uploadedAt: string;
}


@Injectable({
  providedIn: 'root'
})
export class ResumeService {

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8083/api/resumes';


  // =========================
  // ADMIN
  // =========================

  uploadResume(
    name: string,
    jobTitle: string,
    experience: string,
    skills: string,
    file: File
  ): Observable<ResumeInf> {

    const formData = new FormData();

    formData.append('name', name);
    formData.append('jobTitle', jobTitle);
    formData.append('experience', experience);
    formData.append('skills', skills);
    formData.append('file', file);

    return this.http.post<ResumeInf>(
      `${this.apiUrl}/upload`,
      formData
    );
  }


  getAllResumes(): Observable<ResumeInf[]> {

    return this.http.get<ResumeInf[]>(
      this.apiUrl
    );
  }


  getResume(id: number): Observable<ResumeInf> {

    return this.http.get<ResumeInf>(
      `${this.apiUrl}/${id}`
    );
  }


  selectPublicResume(id: number): Observable<ResumeInf> {

    return this.http.put<ResumeInf>(
      `${this.apiUrl}/${id}/select`,
      {}
    );
  }


  deleteResume(id: number): Observable<void> {

    return this.http.delete<void>(
      `${this.apiUrl}/${id}`
    );
  }


  // =========================
  // PUBLIC
  // =========================

  getPublicResume(): Observable<ResumeInf> {

    return this.http.get<ResumeInf>(
      `${this.apiUrl}/public`
    );
  }


  // =========================
  // RESUME VIEW
  // =========================

  getResumeViewUrl(id: number): string {
  return `${this.apiUrl}/${id}/view`;
}

getPublicResumeViewUrl(): string {
  return `${this.apiUrl}/public/view`;
}

}