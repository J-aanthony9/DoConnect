import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/models/question.model';
import { QuestionService } from 'src/app/services/question.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-pending-question',
  templateUrl: './pending-question.component.html',
  styleUrls: ['./pending-question.component.css']
})
export class PendingQuestionComponent implements OnInit {
  questions: Question[] = [];
  question: Question = {};




  constructor(private questionService: QuestionService,
    private route: Router,
    private storageService: StorageService) { }

  ngOnInit(): void {
    this.getAllQuestions();
  }


  getAllQuestions() {
    this.questionService.getAllQuestionFalse().subscribe({
      next: (res) => {
        this.questions = res;
        console.log(res);

      }
    })
  }

  approveQuestion(data: any, id: any) {
    this.question = data;
    this.question.status = "true";
    this.question.qapproved_by = this.storageService.getUser().username;
    this.questionService.updateQuestion(data, id).subscribe({
      next: (res) => {
        this.getAllQuestions();
        this.route.navigateByUrl('/pending_question');
      }
    })

  }

  removeQuestion(id: any) {
    this.questionService.deleteQuestion(id).subscribe({
      next: (res) => {
        this.getAllQuestions();
        this.route.navigateByUrl('/pending_question');
      }
    })


  }

}
