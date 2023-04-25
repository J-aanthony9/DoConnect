import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Question } from '../models/question.model';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private base_url = environment.BASE_URL;

  constructor(private http:HttpClient) { }

  createQuestion(data:any):Observable<any>{
    return this.http.post(`${this.base_url}/quesiton/addquesiton`,data);
  }

  updateQuestion(data:any, id:any):Observable<any>{
    return this.http.put(`${this.base_url}/question/updatequestion/{id}`,data);
  }

  deleteQuestion(id:any):Observable<any>{
    return this.http.delete(`${this.base_url}/question/deletequestiobyid/{id}`);
  }

  getAllQuestion():Observable<Question[]>{
    return this.http.get<Question[]>(`${this.base_url}/question/getallquestion`);
  }


}
