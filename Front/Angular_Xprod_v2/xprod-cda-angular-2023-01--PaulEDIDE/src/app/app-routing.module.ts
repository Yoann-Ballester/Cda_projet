import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditProduitComponent } from './components/edit-produit/edit-produit.component';
import { ListeProduitComponent } from './components/liste-produit/liste-produit.component';
import { LoginComponent } from './components/login/login.component';
import { ProduitComponent } from './components/produit/produit.component';
import { RegisterComponent } from './components/register/register.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  {
    path:'login', component:LoginComponent
  },
  {
    path:'register', component:RegisterComponent
  },
  {
    path:'user/management', component:UserComponent
  },
  {
    path:'produit',
    component:ProduitComponent
  },
  {
    path: 'produit/:id',
    component:ListeProduitComponent
  },
  {
    path:'edit/:id',
    component:EditProduitComponent
  },
  {
    path:'', redirectTo:'login', pathMatch:'full'
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
