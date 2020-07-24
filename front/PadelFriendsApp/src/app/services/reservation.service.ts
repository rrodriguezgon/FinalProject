import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Reservation } from '../models/Reservation/Reservation';
import { ReservationItemViewModel } from '../models/ViewModel/ReservationItemViewModel';

import { GroupDetails } from '../models/Group/GroupDetails';
import { CreateGroupDto } from 'src/app/models/Group/CreateGroupDto';

import { UserViewModel } from '../models/User/UserViewModel';
import { CreateReservationDto } from '../models/Reservation/CreateReservationDto';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  urlPath = 'https://padelfriendsapp.herokuapp.com/reservations';

  user: UserViewModel = JSON.parse(localStorage.getItem('player'));

  httpOptions = {
    headers: new HttpHeaders({'Content-Type':  'application/json'})};

    constructor(private http: HttpClient) { }

  getReservations(): Observable<Reservation[]>{
    this.loadHttp();
    return this.http.get<Reservation[]>(this.urlPath, this.httpOptions);
  }

  getReservation(reservationId: string): Observable<ReservationItemViewModel>{
    this.loadHttp();
    return this.http.get<ReservationItemViewModel>(this.urlPath + '/' + reservationId, this.httpOptions);
  }

  createReservation(reservationCreste: CreateReservationDto): Observable<ReservationItemViewModel>{
    this.loadHttp();
    return this.http.post<ReservationItemViewModel>(this.urlPath, reservationCreste, this.httpOptions);
  }

  editReservation(reservationId: string, reservationCreste: CreateReservationDto): Observable<ReservationItemViewModel>{
    this.loadHttp();
    return this.http.put<ReservationItemViewModel>(this.urlPath + '/' + reservationId, reservationCreste, this.httpOptions);
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
