import { ProduitService } from '../../services/produit/produit.service';

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.css']
})
export class ProduitComponent implements OnInit{

  declare produit : any[];
  declare form : FormGroup;

  constructor(
  private produitService: ProduitService,
  private router : Router,
  private formBuilder:FormBuilder,
  ){}

  ngOnInit(): void {

    this.form = this.formBuilder.group({
      id:['', Validators.required],
      ref:['', Validators.required],
      designation:['', Validators.required],
      descriptif:['', Validators.required],
      prixUHT:['', Validators.required],
      img:['', Validators.required],
    });

    this.getProduit();

  }

  getProduit() {

    this.produitService.findAllProduits().subscribe(
      data => {
        console.log(data);
        this.produit= data as any[];

      }
    );
  }

  create(){
    console.log(this.form.value);
    this.produitService.saveProduit(this.form.value).subscribe(
      ()=>{
        this.router.navigate(['/produits'])
      }
    );
  }

}
