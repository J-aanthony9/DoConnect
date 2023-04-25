import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Chat } from '../models/chat.model'

@Injectable({
  providedIn: 'root'
})

export class ChatService {

  private base_url = environment.BASE_URL;

  constructor(private http: HttpClient) { }

  getAllMsg(): Observable<Chat[]> {
    return this.http.get<Chat[]>(`${this.base_url}/chat/getallmsg`);
  }

  createMsg(data: any): Observable<any> {
    return this.http.post(`${this.base_url}/chat/addMsg`, data);
  }

  deleteChat(id: any): Observable<any> {
    return this.http.delete(`${this.base_url}/chat/deleteChatById/${id}`);
  }

}
