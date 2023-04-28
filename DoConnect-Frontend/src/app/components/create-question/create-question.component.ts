import { Component } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { QuestionService } from 'src/app/services/question.service';

@Component({
  selector: 'app-create-question',
  templateUrl: './create-question.component.html',
  styleUrls: ['./create-question.component.css', '../login/login.component.css']
})
export class CreateQuestionComponent {

  questionForm: FormGroup = new FormGroup({
    title: new FormControl(''),
    topic: new FormControl(''),
    description: new FormControl(''),
    image: new FormControl('')
  });

  created = false;

  constructor(private questionService: QuestionService, private fb: FormBuilder, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.questionForm = this.fb.group({
      title: ['', Validators.required],
      topic: ['', Validators.required, Validators.min(1)],
      description: ['', Validators.required],
      image: ['', Validators.required]
    });
  }


  get f(): { [key: string]: AbstractControl } {
    return this.questionForm.controls;
  }

  onSubmit(): void {

    this.created = true;

    if (this.questionForm.invalid) {
      return;
    } else {
      this.createQuestion()
    }
  }


  createQuestion() {
    const data = {
      title: this.questionForm.value.title,
      topic: this.questionForm.value.topic,
      image_src: this.questionForm.value.image,
      description_question: this.questionForm.value.description,
      status: false,
      datetime: new Date(),

    }

    // this.questionService.createQuestion(data).subscribe({
    //   next: (res) => {
    //     console.log(res);
    //     //navigate later
    //   }

    // });



  }



}
