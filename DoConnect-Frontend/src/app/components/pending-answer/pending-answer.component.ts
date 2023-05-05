import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Answer } from 'src/app/models/answer.model';
import { AnswerService } from 'src/app/services/answer.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-pending-answer',
  templateUrl: './pending-answer.component.html',
  styleUrls: ['./pending-answer.component.css', '../pending-question/pending-question.component.css']
})
export class PendingAnswerComponent implements OnInit {
  answers: Answer[] = [];
  answer: Answer = {};
  constructor(private answerService: AnswerService,
    private route: Router,
    private storageService: StorageService) { }

  ngOnInit(): void {
    this.getAllAnswer();
    this.isAdminAndLoggedIn();


  }

  isAnswersEmpty(): Boolean {
    return this.answers.length == 0 ? true : false;

  }


  isAdminAndLoggedIn() {
    if (!(this.storageService.isLoggedIn() && this.storageService.getUser().roles.includes('ROLE_ADMIN'))) {
      this.route.navigateByUrl('/home');
    }
  }


  getAllAnswer() {
    this.answerService.getAllAnswerFalse().subscribe({
      next: (res) => {
        this.answers = res;
      }
    });
  }

  approveAnswer(data: any, id: any) {
    this.answer = data;
    this.answer.status = "true";
    this.answer.approved_by = ""
    this.answer.approved_by = this.storageService.getUser().username;
    this.answerService.updateAnswer(id, data).subscribe({
      next: (res) => {
        this.getAllAnswer();
        this.route.navigateByUrl('/pending_answer');
      }
    });
  }

  removeAnswer(id: any) {
    this.answerService.deleteAnswer(id).subscribe({
      next: (res) => {
        this.getAllAnswer();
        this.route.navigateByUrl('/pending_answer');
      }
    })
  }

}
