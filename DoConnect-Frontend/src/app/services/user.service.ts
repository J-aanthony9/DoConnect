import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private base_url = environment.BASE_URL;

  constructor(private http:HttpClient) { }

  createUser(data:any):Observable<any>{
    return this.http.post(`${this.base_url}/user/addUser`, data);
  }

  getAllUser():Observable<User[]>{
    return this.http.get<User[]>(`${this.base_url}/user/getAllUsers`);
  }

  getUserById(id:any):Observable<User>{
    return this.http.get<User>(`${this.base_url}/user/${id}`);
  }

  getUserByName(name:string):Observable<User>{
    return this.http.get<User>(`${this.base_url}/user/${name}`);
  }

  getAllUserByType(usertype:string):Observable<User[]>{
    return this.http.get<User[]>(`${this.base_url}/user/getAllByUserType/${usertype}`);
  }

  updateUser(data:any, id:any):Observable<any>{
    return this.http.put(`${this.base_url}/user/updateuser/${id}`, data);
  }


}
