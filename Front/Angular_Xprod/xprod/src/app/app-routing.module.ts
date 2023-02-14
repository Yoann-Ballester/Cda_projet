import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { EditProduitComponent } from './edit-produit/edit-produit.component';
import { ListeProduitComponent } from './components/liste-produit/liste-produit.component';
import { AddProduitComponent } from './components/add-produit/add-produit.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProduitComponent } from './components/produit/produit.component';
import { UserComponent } from './components/user/user.component';


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
    path:'addproduit',
    component:AddProduitComponent
  },

  {
    path:'login', component:LoginComponent
  },

  {
    path:'user/manager', component:UserComponent
  },

  {
    path:'register', component:RegisterComponent
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
