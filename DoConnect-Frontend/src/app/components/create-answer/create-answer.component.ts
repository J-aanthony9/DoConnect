import { Component } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { AnswerService } from 'src/app/services/answer.service';
import { StorageService } from 'src/app/services/storage.service';
import { QuestionService } from 'src/app/services/question.service';
import { Question } from 'src/app/models/question.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-create-answer',
  templateUrl: './create-answer.component.html',
  styleUrls: ['./create-answer.component.css']
})
export class CreateAnswerComponent {



  answerForm: FormGroup = new FormGroup({
    description_answer: new FormControl(''),
    fileSource: new FormControl('')
  });


  submitted = false;

  question: Question = {
    id: 0,
    title: "",
    topic: "",
    image_src: "",
    description_question: ""
  }

  answerId: number = 0;




  constructor(private answerService: AnswerService,
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private storageService: StorageService,
    private questionService: QuestionService,
    private route: ActivatedRoute, private datepipe: DatePipe) {
  }

  ngOnInit(): void {
    this.answerForm = this.fb.group({
      description_answer: ['', Validators.required],


    })

    this.questionService.getQuestionById(this.route.snapshot.paramMap.get('id')).subscribe({
      next: (data) => {
        this.question = data;
      }
    })
    this.isUserAndLoggedIn();
  }
  isUserAndLoggedIn(){
    if(!(this.storageService.isLoggedIn() && this.storageService.getUser().roles.includes('ROLE_USER')) ){
      this.router.navigateByUrl('/home');
    }
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

  onFileSelected(event: any) {
    const file = event.target.files[0];

    //Validation method for images only
    // if(file.match(/image\/*/) == null){ 
    //   return;
    // }

    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.answerForm.value.fileSource = reader.result as string;
    };
  }


  answerQuestion() {
    const data = {

      description_answer: this.answerForm.value.description_answer,
      //null for testing 
      image_src: this.answerForm.value.fileSource,
      // image_src: null,
      status: false,
      datetime: this.datepipe.transform((new Date), 'MM/dd/yyyy h:mm:ss'),
      //hardcoded for testing 
      question: null,
      approved_by: null,
      created_by: this.storageService.getUser().username

    }


    this.answerService.assignAnswerToQuestion(this.question.id, data).subscribe({
      next: (res) => {
        this.router.navigateByUrl('/home');

      }
    });
  }

  getQuestionById(id: any) {
    this.questionService.getQuestionById(id).subscribe({
      next: (res) => {
        this.question = res;
      }
    });
  }



}


