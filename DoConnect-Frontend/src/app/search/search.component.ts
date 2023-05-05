import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../services/question.service';
import { Question } from '../models/question.model';
import { Router } from '@angular/router';
import { topicArr } from 'src/assets/topics';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  questionList: Question[] = [];
  searchQuestionList: Question[] = [];
  filteredQuestions: Question[] = [];
  searchInput: string = "";
  topics: string[] = [];
  selected: string = '';
  flag:Boolean = false;

  constructor(private questionService: QuestionService,
    private router: Router) {
  }

  ngOnInit(): void {

    this.topics = topicArr;
    this.getQuestion();


  }

  isQuestionEmpty(): Boolean {
    // for(let q of this.questionList){
    //   if(q.status == 'false'){
    //     return false;
    //   }
    // }
    const filteredQuestions = this.questionList.filter(q => q.status === 'true');


   return filteredQuestions.length == 0 ? true : false;
  }


  searchBar(title: any) {
    this.questionService.getQuestionByTitle(title, this.selected).subscribe({
      next: (res) => {
        this.questionList = res;
      }
    })
  }


  getQuestion() {
    this.questionService.getAllQuestion().subscribe({
      next: (res) => {
        this.questionList = res;
      }
    });
  }


  showDetails(id: any) {
    // this.router.navigate(["/add-user"], {state: {data:this.user}})
    this.router.navigate(["/create_answer/" + id]);
    // this.userService.updateUser(id, user).subscribe({
    //   next:(res)=>console.log(res)
    // })
  }

  answerDetails(id: any) {
    // this.router.navigate(["/add-user"], {state: {data:this.user}})
    this.router.navigate(["/answers/" + id]);
    // this.userService.updateUser(id, user).subscribe({
    //   next:(res)=>console.log(res)
    // })
  }


  onOptionsSelected(event: any) {
    const value = event.target.value;
    this.selected = value;
    this.getQuestionByTopic(this.selected);

  }

  getQuestionByTopic(topic: string) {
    this.questionService.getQuestionTopic(topic).subscribe({
      next: (res) => {
        this.questionList = res;
      }
    })
  }


}
