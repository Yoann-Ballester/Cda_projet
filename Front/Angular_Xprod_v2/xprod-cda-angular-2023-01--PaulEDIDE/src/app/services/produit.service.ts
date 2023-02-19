import { Produit } from './../models/produit/produit';
import { AppSettings } from './../settings/app.settings';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {
  httpOptions= {
    headers : new HttpHeaders({'Content-Type':'application/json'})
  }

  constructor(private http:HttpClient){}

  findAllProduits(){
    return this.http.get(AppSettings.APP_URL+"/produits");
  }

  saveProduit(produit:Produit){
    return this.http.post("http://localhost:8085/produits",JSON.stringify(produit),this.httpOptions);
  }

  editProduit(id:number){ 
    return this.http.get(AppSettings.APP_URL+"/produits/"+id);
  }
  updateProduit(produit:Produit){
    //return this.http.put(AppSettings.APP_URL+"/produits/"+produit.id,JSON.stringify(produit),this.httpOptions);
    return this.http.put("http://localhost:8085/produits/"+produit.id,produit);
  }
  deleteProduit(id:number){
    return this.http.delete(AppSettings.APP_URL+"/produits/"+id);
  }

}
