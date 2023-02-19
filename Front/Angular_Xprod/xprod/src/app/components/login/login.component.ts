import { HeaderType } from './../../enum/header-type.enum';
import { NotificationService } from './../../services/notification/notification.service';
import { NotificationType } from './../../enum/notification-type.enum';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { NotificationModule } from './../../notification.module';
import { Router } from '@angular/router';
import { AuthenticationService } from './../../services/authentication/authentication.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { User } from 'src/app/models/user/user';
import { Subscription } from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit,OnDestroy{

  public showLoading: boolean = true;
  private subscriptions : Subscription[] = []


  constructor(private router:Router,
    private authenticationService:AuthenticationService,
    private notificationModule:NotificationModule,
    private notificationService:NotificationService,

    ){
      this.showLoading = false;
    }



  ngOnInit(): void {
    if(this.authenticationService.isLoggedIn()){
      this.router.navigateByUrl('/user/management')
      console.log("works");

    }else{
      this.router.navigateByUrl('/login')
      console.log("nope");

    }
  }

  public onLogin(user:User){
    this.showLoading=true;
    console.log(user)
    this.subscriptions.push(
      this.authenticationService.login(user).subscribe(
       (response : HttpResponse<User> | any) =>{
          const token = response.headers.get(HeaderType.JWT_TOKEN);
          this.authenticationService.saveToken(token);
          this.authenticationService.addUserToLocalCache(response.body);
          this.router.navigateByUrl('/user/management')
          this.showLoading = false;
        },
        (errorResponse:HttpErrorResponse) => {
          this.sendErrorNotification(NotificationType.ERROR, errorResponse.error.message)
          this.showLoading = false;
        }
      )
    )
  }
  sendErrorNotification(notificationType: NotificationType, message: string) {
   if (message) {
    this.notificationService.notify(notificationType,message);
   }else{
    this.notificationService.notify(notificationType,'AN ERROR OCCURED. PLEASE TRY AGAIN')
   }

  }


  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }



}
