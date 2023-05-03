import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Question } from '../models/question.model';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + storageService.getuser
    })
  };

  private base_url = environment.BASE_URL;

  constructor(private http: HttpClient, private storageService: StorageService) {
    const token = this.storageService.getUser().token;
    
    this.httpOptions.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + token
    })
  }

  createQuestion(data: any): Observable<any> {
    return this.http.post(`${this.base_url}/question/addQuestion`, data, this.httpOptions);
    // return this.http.post(`${this.base_url}/user/authenticate`, data, httpOptions);
  }

  updateQuestion(data: any, id: any): Observable<any> {
    return this.http.put(`${this.base_url}/question/updateQuestion/${id}`, data);
  }

  deleteQuestion(id: any): Observable<any> {
    return this.http.delete(`${this.base_url}/question/deleteQuestionById/${id}`);
  }

  getAllQuestion(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.base_url}/question/getAllQuestion`);
  }

  getAllQuestionFalse(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.base_url}/question/getAllQuestionFalse`);
  }

  getQuestionTopic(topic: string): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.base_url}/question/getQuestionByTopic?topic=${topic}`)
  }

  getQuestionById(id: any): Observable<Question> {
    return this.http.get<Question>(`${this.base_url}/question/getQuestionById/${id}`);
  }

  getQuestionByTitle(title:any): Observable<Question[]>{
    return this.http.get<Question[]>(`${this.base_url}/question/getQuestionByTitle?title=${title}`);
  }


}
