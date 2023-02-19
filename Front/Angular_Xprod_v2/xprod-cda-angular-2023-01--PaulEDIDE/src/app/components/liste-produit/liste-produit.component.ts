import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProduitService } from 'src/app/services/produit.service';

@Component({
  selector: 'app-liste-produit',
  templateUrl: './liste-produit.component.html',
  styleUrls: ['./liste-produit.component.css']
})
export class ListeProduitComponent implements OnInit{
  declare produits:any;
  constructor(
    private produitService : ProduitService,
    private router : Router,
    private route : ActivatedRoute,
  ){}
  ngOnInit(): void {
  this.produitService.findAllProduits().subscribe(
    data => {
      console.log(data);
      this.produits = data;
    }
  );
  if(this.route.snapshot.paramMap.get('id')){
    this.remove();}
  }
  remove() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.produitService.deleteProduit(id).subscribe(
      () =>{
            this.router.navigate(['/produit']);
      }
    );
  }

}
