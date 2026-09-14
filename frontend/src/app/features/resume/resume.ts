import { Component, inject, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';

import {
  ResumeInf,
  ResumeService
} from '../../core/services/resume.service';

import { AuthService } from '../../core/services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-resume',
  imports: [DatePipe, FormsModule],
  templateUrl: './resume.html',
  styleUrl: './resume.css',
})
export class Resume implements OnInit {

  private resumeService = inject(ResumeService);
  private sanitizer = inject(DomSanitizer);
  private authService = inject(AuthService);
  private router = inject(Router);

  resumes: ResumeInf[] = [];

  selectedFile: File | null = null;

  selectedResume: ResumeInf | null = null;

  selectedResumeUrl: SafeResourceUrl | null = null;

  uploadMessage = '';

  uploading = false;

  isAdmin = false;


  name = '';
  jobTitle = '';
  experience = '';
  skills = '';

  ngOnInit(): void {
  this.isAdmin =
    sessionStorage.getItem('isAdmin') === 'true';

  if (this.isAdmin) {
    this.loadResumes();
  } else {
    this.loadPublicResume();
  }
  }


  // ==============================
  // PUBLIC
  // ==============================

 loadPublicResume(): void {

  this.resumeService.getPublicResume().subscribe({

    next: (resume) => {

      this.resumes = [resume];

    },

    error: (error) => {

      console.error(
        'Failed to load public resume:',
        error
      );

      this.resumes = [];

    }

  });

}


  // ==============================
  // ADMIN
  // ==============================

 loadResumes(): void {

  this.resumeService.getAllResumes().subscribe({

    next: (resumes) => {

      this.resumes = resumes;

    },

    error: (error) => {

      console.error(
        'Failed to load resumes:',
        error
      );

    }

  });

}


  onFileSelected(event: Event): void {

    const input =
      event.target as HTMLInputElement;

    if (
      input.files &&
      input.files.length > 0
    ) {

      this.selectedFile =
        input.files[0];

      this.uploadMessage = '';

    }
  }

uploadResume(): void {

  if (!this.isAdmin) {
    return;
  }

  if (!this.name.trim()) {
    this.uploadMessage = 'Name is required.';
    return;
  }

  if (!this.jobTitle.trim()) {
    this.uploadMessage = 'Job title is required.';
    return;
  }

  if (!this.experience.trim()) {
    this.uploadMessage = 'Experience is required.';
    return;
  }

  if (!this.skills.trim()) {
    this.uploadMessage = 'Skills are required.';
    return;
  }

  if (!this.selectedFile) {
    this.uploadMessage = 'Please select a PDF file.';
    return;
  }

  this.uploading = true;
  this.uploadMessage = '';

  this.resumeService.uploadResume(
    this.name,
    this.jobTitle,
    this.experience,
    this.skills,
    this.selectedFile
  ).subscribe({

    next: () => {

      this.uploadMessage =
        'Resume uploaded successfully.';

      this.name = '';
      this.jobTitle = '';
      this.experience = '';
      this.skills = '';
      this.selectedFile = null;

      this.uploading = false;

      this.loadResumes();
    },

    error: (error) => {

      console.error(
        'Resume upload failed:',
        error
      );

      this.uploadMessage =
        'Failed to upload resume.';

      this.uploading = false;
    }

  });
}


selectResume(id: number): void {

  this.resumeService.selectPublicResume(id).subscribe({

    next: () => {

      this.loadResumes();

    },

    error: (error) => {

      console.error(
        'Failed to select resume:',
        error
      );

    }

  });
}


  deleteResume(id: number): void {

  const confirmed = confirm(
    'Are you sure you want to delete this resume?'
  );

  if (!confirmed) {
    return;
  }

  this.resumeService.deleteResume(id).subscribe({

    next: () => {

      this.loadResumes();

    },

    error: (error) => {

      console.error(
        'Failed to delete resume:',
        error
      );

    }

  });
}


  // ==============================
  // VIEW PDF
  // ==============================

viewResume(resume: ResumeInf): void {

  this.selectedResume = resume;

  let url: string;

  if (this.isAdmin) {

    url = this.resumeService.getResumeViewUrl(
      resume.id
    );

  } else {

    url = this.resumeService.getPublicResumeViewUrl();

  }

  this.selectedResumeUrl =
    this.sanitizer.bypassSecurityTrustResourceUrl(url);
}

  closeViewer(): void {

    this.selectedResume = null;

    this.selectedResumeUrl = null;
  }


  // ==============================
  // LOGOUT
  // ==============================

 logout(): void {

  this.authService.logout();

  this.router.navigate(['/admin-console-7x9']);

}
}