import { Routes } from '@angular/router';
import { Home } from './features/home/home';
import { Resume } from './features/resume/resume';
import { Projects } from './features/projects/projects';
import { Artwork } from './features/artwork/artwork';
import { Contact } from './features/contact/contact';
export const routes: Routes = [

  {
    path: '',
    component: Home
  },

  {
    path: 'resume',
    component: Resume
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
