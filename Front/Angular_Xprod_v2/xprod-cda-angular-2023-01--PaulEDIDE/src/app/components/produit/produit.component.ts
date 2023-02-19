import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProduitService } from 'src/app/services/produit.service';

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.css']
})
export class ProduitComponent implements OnInit{
  
  declare produits : any[];
  declare form : FormGroup; 

  constructor(
    private produitService : ProduitService,
    private router : Router,
    private formBuilder: FormBuilder,
  ){

  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      id:['',Validators.required],
     refInterne:['',Validators.required],
      designation:['',Validators.required],
      descriptif:['',Validators.required],
      prixUHT:['',Validators.required],
    });
    this.getProduits();
  }
  getProduits() {
 
    this.produitService.findAllProduits().subscribe(
      data  =>{
        console.log(data);
        this.produits = data as any[];
        
      }
    );
  }
 

}
