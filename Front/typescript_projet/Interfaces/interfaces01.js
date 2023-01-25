var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var CompteUtilisateur = /** @class */ (function () {
    // Un constructeur
    function CompteUtilisateur(nom, prenom, id) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }
    return CompteUtilisateur;
}());
var util = new CompteUtilisateur("Ballester", "Yoann", 1); // je passe par l'interface "Utilisateur" pour crée mon objet
console.log("".concat(util.nom, ", ").concat(util.prenom, ", ").concat(util.id));
var Personne = /** @class */ (function () {
    function Personne(id, nom, prenom, adresse, cp, ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.id = id;
        this.ville = ville;
    }
    // methode direBonjour()
    Personne.prototype.direbonjour = function () {
        console.log("Bonjour, mon nom est ".concat(sal.nom, " ").concat(sal.prenom));
    };
    return Personne;
}());
var Salarie = /** @class */ (function (_super) {
    __extends(Salarie, _super);
    function Salarie(id, nom, prenom, adresse, cp, ville, numSecu, dateDebut, anciennete, posteOccupe, classification, categorie) {
        var _this = _super.call(this, id, nom, prenom, adresse, cp, ville) || this;
        _this.numSecu = numSecu;
        _this.dateDebut = dateDebut;
        _this.anciennete = anciennete;
        _this.posteOccupe = posteOccupe;
        _this.classification = classification;
        _this.categorie = categorie;
        return _this;
    }
    return Salarie;
}(Personne));
var sal = new Salarie(1, "Ballester", "Yoann", "10 rue blabla", "77164", "Ferrieres", 12345, "10-02-23", "4", "Dev", "blabla", "cat3"); // je passe par l'interface "Utilisateur" pour crée mon objet
console.log("".concat(util.nom, ", ").concat(sal.prenom, ", ").concat(sal.id, ", ").concat(sal.adresse, ", ").concat(sal.cp, ", ").concat(sal.ville, " , ").concat(sal.numSecu, ", ").concat(sal.dateDebut, ", ").concat(sal.anciennete, ", ").concat(sal.posteOccupe, " , ").concat(sal.classification, " , ").concat(sal.categorie));
sal.direbonjour();
