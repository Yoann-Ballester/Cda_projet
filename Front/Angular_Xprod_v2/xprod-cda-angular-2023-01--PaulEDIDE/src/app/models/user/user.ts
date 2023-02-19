export class User {
public id :number ; //id for the DB
public userId :string ; //Id of the user
public firstname:string ;
public lastname:string;
public username: string;
public password: string;
public email: string;
public profileImageUrl: string;
public lastLoginDate: Date;
public lastLoginDateDisplay: Date;
public joinDate: Date;
public role: string;
public authorities: [];
public isActive: boolean;
public isNotLocked: boolean;

constructor(){
    this.id=0;
    this. userId ='';
    this.firstname='';
    this.lastname='';
    this.username= "";
    this.password= "";
    this.email= "";
    this.profileImageUrl= "";
    this.lastLoginDate= new Date();
    this.lastLoginDateDisplay= new Date();
    this.joinDate= new Date();
    this.role= "";
    this.authorities= [];
    this.isActive= true;
    this.isNotLocked= true;

}

}