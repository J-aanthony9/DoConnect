import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../services/question.service';
import { Question } from '../models/question.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  questionList: Question[] = [];
  searchInput:string="";

  // question:Question = {
  //   id:0,
  //   topic:"",
  //   title:"",
  //   description_question:""
  // }
  
  constructor(private questionService: QuestionService,
    private router:Router){}

  ngOnInit(): void {
    this.getQuestion();
  }

  searchBar(title:any){
    this.questionService.getQuestionByTitle(title).subscribe({
      next: (res) => {
        this.questionList = res;
      }
    })
  }


  getQuestion() {
    this.questionService.getAllQuestionFalse().subscribe({
      next: (res) => {
        this.questionList = res;
      }
    });
  }


  showDetails(id:any){ 
    // this.router.navigate(["/add-user"], {state: {data:this.user}})
    this.router.navigate(["/create_answer/" + id]);
    // this.userService.updateUser(id, user).subscribe({
    //   next:(res)=>console.log(res)
    // })
  }


}
