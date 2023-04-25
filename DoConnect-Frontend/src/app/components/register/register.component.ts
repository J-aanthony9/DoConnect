import { Component } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css','../login/login.component.css']
})
export class RegisterComponent {
  user: User={
    name:'',
    username:'',
    password:'',
    email:'',
    usertype:''
  }

  constructor(private userService: UserService){}

  signUpUser(){
    const data = {
      name:this.user.name,
      username: this.user.username,
      password:this.user.password,
      email: this.user.email,
      usertype:"admin"
    };
    this.userService.createUser(data).subscribe({
      next: (res) => {
        console.log(res);
        //navigate later
      }
     
    });
  }

}
