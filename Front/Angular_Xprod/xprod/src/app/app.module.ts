
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ProduitComponent } from './components/produit/produit.component';
import { AddProduitComponent } from './components/add-produit/add-produit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListeProduitComponent } from './components/liste-produit/liste-produit.component';
import { EditProduitComponent } from './edit-produit/edit-produit.component';
import { UserComponent } from './models/user/user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    ProduitComponent,
    AddProduitComponent,
    ListeProduitComponent,
    EditProduitComponent,
    UserComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,


  ],

  providers: [],
  bootstrap: [AppComponent]

})
export class AppModule { }
