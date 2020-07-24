import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserViewModel } from '../../models/User/UserViewModel';
import { Reservation } from 'src/app/models/Reservation/Reservation';

import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  user: UserViewModel;
  isShowNextMatches = true;
  isshowNextPublicMatches = false;

  reservationsUserList: Reservation[];
  reservationsPublicList: Reservation[];

  showSpinner = false;
  showAlert = false;
  messageAlert = '';

  constructor(private router: Router, private reservationService: ReservationService) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.showSpinner = true;

      this.reservationService.getReservations().subscribe(
        data => {
          this.reservationsUserList = [];

          if (this.user.reservationList !== undefined && this.user.reservationList.length > 0){
            data.map(reservation => {
              if (this.user.reservationList.find(reservationUser => reservationUser.id === reservation.id)){
                this.reservationsUserList.push(reservation);
              }
            });
          }

          this.showSpinner = false;
      });
    }
  }

  hideAlert(): void {
    this.showAlert = false;
  }

  showNextMatches(): void {
    this.isShowNextMatches = true;
    this.isshowNextPublicMatches = false;
    this.reservationsUserList  = [];
    this.showSpinner = true;

    this.reservationService.getReservations().subscribe(
      data => {
        this.reservationsUserList = data;

        if (this.user.reservationList.length > 0){
          data.map(reservation => {
            if (this.user.reservationList.find(reservationUser => reservationUser.id === reservation.id)){
              this.reservationsUserList.push(reservation);
            }
          });
        }

        this.showSpinner = false;
    });
  }

  showNextPublicMatches(): void {
    this.isShowNextMatches = false;
    this.isshowNextPublicMatches = true;
    this.showSpinner = true;

    this.reservationService.getReservations().subscribe(
      data => {
        this.reservationsPublicList = data.filter(item => item.isprivate === false);

        this.showSpinner = false;
    });
  }
}
