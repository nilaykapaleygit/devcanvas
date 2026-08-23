import { Component, inject } from '@angular/core';
import { Profile } from '../../core/models/profile.model';
import { ProfileService } from '../../core/services/profile.service';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  private profileService = inject(ProfileService);

  profile?: Profile;

  ngOnInit(): void {

    this.profileService
      .getProfile()
      .subscribe(data => {
        this.profile = data;
      });
  }
}
