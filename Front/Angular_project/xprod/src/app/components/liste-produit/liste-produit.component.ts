import { Produit } from './../../models/produit/produit';
import { ActivatedRoute, Router } from '@angular/router';
import { ProduitService } from './../../services/produit.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-liste-produit',
  templateUrl: './liste-produit.component.html',
  styleUrls: ['./liste-produit.component.css']
})
export class ListeProduitComponent implements OnInit{

declare produit:any;

  constructor(
    private produitService:ProduitService,
    private router:Router,
    private route:ActivatedRoute,
  ){}


  ngOnInit(): void {
    this.produitService.findAllProduits().subscribe(
      data => {
        console.log(data);
        this.produit = data;
      }
    );


    if  (this.route.snapshot.paramMap.get('id') != null){
      this.remove();
    }
  }

  remove() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.produitService.deleteProduit(id).subscribe(
      ()=>{
        this.router.navigate(['/produit']);
      }
    );

  }

}
