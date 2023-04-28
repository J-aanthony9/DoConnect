import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private base_url = environment.BASE_URL;

  constructor(private http: HttpClient) {
  }

  user:User = {
    id:undefined,
    username:undefined,
    usertype:undefined  
  }
  

  login(Data: any): Observable<User> {
    return this.http.post(`${this.base_url}/user/authenticate`, Data, httpOptions);
  }

  
}
