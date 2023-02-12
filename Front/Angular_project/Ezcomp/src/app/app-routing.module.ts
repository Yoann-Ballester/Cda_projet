import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProduitComponent } from './components/produit/produit.component';

const routes: Routes = [
  {
    path:'produit',
    component:ProduitComponent
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
