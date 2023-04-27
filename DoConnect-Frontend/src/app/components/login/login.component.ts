import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { Validators } from '@angular/forms';
import { AbstractControl } from '@angular/forms';
import { StorageServiceService } from 'src/app/storage-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({

    username: new FormControl(''),
    password: new FormControl(''),

  });
  isLoggedIn = false;
  isLoginFailed = false;

  constructor(private userService: UserService, private fb: FormBuilder, private authService: AuthService, private router: Router, private storageService: StorageServiceService) {
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
    }
    this.loginForm = this.fb.group({

      username: ['',
        [
          Validators.required,

        ]
      ],
      password: ['',
        [
          Validators.required,

        ]
      ]
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.loginForm.controls;
  }

  login() {
    const val = this.loginForm.value;
    console.log(val);


    this.authService.login(val)
      .subscribe({
        next: data => {
          console.log('login res data ===', data);
          this.storageService.saveUser(data)
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          // this.reloadPage();
        },
        error: err => {
          console.log('login err ===', err);
          this.isLoginFailed = true;
        }
      }

      );

  }

  reloadPage(): void {
    window.location.reload();
  }
}
