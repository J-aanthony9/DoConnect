import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment.development';
import { Answer } from './models/answer.model';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  private base_url = environment.BASE_URL;

  constructor(private http: HttpClient) { }

  getAllAnswer(): Observable<Answer[]> {
    return this.http.get<Answer[]>(`${this.base_url}/answer/getallanswers`)
  }

  getAllAnswerFalse(): Observable<Answer[]> {
    return this.http.get<Answer[]>(`${this.base_url}/answer/getAllAnswerFalse`)
  }

  createAnswer(data: any): Observable<any> {
    return this.http.post(`${this.base_url}/answer/addAnswer`, data);
  }

  deleteAnswer(id: any): Observable<any> {
    return this.http.delete(`${this.base_url}/answer/deleteanswerbyid/${id}`);
  }

  getAnswerById(id: any): Observable<Answer> {
    return this.http.get(`${this.base_url}/answer/getanswerbyid/${id}`);
  }

  updateAnswer(id: any, data: any): Observable<any> {
    return this.http.put(`${this.base_url}/answer/getanswerbyid/${id}`, data);
  }

  getAnswerbyQuestionID(id: any): Observable<Answer> {
    return this.http.get(`${this.base_url}/getAnswersbyQuestionID/{id}`);
  }

}
