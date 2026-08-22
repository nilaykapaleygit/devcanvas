import { Component, inject } from '@angular/core';
import { ProjectService, Project } from '../../core/services/project.service';

@Component({
  selector: 'app-projects',
  imports: [],
  templateUrl: './projects.html',
  styleUrl: './projects.css',
})
export class Projects {
  private projectService = inject(ProjectService);

  projects: Project[] = [];

  ngOnInit(): void {

    this.projectService.getAllProjects()
      .subscribe(data => {
        this.projects = data;
      });
  }
}
