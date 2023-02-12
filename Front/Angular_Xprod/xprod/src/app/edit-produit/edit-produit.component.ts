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

  declare editForm : FormGroup;

  constructor(
    private produitService:ProduitService,
    private router:Router,
    private route : ActivatedRoute,
    private formBuilder : FormBuilder,
  ){}

  ngOnInit(): void {

      this.editForm = this.formBuilder.group({
      id:['', Validators.required],
      ref:['', Validators.required],
      designation:['', Validators.required],
      descriptif:['', Validators.required],
      prixUHT:['', Validators.required],
      img:['', Validators.required],
      //reference:['', Validators.required],
     });

     if(this.route.snapshot.paramMap.get('id')!= null) {
      return this.edit();
     }

  }

  edit(){
    // on cree une constante à laquelle on donne l'url correspondant à l'id
  const id = Number(this.route.snapshot.paramMap.get('id'));
  // on va chercher la methode edit dans les services pour l'utiliser sur l'id
    this.produitService.editProduit(id).subscribe(
      data => {

        // insertion des nouvelles data dans editform
        this.editForm.setValue(data);
      }
    );
  }

  update(){
    console.log(this.editForm.value);
    if(this.editForm.valid){
      this.produitService.updateProduit(this.editForm.value).subscribe(
        ()=>{
          this.router.navigate(['/produit']);
        }
      );
    }

  }

}
