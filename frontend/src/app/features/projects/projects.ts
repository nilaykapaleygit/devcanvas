import { Component, inject } from '@angular/core';
import { ProjectService } from '../../core/services/project.service';
import { Project } from '../../core/models/project.model';

@Component({
  selector: 'app-projects',
  imports: [],
  templateUrl: './projects.html',
  styleUrl: './projects.css',
})
export class Projects {
 
  private projectService = inject(ProjectService);

  projects: Project[] = [];

  isLoading = true;

  errorMessage = '';

  ngOnInit(): void {
 
    this.loadProjects();
  }

  private loadProjects(): void {

    this.projectService
      .getAllProjects()
      .subscribe({

        next: (data) => {

          this.projects = data;

          this.isLoading = false;
        },

        error: (error) => {

          console.error(
            'Failed to load projects',
            error
          );

          this.errorMessage =
            'Unable to load projects.';

          this.isLoading = false;
        }
      });
  }
}
