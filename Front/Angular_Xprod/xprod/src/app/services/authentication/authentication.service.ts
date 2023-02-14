import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { AppSettings } from './../../settings/app.settings';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public host:string=AppSettings.APP_URL;
  private token:string;
  loggedInUsername:any;
  private jwtHelper = new JwtHelperService();

  constructor(private http:HttpClient) {
    this.token='',
    this.loggedInUsername=''
   }

  public login(user:User) :  Observable<HttpResponse<User> | HttpErrorResponse> {
    return this.http.post<User>(
      `${this.host}/user/login`, user, {observe:'response'}
    );
  }

  public register(user:User) : Observable<HttpResponse<User> | HttpErrorResponse> {
    return this.http.post<User>(
      `${this.host}/user/register`, user, {observe:'response'}
    );
  }

    public logOut() : void {
      this.token='',
      this.loggedInUsername=''
      localStorage.removeItem('user');
      localStorage.removeItem('token');
      localStorage.removeItem('users');

    }

    public saveToken(token : string) : void {
      this.token= token
      localStorage.setItem('token',token);

    }

    public addUserToLocalCache(user : User) : void {

      localStorage.setItem('user',JSON.stringify(user));

    }

     /* Cherche l'utilisateur dans le cache local */
  public getUserFromLocalCache():User{
    return JSON.stringify(localStorage.getItem('user')) as any;
    //return localStorage.getItem('user') as User;
  }

  /* Charge le token du cache local */
  public loadToken():void{
    this.token = localStorage.getItem('token') as string;
  }

  /* Récupère le token du cache local */
  public getToken():string{
    return this.token;
  }

  /* Vérifie si un utilisateur est connecté */
  public isLoggedIn():boolean{
    this.loadToken();
    if(this.token!=null && this.token!=='') {
      // On décode le token
      if(this.jwtHelper.decodeToken(this.token).sub!=null||''){
        if(!this.jwtHelper.isTokenExpired(this.token)){
          this.loggedInUsername=this.jwtHelper.decodeToken(this.token).sub;
          return true;
        }
      }
    }
    else {
      this.logOut();
      return false;
    }
    return false;
  }

}
