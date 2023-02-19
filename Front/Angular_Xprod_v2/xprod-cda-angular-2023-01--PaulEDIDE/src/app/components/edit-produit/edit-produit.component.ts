import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { ProduitService } from 'src/app/services/produit.service';

@Component({
  selector: 'app-edit-produit',
  templateUrl: './edit-produit.component.html',
  styleUrls: ['./edit-produit.component.css']
})
export class EditProduitComponent implements OnInit{
  declare editForm : FormGroup;

  constructor(
    private produitService : ProduitService,
    private router : Router,
    private route : ActivatedRoute,
    private formBuilder : FormBuilder,
  ){}
  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id:['',Validators.required],  //Si id en auto-increment
      refInterne:['',Validators.required],
      designation:['',Validators.required],
      descriptif:['',Validators.required],
      prixUHT:['',Validators.required],
    });
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.produitService.editProduit(id).subscribe(
      data =>{
        this.editForm.setValue(data);
            
      }
    );
    
  }
  update(){
    console.log(this.editForm.value);
    if(this.editForm.valid){
      this.produitService.updateProduit(this.editForm.value).subscribe(
        () =>{
          this.router.navigate(['/produit']);
        }
      );

    }
    
  }

}
