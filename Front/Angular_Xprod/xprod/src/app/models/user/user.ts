export class User {

  public id:number;// id for database
  public userId:string; // id user
  public firstName:string;
  public lastName:string;
  public username:string;
  public password:string;
  public email:string;
  public profileImageUrl:string;
  public lastLoginDate: Date;
  public lastLoginDateDisplay: Date;
  public joinDate: Date;
  public role: string;
  public authorities: [];
  public isActive: boolean;
  public isNotLocked: boolean;


  constructor(){
    this.id=0;
    this.userId="";
    this.firstName="";
    this.lastName="";
    this.username="";
    this.password="";
    this.email="";
    this.profileImageUrl="";
    this.lastLoginDate= new Date();
    this.lastLoginDateDisplay= new Date();
    this.joinDate= new Date();
    this.role= "";
    this.authorities= [];
    this.isActive= true;
    this.isNotLocked= true;
  }
}
