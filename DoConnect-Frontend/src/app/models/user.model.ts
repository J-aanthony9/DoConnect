export class User{
    id?:number;
    name?:string;
    username?:string;
    password?:string;
    email?:string;
    usertype?:string;

    constructor(id:number, username:string, usertype:string){
        this.id = id;
        this.username = username;
        this.usertype = usertype;
    }
}