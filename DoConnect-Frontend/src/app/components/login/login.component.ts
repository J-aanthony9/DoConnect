import { Component } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { Validators } from '@angular/forms';
import { AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm: FormGroup = new FormGroup({

    username: new FormControl(''),
    password: new FormControl(''),

  });
  submitted = false;

  constructor(private userService: UserService, private fb: FormBuilder, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
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
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    } else {

      const val = this.loginForm.value;


      this.authService.login(val)
        .subscribe(
          () => {
            console.log("User is logged in");
            //this.router.navigateByUrl('/');
          }
        );

    }
    console.log(JSON.stringify(this.loginForm.value, null, 2));
  }
}
