import { Personne } from "./Personne";
import { ISalarie } from "./ISalarie";


export class Salarie extends Personne implements ISalarie{
    numSecu : number
    dateDebut: string
    anciennete: string
    posteOccupe: string
    classification: string
    categorie: string

    constructor (id:number,
        nom:string,
        prenom:string,
        adresse:string,
        cp:string,
        ville:string,
        numSecu : number,
        dateDebut: string,
        anciennete: string,
        posteOccupe: string,
        classification: string,
        categorie: string   ){

        super(id,nom,prenom,adresse, cp,ville)

        this.numSecu=numSecu
        this.dateDebut=dateDebut
        this.anciennete=anciennete
        this.posteOccupe=posteOccupe
        this.classification=classification
        this.categorie=categorie
    }

}