import { ActivatedRoute, Router } from '@angular/router';
import { ProduitService } from './../services/produit.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-produit',
  templateUrl: './edit-produit.component.html',
  styleUrls: ['./edit-produit.component.css']
})
export class EditProduitComponent implements OnInit{

  declare editform : FormGroup;

  constructor(
    private produitService:ProduitService,
    private router:Router,
    private route : ActivatedRoute,
    private formBuilder : FormBuilder,
  ){}

  ngOnInit(): void {
    this.editform = this.formBuilder.group({
      //id:['', Validators.required],         // l'id est commente car il est regle sur auto increment
      ref:['', Validators.required],
      designation:['', Validators.required],
      descriptif:['', Validators.required],
      prixUHT:['', Validators.required],
      img:['', Validators.required],
  });
  // on cree une constante à laquelle on donne l'url correspondant à l'id
  const id = Number(this.route.snapshot.paramMap.get('id'));
  // on va chercher la methode edit dans les services pour l'utiliser sur l'id
    this.produitService.editProduit(id).subscribe(
      data => {
        // insertion des nouvelles data dans editform
        this.editform.setValue(data);
      }
    );
  }

  update(){
    console.log(this.editform.value);
    if(this.editform.valid){
      this.produitService.updateProduit(this.editform.value).subscribe(
        ()=>{
          this.router.navigate(['/produit']);
        }
      );
    }

  }

}
