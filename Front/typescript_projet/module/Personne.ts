
import { IPersonne } from "./IPersonne";


export class Personne implements IPersonne{
    id:number;
    nom:string;
    prenom:string;
    adresse:string
    cp:string
    ville:string
   
    
  

    constructor (id:number,
        nom:string,
        prenom:string,
        adresse:string,
        cp:string,
        ville:string,
        
        ){

            this.nom=nom
            this.prenom=prenom
            this.adresse=adresse
            this.cp=cp
            this.id=id
            this.ville=ville
            
        }
       

}