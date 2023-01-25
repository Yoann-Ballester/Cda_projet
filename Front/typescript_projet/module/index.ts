import { Personne } from "./Personne";
import { Salarie } from "./Salarie";

const sal : Salarie = new Salarie(1,"Ballester","Yoann","10 rue blabla","77164","Ferrieres",12345,"10-02-23","4","Dev", "blabla","cat3") // je passe par l'interface "Utilisateur" pour crée mon objet
console.log(`${sal.nom}, ${sal.prenom}, ${sal.id}, ${sal.adresse}, ${sal.cp}, ${sal.ville} , ${sal.numSecu}, ${sal.dateDebut}, ${sal.anciennete}, ${sal.posteOccupe } , ${sal.classification} , ${sal.categorie}`);

