import { NotificationType } from './../enum/notification-type.enum';
import { NotificationService } from './../services/notification/notification.service';
import { AuthenticationService } from './../services/authentication/authentication.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

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
