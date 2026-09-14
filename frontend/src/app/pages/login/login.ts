import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private router = inject(Router);
  private authService = inject(AuthService);

  username = '';
  password = '';

  errorMessage = '';
  loading = false;

  login(): void {

    if (!this.username || !this.password) {
      this.errorMessage = 'Username and password are required.';
      return;
    }

     this.loading = true;
    this.errorMessage = '';


    this.authService
      .login(this.username, this.password)
      .subscribe({

        next: () => {

          this.loading = false;

          sessionStorage.setItem('username', this.username);
          sessionStorage.setItem('password', this.password);
          sessionStorage.setItem('isAdmin', 'true');

          this.loading = false;

          this.router.navigate(['/admin/resume']);
        },

        error: (error) => {

          this.loading = false;

          if (error.status === 401) {

            this.errorMessage =
              'Invalid username or password.';

          } else {

            this.errorMessage =
              'Login failed. Please try again.';
          }
        }

      });
    // Authentication will be connected in the next step
    console.log('Login:', this.username);

  }
}
