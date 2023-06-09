import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule, FormBuilder, AbstractControl } from '@angular/forms';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {



  registerForm: FormGroup = new FormGroup({
    name: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
    email: new FormControl('')
  });
  submitted = false;

  constructor(private userService: UserService, 
    private fb: FormBuilder,
     private authService: AuthService,
      private router: Router,
      private storageService:StorageService) {
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

    this.isAdminAndLoggedIn();
  }

  isAdminAndLoggedIn(){
    if(!(this.storageService.isLoggedIn() && this.storageService.getUser().roles.includes('ROLE_ADMIN')) ){
      this.router.navigateByUrl('/home');
    }
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

    // console.log(JSON.stringify(this.registerForm.value, null, 2));
  }


  signUpUser() {
    const data = {
      name: this.registerForm.value.name,
      username: this.registerForm.value.username,
      password: this.registerForm.value.password,
      email: this.registerForm.value.email,
      role: ["admin"]
    }


    this.userService.createUser(data).subscribe({
      next: (res) => {
        this.router.navigateByUrl('/home');

      }

    });


  }
}
