import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProduitService } from 'src/app/services/produit.service';

@Component({
  selector: 'app-add-produit',
  templateUrl: './add-produit.component.html',
  styleUrls: ['./add-produit.component.css']
})
export class AddProduitComponent implements OnInit {
  declare form : FormGroup;
  
  constructor(
    private produitService : ProduitService,    
    private formBuilder: FormBuilder,
    private router : Router,
  ){} 
  ngOnInit(): void {
    this.form = this.formBuilder.group({
      id:['',Validators.required],  //Si id en auto-increment
      refInterne:['',Validators.required],
      designation:['',Validators.required],
      descriptif:['',Validators.required],
      prixUHT:['',Validators.required],
    });
  }

  create(){
    console.log(this.form.value);
    this.produitService.saveProduit(this.form.value).subscribe(
      ()  =>{
        this.router.navigate(['/produit']);
      }
    );
  }
}
