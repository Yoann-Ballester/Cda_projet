import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { AppSettings } from './../../settings/app.settings';

import { observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private host:String=AppSettings.APP_URL;
  private token:String;
  loggedInUsername:any;

  constructor(private http:HttpClient) {
    this.token='',
    this.loggedInUsername=''
   }
  public login(user:User) : observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(
      `${this.host}/user/login`, user, {observe:'response'}
    );
  }

  public register(user:User) : observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(
      `${this.host}/user/register`, user, {observe:'response'}
    );

    public logOut() : void {
      this.token='',
      this.loggedInUsername=''
      localStorage.removeItem('user');
      localStorage.removeItem('token');
      localStorage.removeItem('users');

    }

    public saveToken(token : String) : void {
      this.token= token
      localStorage.setItem('token',token);

    }

    public addUserToLocalCache(user : User) : void {

      localStorage.setItem('user',user);

    }

}
