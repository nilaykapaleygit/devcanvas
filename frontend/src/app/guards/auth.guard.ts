import { inject } from "@angular/core";
import { CanActivateFn, Router } from "@angular/router";


export const authGuard: CanActivateFn = () => {
  const router = inject(Router);

  const isAdmin =
    sessionStorage.getItem('isAdmin') === 'true';

  if (isAdmin) {
    return true;
  }

  router.navigate(['/admin-console-7x9']);

  return false;
}