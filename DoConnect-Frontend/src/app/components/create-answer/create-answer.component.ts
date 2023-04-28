import { Component } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { AnswerService } from 'src/app/services/answer.service';

@Component({
  selector: 'app-create-answer',
  templateUrl: './create-answer.component.html',
  styleUrls: ['./create-answer.component.css']
})
export class CreateAnswerComponent {

  answerForm: FormGroup = new FormGroup({
    description_answer: new FormControl(''),
    image: new FormControl('')
  });

  submitted = false;

  constructor(private answerService: AnswerService, private fb: FormBuilder, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.answerForm = this.fb.group({
      description_answer: ['', Validators.required],
      image: ['', Validators.required]
    })
  }

  get f(): { [key: string]: AbstractControl } {
    return this.answerForm.controls;
  }

  onSubmit(): void {

    this.submitted = true;

    if (this.answerForm.invalid) {
      return;
    } else {
      this.answerQuestion()
    }
  }

  answerQuestion() {
    const data = {

      description_answer: this.answerForm.value.description_answer,
      image_src: this.answerForm.value.image,
      status: false,
      datetime: new Date(),

    }

    // this.answerService.createAnswer(data).subscribe({
    //   next: (res) => {
    //     console.log(res);
    //     //navigate later
    //   }

    // });



  }

}
