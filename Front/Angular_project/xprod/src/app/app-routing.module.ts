import { EditProduitComponent } from './edit-produit/edit-produit.component';
import { ListeProduitComponent } from './components/liste-produit/liste-produit.component';
import { AddProduitComponent } from './components/add-produit/add-produit.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProduitComponent } from './components/produit/produit.component';


const routes: Routes = [
  {
    path:'produit',
    component:ProduitComponent


  }
,
  {
    path:'produit/:id',
    component:ListeProduitComponent
  }
  ,
  {
    path:'edit/:id',
    component:EditProduitComponent
  },

  {
    path:'', redirectTo:'produit', pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
