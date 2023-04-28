import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Question } from '../models/question.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private base_url = "localhost:8080/question/addQuestion";

  constructor(private http: HttpClient) { }

  createQuestion(data: any): Observable<any> {
    return this.http.post(`${this.base_url}`, data,httpOptions);
  }

  updateQuestion(data: any, id: any): Observable<any> {
    return this.http.put(`${this.base_url}/question/updateQuestion/{id}`, data);
  }

  deleteQuestion(id: any): Observable<any> {
    return this.http.delete(`${this.base_url}/question/deleteQuestionById/{id}`);
  }

  getAllQuestion(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.base_url}/question/getAllQuestion`);
  }

  getAllQuestionFalse(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.base_url}/question/getAllQuestionFalse`);
  }

  getQuestionTopic(topic: string): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.base_url}/question?topic=${topic}`)
  }

  getQuestionById(id: number): Observable<Question> {
    return this.http.get<Question>(`${this.base_url}/question/getQuestionById`);
  }


}
