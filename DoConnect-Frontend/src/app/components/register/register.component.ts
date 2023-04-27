import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule, FormBuilder, AbstractControl } from '@angular/forms';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css', '../login/login.component.css']
})
export class RegisterComponent implements OnInit {


  registerForm: FormGroup = new FormGroup({
    name: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
    email: new FormControl('')
  });
  submitted = false;

  constructor(private userService: UserService, private fb: FormBuilder, private authService: AuthService, private router: Router) {
  }
  ngOnInit(): void {
    this.registerForm = this.fb.group({
      name: ['', Validators.required],
      username: ['',
        [
          Validators.required,
          Validators.minLength(4),
          Validators.maxLength(20)
        ]
      ],
      email: ['',
        [
          Validators.required,
          Validators.email,
          Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')
        ]],
      password: ['',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(20)
        ]
      ]
    });

  }

  get f(): { [key: string]: AbstractControl } {
    return this.registerForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    } else {
      this.signUpUser()
    }

    console.log(JSON.stringify(this.registerForm.value, null, 2));
  }


  signUpUser() {

    const val = this.registerForm.value;

    console.log(val);

    // if (val.email && val.password) {
    //   this.authService.login(val.email, val.password)
    //     .subscribe(
    //       () => {
    //         console.log("User is logged in");
    //         this.router.navigateByUrl('/');
    //       }
    //     );

    this.userService.createUser(val).subscribe({
      next: (res) => {
        console.log(res);
        //navigate later
      }

    });


  }
}
