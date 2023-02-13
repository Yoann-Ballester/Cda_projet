import { Produit } from '../../models/produit/produit';
import { AppSettings } from '../../settings/app.settings';
import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  httpOptions = {
    headers : new HttpHeaders({'content-type':'application/json'})
  }

  constructor(private http:HttpClient) { }

  findAllProduits(){
    return this.http.get(AppSettings.APP_URL+"/produits");
    //return this.http.get('http://localhost:8085/produits');
  }

  saveProduit(produit:Produit) {
    return this.http.post(AppSettings.APP_URL+"/produits",JSON.stringify(produit),this.httpOptions);
  }

  editProduit(id:number){
    return this.http.get(AppSettings.APP_URL+"/produits/"+id);
  }

  updateProduit(produit:Produit){
    return this.http.put(AppSettings.APP_URL+"/produits/"+produit.id,JSON.stringify(produit),this.httpOptions);
    // return this.http.put(AppSettings.APP_URL+"/produits",produit);
  }

  deleteProduit(id:number){
    return this.http.delete(AppSettings.APP_URL+"/produits/"+id);
  }
}
