import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { UserViewModel } from '../models/User/UserViewModel';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  urlPath = 'http://localhost:8080/users';

  user: UserViewModel = JSON.parse(localStorage.getItem('currentUser'));

  httpOptions = {
    headers: new HttpHeaders({'Content-Type':  'application/json'})};

    constructor(private http: HttpClient) { }

  login(loginDto: any): Observable<UserViewModel>{
    this.loadHttp();
    return this.http.post<UserViewModel>(this.urlPath + '/login', loginDto);
  }

  register(registerDto: any): Observable<UserViewModel>{
    this.loadHttp();
    return this.http.post<UserViewModel>(this.urlPath, registerDto);
  }

  loadHttp(): void {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    if (this.user != null){
      this.httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          Authorization: 'Basic ' + btoa( this.user.username + ':' + this.user.password)
        })
      };
    }
  }
}
