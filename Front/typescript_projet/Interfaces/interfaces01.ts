interface Utilisateur {
    // definition de mon contrat
    // les champs nom, prenom, id sont obligatoires
    // par defaut les proprietes ont une portée publique

    nom : string
    prenom: string
    id : number
}

class CompteUtilisateur {
    nom:string;
    prenom:string;
    id:number;

    // Un constructeur
    constructor (nom:string,
    prenom:string,
    id:number){
        this.nom = nom;
        this.prenom = prenom;
        this.id=id;
    }

}

const util : Utilisateur = new CompteUtilisateur("Ballester","Yoann",1) // je passe par l'interface "Utilisateur" pour crée mon objet
console.log(`${util.nom}, ${util.prenom}, ${util.id}`);




interface IPersonne {
    id:number;
    nom:string;
    prenom:string;
    adresse:string
    cp:string
    ville:string
    
       
}

class Personne implements IPersonne{
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
        // methode direBonjour()
        direbonjour(){
            console.log(`Bonjour, mon nom est ${sal.nom} ${sal.prenom}`);
            
        }

}

interface ISalarie {
    numSecu : number
    dateDebut: string
    anciennete: string
    posteOccupe: string
    classification: string
    categorie: string
}

class Salarie extends Personne implements ISalarie{
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

const sal : Salarie = new Salarie(1,"Ballester","Yoann","10 rue blabla","77164","Ferrieres",12345,"10-02-23","4","Dev", "blabla","cat3") // je passe par l'interface "Utilisateur" pour crée mon objet
console.log(`${sal.nom}, ${sal.prenom}, ${sal.id}, ${sal.adresse}, ${sal.cp}, ${sal.ville} , ${sal.numSecu}, ${sal.dateDebut}, ${sal.anciennete}, ${sal.posteOccupe } , ${sal.classification} , ${sal.categorie}`);

sal.direbonjour()