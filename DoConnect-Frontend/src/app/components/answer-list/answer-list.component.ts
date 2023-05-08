import { Component } from '@angular/core';
import { Question } from 'src/app/models/question.model';
import { QuestionService } from 'src/app/services/question.service';
import { ActivatedRoute } from '@angular/router';
import { Answer } from 'src/app/models/answer.model';
import { AnswerService } from 'src/app/services/answer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-answer-list',
  templateUrl: './answer-list.component.html',
  styleUrls: ['./answer-list.component.css']
})
export class AnswerListComponent {
  answerList: Answer[] = [];

  question: Question = {
    id: 0,
    title: "",
    topic: "",
    image_src: "",
    description_question: ""
  };

  constructor(
    private questionService: QuestionService,
    private answerService: AnswerService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    this.questionService.getQuestionById(this.route.snapshot.paramMap.get('id')).subscribe({
      next: (data) => {
        this.question = data;
      }
    });

    this.getAnswer(this.route.snapshot.paramMap.get('id'));
  }

  getQuestionById(id: any) {
    this.questionService.getQuestionById(id).subscribe({
      next: (res) => {
        this.question = res;
      }
    });
  }

  getAnswer(id: any) {
    this.answerService.getAnswerbyQuestionID(id).subscribe({
      next: (res) => {
        console.log(res);
        this.answerList = res;
      }
    });
  }

  createAnswer(questionId: number) {
    this.router.navigateByUrl(`/create_answer/${questionId}`);
  }
  
}
