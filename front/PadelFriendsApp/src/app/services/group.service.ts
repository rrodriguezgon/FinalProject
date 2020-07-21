import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Group } from '../models/Group/Group';
import { DetailGroup } from '../models/Group/DetailGroup';

import { UserViewModel } from '../models/User/UserViewModel';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  urlPath = 'http://localhost:8080/groups';

  user: UserViewModel = JSON.parse(localStorage.getItem('currentUser'));

  httpOptions = {
    headers: new HttpHeaders({'Content-Type':  'application/json'})};

    constructor(private http: HttpClient) { }

  getGroups(): Observable<Group[]>{
    this.loadHttp();
    return this.http.get<Group[]>(this.urlPath, this.httpOptions);
  }

  loadHttp(): void {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.user.username = 'raquel';
    this.user.password = 'Admin1234!';
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
