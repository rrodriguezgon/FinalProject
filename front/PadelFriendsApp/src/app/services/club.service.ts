import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Club } from '../models/Club/Club';
import { ClubViewModel } from 'src/app/models/ViewModel/ClubViewModel';

import { UserViewModel } from '../models/User/UserViewModel';


@Injectable({
  providedIn: 'root'
})
export class ClubService {

  urlPath = 'https://padelfriendsapp.herokuapp.com/clubs';

  user: UserViewModel = JSON.parse(localStorage.getItem('player'));

  httpOptions = {
    headers: new HttpHeaders({'Content-Type':  'application/json'})};

    constructor(private http: HttpClient) { }

  getClubs(): Observable<Club[]>{
    this.loadHttp();
    return this.http.get<Club[]>(this.urlPath, this.httpOptions);
  }

  getClub(clubId: string): Observable<ClubViewModel>{
    this.loadHttp();
    return this.http.get<ClubViewModel>(this.urlPath + '/' + clubId, this.httpOptions);
  }

  createClub(clubCreate: Club): Observable<ClubViewModel>{
    this.loadHttp();
    return this.http.post<ClubViewModel>(this.urlPath, clubCreate, this.httpOptions);
  }

  editClub(clubId: string, clubCreate: Club): Observable<ClubViewModel> {
    this.loadHttp();
    return this.http.put<ClubViewModel>(this.urlPath + '/' + clubId, clubCreate, this.httpOptions);
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
