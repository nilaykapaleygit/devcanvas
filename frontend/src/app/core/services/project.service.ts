import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Project } from "../models/project.model";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8083/api/projects';

  getAllProjects(): Observable<Project[]> {

    return this.http.get<Project[]>(this.apiUrl);
  }

  getProject(id: number): Observable<Project> {

    return this.http.get<Project>(
      `${this.apiUrl}/${id}`
    );
  }
}