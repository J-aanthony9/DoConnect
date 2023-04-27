import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs';


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
  

  login(Data: any): Observable<any> {
    return this.http.post(`${this.base_url}/user/authenticate`, Data, httpOptions);
    // this is just the HTTP call, 
    // we still need to handle the reception of the token
    //.shareReplay();
  }

  // private setSession(authResult: any) {
  //   const expiresAt = moment().add(authResult.expiresIn, 'second');

  //   localStorage.setItem('Bearer token', authResult.idToken);
  //   localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()));
  // }

  // logout() {
  //   localStorage.removeItem("Bearer token");
  //   localStorage.removeItem("expires_at");
  // }

  // public isLoggedIn() {
  //   return moment().isBefore(this.getExpiration());
  // }

  // isLoggedOut() {
  //   return !this.isLoggedIn();
  // }

  // getExpiration() {

  //   const expiration = localStorage.getItem("expires_at");

  //   const expiresAt = JSON.parse(expiration || '{}');
  //   return moment(expiresAt);
  // }
}
