import { Component } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { QuestionService } from 'src/app/services/question.service';
import { Question } from '../../models/question.model'
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-create-question',
  templateUrl: './create-question.component.html',
  styleUrls: ['./create-question.component.css', '../login/login.component.css']
})
export class CreateQuestionComponent {

  // data:Question ={
  //   description_question: '',
  //   image_src: '',
  //   datetime: '',
  //   status: '',
  //   topic: '',
  //   title: '',
  //   qcreated_by: ''
  // }


  questionForm: FormGroup = new FormGroup({
    title: new FormControl(''),
    topic: new FormControl({ value: "Select a topic", disabled: true }),
    description: new FormControl(''),
    image: new FormControl(''),
    fileSource: new FormControl('')
  });

  created = false;

  constructor(private questionService: QuestionService,
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private storageService: StorageService) {
  }

  ngOnInit(): void {
    this.questionForm = this.fb.group({
      title: ['', Validators.required],
      topic: ['', Validators.required, Validators.min(1)],
      description: ['', Validators.required],
      image: ['', Validators.required],
      fileSource: ['', Validators.required]
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

  // onFileChange(event: any) {
  //   if (event.target.files.length > 0) {
  //     const file = event.target.files[0];
  //     this.questionForm.patchValue({
  //       fileSource: file
  //     });
  //   }
  // }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if(file.match(/image\/*/) == null){ 
      //validation
      //only image should be supported
      return;
    }

    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.questionForm.value.fileSource = reader.result as string;
    }; 
  }

  createQuestion() {

    const data = {
      title: this.questionForm.value.title,
      topic: this.questionForm.value.topic,
      image_src: this.questionForm.value.fileSource,
      description_question: this.questionForm.value.description,
      status: false,
      datetime: new Date(),
      qcreated_by: this.storageService.getUser().username
    }

    console.log(data);



    this.questionService.createQuestion(data).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigateByUrl('/home');
      }

    });



  }



}
