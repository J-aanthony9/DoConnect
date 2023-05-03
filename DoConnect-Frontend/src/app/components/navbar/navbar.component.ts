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
  token: string | null = '';



  constructor(
    public storageService: StorageService,
    private authService: AuthService,
    private router: Router) {
  }

  ngOnInit(): void {
    console.log(this.storageService.getUser().token);


  }

  isAdmin(): boolean {
    if (this.storageService.isLoggedIn()) {
      return this.storageService.getUser().roles[0] == 'ROLE_ADMIN' ? true : false;
    } else {
      return false;
    }

  }

  isUser(): boolean {
    if(this.storageService.isLoggedIn()){
    return this.storageService.getUser().roles[0] == 'ROLE_USER' ? true : false;
    } else { return false;}
  }

  logOut() {
    this.router.navigateByUrl('/home');
    return this.storageService.logout();
  }




}
