import { Component, inject } from '@angular/core';
import { Profile, ProfileService } from '../../core/services/profile.service';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  private profileService = inject(ProfileService);

  profile?: Profile;

  ngOnInit(): void {
    this.profileService.getProfile()
      .subscribe(data => {
        this.profile = data;
      });
  }

}
