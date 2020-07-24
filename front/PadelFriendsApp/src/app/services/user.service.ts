import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { UserViewModel } from '../models/User/UserViewModel';
import { Player } from '../models/User/Player';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  urlPath = 'https://padelfriendsapp.herokuapp.com/users';

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

  getPlayers(): Observable<Player[]>{
    this.loadHttp();
    return this.http.get<Player[]>(this.urlPath, this.httpOptions);
  }

  loadHttp(): void {
    this.user = JSON.parse(localStorage.getItem('player'));
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
