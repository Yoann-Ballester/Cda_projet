"use strict";
exports.__esModule = true;
var Salarie_1 = require("./Salarie");
var sal = new Salarie_1.Salarie(1, "Ballester", "Yoann", "10 rue blabla", "77164", "Ferrieres", 12345, "10-02-23", "4", "Dev", "blabla", "cat3"); // je passe par l'interface "Utilisateur" pour crée mon objet
console.log("".concat(sal.nom, ", ").concat(sal.prenom, ", ").concat(sal.id, ", ").concat(sal.adresse, ", ").concat(sal.cp, ", ").concat(sal.ville, " , ").concat(sal.numSecu, ", ").concat(sal.dateDebut, ", ").concat(sal.anciennete, ", ").concat(sal.posteOccupe, " , ").concat(sal.classification, " , ").concat(sal.categorie));
