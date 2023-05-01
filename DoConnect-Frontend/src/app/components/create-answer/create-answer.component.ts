import { Component } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { AnswerService } from 'src/app/services/answer.service';
import { StorageService } from 'src/app/services/storage.service';
import { QuestionService } from 'src/app/services/question.service';
import { Question } from 'src/app/models/question.model';

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
  questionList: Question[] = [];

  constructor(private answerService: AnswerService, private fb: FormBuilder, private authService: AuthService, private router: Router, private storageService: StorageService, private questionService: QuestionService) {
  }

  ngOnInit(): void {
    this.answerForm = this.fb.group({
      description_answer: ['', Validators.required],
      image: ['', Validators.required]
    })

    this.getQuestion();
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
      //null for testing 
      // image_src: this.answerForm.value.fileSource,
      image_src: null,
      status: false,
      datetime: new Date().toLocaleString().split(',')[0],
      //hardcoded for testing 
      question: null,
      approved_by: null,
      created_by: this.storageService.getUser().username

    }

    this.answerService.createAnswer(data).subscribe({
      next: (res) => {
        console.log(res);
        //     //navigate later
        this.router.navigate(['/home']);
      }

    });

  }

  getQuestion() {

    this.questionService.getAllQuestion().subscribe({
      next: (res) => {
        this.questionList = res;
      }
    });
  }



}


