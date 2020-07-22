import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Group } from '../models/Group/Group';
import { GroupDetails } from '../models/Group/GroupDetails';
import { CreateGroupDto } from 'src/app/models/Group/CreateGroupDto';

import { UserViewModel } from '../models/User/UserViewModel';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  urlPath = 'http://localhost:8080/groups';

  user: UserViewModel = JSON.parse(localStorage.getItem('player'));

  httpOptions = {
    headers: new HttpHeaders({'Content-Type':  'application/json'})};

    constructor(private http: HttpClient) { }

  getGroups(): Observable<Group[]>{
    this.loadHttp();
    return this.http.get<Group[]>(this.urlPath, this.httpOptions);
  }

  getGroupDetails(groupId: string): Observable<GroupDetails>{
    this.loadHttp();
    return this.http.get<GroupDetails>(this.urlPath + '/' + groupId, this.httpOptions);
  }

  createGroup(groupCreate: CreateGroupDto): Observable<GroupDetails>{
    this.loadHttp();
    return this.http.post<GroupDetails>(this.urlPath, groupCreate, this.httpOptions);
  }

  editGroup(groupId: string, groupCreate: CreateGroupDto): Observable<GroupDetails>{
    this.loadHttp();
    return this.http.put<GroupDetails>(this.urlPath + '/' + groupId, groupCreate, this.httpOptions);
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
