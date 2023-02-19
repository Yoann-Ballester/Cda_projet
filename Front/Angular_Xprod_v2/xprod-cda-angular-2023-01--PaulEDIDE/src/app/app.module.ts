import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProduitComponent } from './components/produit/produit.component';
import { AddProduitComponent } from './components/add-produit/add-produit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListeProduitComponent } from './components/liste-produit/liste-produit.component';
import { EditProduitComponent } from './components/edit-produit/edit-produit.component';
import { AuthenticationService } from './services/authentication/authentication.service';
import { UserService } from './services/user/user.service';

import { AuthenticationGuard } from './guard/authentication.guard';
import {NotificationModule } from './notification.module';
import { NotificationService } from './services/notification/notification.service';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserComponent } from './components/user/user.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    ProduitComponent,
    AddProduitComponent,
    ListeProduitComponent,
    EditProduitComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NotificationModule,
  ],
  providers: [NotificationService,
      AuthenticationGuard, 
      AuthenticationService,UserService,
     {provide : HTTP_INTERCEPTORS,useClass:AuthInterceptor, multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
