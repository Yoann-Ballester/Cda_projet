"use strict";
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
exports.__esModule = true;
exports.Salarie = void 0;
var Personne_1 = require("./Personne");
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
}(Personne_1.Personne));
exports.Salarie = Salarie;
