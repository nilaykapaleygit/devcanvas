import { Routes } from '@angular/router';
import { Home } from './features/home/home';
import { Resume } from './features/resume/resume';
import { Projects } from './features/projects/projects';
import { Artwork } from './features/artwork/artwork';
import { Contact } from './features/contact/contact';
import { Login } from './pages/login/login';
import { authGuard } from './guards/auth.guard';
import { AdminDashboard } from './admin/admin-dashboard/admin-dashboard';

export const routes: Routes = [

  {
    path: 'admin-console-7x9',
    component: Login
  },

  {
    path: 'admin-dashboard',
    component: AdminDashboard,
    canActivate: [authGuard]
  },

  {
    path: 'admin/resume',
    component: Resume,
    canActivate: [authGuard]
  },

  {
    path: 'resume',
    component: Resume,
  },

  {
    path: 'projects',
    component: Projects
  },

  {
    path: 'artwork',
    component: Artwork
  },

  {
    path: 'contact',
    component: Contact
  },

  {
    path: '**',
    redirectTo: ''
  }
];
