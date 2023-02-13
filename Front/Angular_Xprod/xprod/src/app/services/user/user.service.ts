import { CustomHttpResponse } from './../../models/custom-http-response/custom-http-response';
import { Observable } from 'rxjs';
import { HttpClient, HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppSettings } from 'src/app/settings/app.settings';
import { User } from 'src/app/models/user/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private host:String=AppSettings.APP_URL;
  private loggedInUsername:string;
constructor(private http:HttpClient) {
  this.loggedInUsername="";
}

public getUsers() : Observable<User[] | HttpErrorResponse> {
  return this.http.get<User[]>(`${this.host}/user/list`);
}

public addUsers(formData:FormData): Observable<User | HttpErrorResponse>{
  return this.http.post<User>(`${this.host}/user/add`,formData)
}

public updateUsers(formData:FormData): Observable<User | HttpErrorResponse>{
  return this.http.post<User>(`${this.host}/user/update`,formData)
}

public resetPassword(email:string): Observable<any | HttpErrorResponse>{
  return this.http.get(`${this.host}/user/resetPassword/${email}`);
}

public deleteUsers(userid:number): Observable<CustomHttpResponse  | HttpErrorResponse>{
  return this.http.delete<CustomHttpResponse>(`${this.host}/user/delete/${userid}`);
}

public addUsersToLocalCache(users:User[]): void{
   localStorage.setItem('users', JSON.stringify(users));
}

public getUserFromLocalCache():User{
  return JSON.stringify(localStorage.getItem('user')) as any;

}

public createUserFormDate(loggedInUsername:string, user:User,profileImage:File):FormData{
const formData = new FormData();

formData.append('currentUsername',loggedInUsername);
formData.append('firstname',user.firstName);
formData.append('lastname',user.lastName);
formData.append('username',user.username);
formData.append('email',user.email);
formData.append('role',user.role);
formData.append('profileImage',profileImage);
formData.append('isActive',JSON.stringify(user.isActive));
formData.append('isNotLocked',JSON.stringify(user.isNotLocked));
return formData;
}


}
