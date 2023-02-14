import { NotificationType } from './../enum/notification-type.enum';
import { NotificationService } from './../services/notification/notification.service';
import { AuthenticationService } from './../services/authentication/authentication.service';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(private authenticationService:AuthenticationService,
  private router: Router,
  private notificationService:NotificationService,
  ){}





  canActivate(
    route: ActivatedRouteSnapshot,
    // Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree
    state: RouterStateSnapshot): boolean {
    return true;
  }
  private isUserLoggedIn(): boolean {
    if (this.authenticationService.isLoggedIn()) {
      return true;

    }
    this.router.navigate([`/login`]);
    this.notificationService.notify(NotificationType.ERROR, `Vous devez être connecter pour accéder à cette page`.toUpperCase());
    return false;
  }
}
