export class Produit {

  public id: number;
  public refInterne:string;
  public designation:string;
  public descriptif:string;
  public prixUHT:number;

  constructor(){
    this.id = 0;
    this.refInterne='';
    this.designation='';
    this.descriptif='';
    this.prixUHT=0
  }
}
