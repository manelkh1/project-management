import { UserRole } from '../models/userRole';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../services/token-storage.service';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class HasRoleGuard implements CanActivate {
  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {}

  user: any;

  canActivate(route: ActivatedRouteSnapshot): boolean {
    this.user = this.tokenStorage.getUser();
    const user = JSON.parse(this.user);

    // compare between connected user and the path user role app-routing.module.ts
    if (route.data['role'] === user.userRole) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
