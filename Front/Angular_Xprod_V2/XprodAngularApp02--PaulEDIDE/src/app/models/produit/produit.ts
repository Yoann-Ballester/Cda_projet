export class Produit {
     public id: number;
     public reference: string;
     public designation: string;
     public descriptif: string;
     public prixVente: number;
     public image : string;
     constructor(){
         this.id=0;
         this.reference='';
         this.designation='';
         this.descriptif='';
         this.prixVente=0;
         this.image='';
     }

}
