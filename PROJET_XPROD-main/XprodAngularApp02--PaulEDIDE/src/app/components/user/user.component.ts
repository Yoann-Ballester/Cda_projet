import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { CustomHttpResponse } from 'src/app/models/custom-http-response';
import { NotificationService } from 'src/app/services/notification/notification.service';
import { UserService } from './../../services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Subscription } from 'rxjs';
import { User } from 'src/app/models/user/user';
import { NotificationType } from 'src/app/enum/notification-type.enum';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Role } from 'src/app/enum/role.enum';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {



  private titleSubject = new BehaviorSubject<string>('Users');
  public titleAction$ = this.titleSubject.asObservable();
  declare public refreshing: boolean;
  declare public users: User[];
  private subscription:Subscription[]=[];
  declare public selectedUser: User|undefined;
  declare public fileName: string;
  declare public profileImage: File;
  public editUser = new User(); //: User | undefined ;
  declare public currentUsername:string;
  declare public user:User;



  constructor(private authenticationService:AuthenticationService,
    private userService:UserService,
     private notificationService:NotificationService,
     private router:Router) { }


  ngOnInit(): void {
    this.getUsers(true)
  }



  public changeTitle(title : string) : void {
    this.titleSubject.next(title);
  }

  public getUsers(showNotification: boolean):void{
    this.refreshing = true;
    this.subscription.push(
      this.userService.getUsers().subscribe(
        (response : User[])=>{
          this.userService.addUsersToLocalCache(response); // ajout des utilisateurs dans le cache local
          this.users = response;
          this.refreshing=false;
          if(showNotification){
            this.sendNotification(NotificationType.SUCCESS, `${response.length} user(s) loaded successfully.`)
          }
        },   (errorResponse: HttpErrorResponse) => {
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
           }
      )
    )
  }

  public OnSelectUser(selectedUser:User):void {
    this.selectedUser = selectedUser;
    document.getElementById('openUserInfo')?.click();
  }

  updateProfileImage(){
    throw new Error('method not implemented.')
  }

  onUpdateCurrentUser(e:NgForm){
    throw new Error('method not implemented.')
  }

  public onProfileImageChange(fileName : string , profileImage:File) :void {
    //console.log(profileImage,fileName);
    this.fileName = fileName;
    this.profileImage = profileImage;
  }



public onAddNewUser(userForm:NgForm):void {
 const formData = this.userService.createUserFormData(null as any,userForm.value,this.profileImage);
 this.subscription.push(
  this.userService.addUser(formData).subscribe(
    (response : User)=>{
      this.clickButton('new-user-close')
      this.getUsers(false);
      this.fileName = null as any;
      this.profileImage = null as any;
      userForm.reset();
      this.sendNotification(NotificationType.SUCCESS, `${response.firstname} ${response.lastname} updated successfully`)
    },
     (errorResponse: HttpErrorResponse) => {
      this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
      this.profileImage = null as any;
       }
  )
 );
}


  saveNewUser() :void{
    this.clickButton("new-user-save")

    }


    public searchUsers(searchTerm:string):void {
      //console.log(searchTerm);

      const results: User[]=[];
      for (const user of this.userService.getUsersFromLocalCache()){
          if(user.firstname.toLowerCase().indexOf(searchTerm.toLowerCase())!== -1 ||
              user.lastname.toLowerCase().indexOf(searchTerm.toLowerCase())!== -1 ||
              user.username.toLowerCase().indexOf(searchTerm.toLowerCase())!== -1 ||
              user.userId.toLowerCase().indexOf(searchTerm.toLowerCase())!== -1

        ){
          results.push(user);
        }
      }
          this.users = results;
          if(results.length === 0 || !searchTerm) {
          this.users = this.userService.getUsersFromLocalCache();

        }
       }



       public onEditUser(editUser: User) :void{
          this.editUser = editUser;
          this.currentUsername = editUser.username;
          this.clickButton('openUserEdit');
       }

       onLogout() {
        this.authenticationService.logOut();
        this.router.navigate(['/login'])
        this.sendNotification(NotificationType.SUCCESS, "You have been successfully logged out!");
       }

       public onResetPassword(emailForm:NgForm):void {
        this.refreshing = true;
       const emailAddress = emailForm.value['reset-password-email'];
        this.subscription.push(
          this.userService.resetPassword(emailAddress).subscribe(
            (response : CustomHttpResponse) => {
              this.sendNotification(NotificationType.SUCCESS, response.message);
              this.refreshing=false;
            },
            (errorResponse: HttpErrorResponse) => {
              this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
              this.refreshing=false;
               },
               ()=>emailForm.reset()
          )
        );
       }

       public onDeleteUser(uid: number):void {
        console.log(uid);

          this.subscription.push(
            this.userService.deleteUser(uid).subscribe(
              (response : CustomHttpResponse) => {
                this.sendNotification(NotificationType.SUCCESS, response.message);
                this.getUsers(true)
              },
              (errorResponse: HttpErrorResponse) => {
                this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
                this.profileImage = null as any;
                 }
            )
          );
       }

       public get isAdmin():boolean{
        return this.getUserRole() === Role.ADMIN ||this.getUserRole() === Role.SUPER_ADMIN
       }

       public get isManager():boolean {
        return this.isAdmin || this.getUserRole() === Role.MANAGER;
       }

       public get isAdminOrManager() :boolean{
        return this.isAdmin || this.isManager;
       }

       getUserRole() :string{
       return this.authenticationService.getUserFromLocalCache().role;
      }

      public onUpdateUser() :void{

        const formData = this.userService.createUserFormData(this.currentUsername,this.editUser,this.profileImage);
        this.subscription.push(
         this.userService.updateUser(formData).subscribe(
           (response : User)=>{
             this.clickButton('closeEditUserModalBtn')
             this.getUsers(false);
             this.fileName = null as any;
             this.profileImage = null as any;

             this.sendNotification(NotificationType.SUCCESS, `${response.firstname} ${response.lastname} updated successfully`)
           },
            (errorResponse: HttpErrorResponse) => {
             this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
             this.profileImage = null as any;
              }
         )
        );
      }

  private sendNotification(notificationType: NotificationType, message: string) {
    if(message){
      this.notificationService.notify(notificationType, message);
    } else {
      this.notificationService.notify(notificationType, 'Une erreur est survenue. Veuillez réessayer.');
    }
  }

  private clickButton(buttonId:string):void {
    document.getElementById(buttonId)?.click();
  }



}
