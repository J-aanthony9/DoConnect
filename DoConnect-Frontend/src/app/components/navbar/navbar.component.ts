import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  // data:User = {
  //   id:0,
  //   username:'',
  //   usertype:''
  // }
  

  constructor(
    public storageService: StorageService,
    private authService: AuthService,
    private router:Router) { }

  ngOnInit(): void {

  }

  isAdmin():boolean {
    return this.storageService.getUser().usertype == 'admin' ? true : false;
  }

  isUser():boolean {
    return this.storageService.getUser().usertype == 'user' ? true : false;
  }

  logOut(){
    this.router.navigateByUrl('/home');
    return this.storageService.logout();
  }




}
