import { Component, inject } from '@angular/core';
import { Profile } from '../../core/models/profile.model';
import { ProfileService } from '../../core/services/profile.service';

@Component({
  selector: 'app-resume',
  imports: [],
  templateUrl: './resume.html',
  styleUrl: './resume.css',
})
export class Resume {

    private profileService = inject(ProfileService);

  profile?: Profile;

  ngOnInit(): void {

    this.profileService
      .getProfile()
      .subscribe({

        next: (data) => {
          this.profile = data;
        },

        error: (error) => {
          console.error(
            'Failed to load profile',
            error
          );
        }
      });
  }

}
