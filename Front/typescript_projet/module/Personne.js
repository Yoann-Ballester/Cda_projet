"use strict";
exports.__esModule = true;
exports.Personne = void 0;
var Personne = /** @class */ (function () {
    function Personne(id, nom, prenom, adresse, cp, ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.id = id;
        this.ville = ville;
    }
    return Personne;
}());
exports.Personne = Personne;
