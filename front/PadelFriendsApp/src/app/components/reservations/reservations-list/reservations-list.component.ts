import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserViewModel } from '../../../models/User/UserViewModel';
import { Reservation } from 'src/app/models/Reservation/Reservation';

import { ReservationService } from 'src/app/services/reservation.service';

import { ProvinceAndCity, Province } from 'src/app/models/ProvincesCities';

@Component({
  selector: 'app-reservations-list',
  templateUrl: './reservations-list.component.html',
  styleUrls: ['./reservations-list.component.css']
})
export class ReservationsListComponent implements OnInit {

  user: UserViewModel;
  isShowNextMatches = true;
  isshowNextPublicMatches = false;

  reservationsUserList: Reservation[];
  reservationsUserListFilter: Reservation[];

  reservationsPublicList: Reservation[];
  reservationsPublicListFilter: Reservation[];

  provinceList: Province[] = [];
  cityList: string[] = [];

  provinceSelected: string;
  citySelected: string;

  showSpinner = true;
  showAlert = false;
  messageAlert = '';

  provinceAndCities = new ProvinceAndCity().provinces;

  constructor(private router: Router, private reservationService: ReservationService) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.showSpinner = true;
      this.showAlert = false;
      this.reservationService.getReservations().subscribe(
      data => {
        this.reservationsUserList = [];
        this.reservationsUserListFilter = [];

        this.provinceList = this.provinceAndCities;
        data.map(reservation => {
          if (this.user.reservationList.find(reservationUser => reservationUser.id === reservation.id)){
            this.reservationsUserList.push(reservation);
            this.reservationsUserListFilter.push(reservation);

            if (this.reservationsUserListFilter.length === 0){
              this.messageAlert = 'No have Results';
              this.showAlert = true;
            }
          }
        });

        this.showSpinner = false;
    },
    error => {
      console.log(error);
      this.messageAlert = 'It was not possible to connect to the server';
      this.showSpinner = false;
      this.showAlert = true;
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
    this.reservationsUserListFilter = [];
    this.provinceList = [];

    this.showSpinner = true;
    this.showAlert = false;
    this.reservationService.getReservations().subscribe(
      data => {
        data.map(reservation => {
          if (this.user.reservationList.find(reservationUser => reservationUser.id === reservation.id)){
            this.reservationsUserList.push(reservation);
            this.reservationsUserListFilter.push(reservation);
          }
        });

        this.provinceSelected = '';
        this.citySelected = '';

        this.provinceList = this.provinceAndCities;
        this.cityList = [];

        if (this.reservationsUserListFilter.length === 0){
          this.messageAlert = 'No have Results';
          this.showAlert = true;
        }
    },
    error => {
      console.log(error);
      this.messageAlert = 'It was not possible to connect to the server';
      this.showSpinner = false;
      this.showAlert = true;
    });
  }

  showNextPublicMatches(): void {
    this.isShowNextMatches = false;
    this.isshowNextPublicMatches = true;
    this.provinceList = [];
    this.showSpinner = true;
    this.showAlert = false;

    this.reservationService.getReservations().subscribe(
      data => {
        this.reservationsPublicList = data.filter(item => item.isprivate === false);
        this.reservationsPublicListFilter = data.filter(item => item.isprivate === false);

        this.provinceList = this.provinceAndCities;
        this.cityList = [];
        this.provinceSelected = '';
        this.citySelected = '';
        this.showSpinner = false;

        if (this.reservationsPublicListFilter.length === 0){
          this.messageAlert = 'No have Results';
          this.showAlert = true;
        }
    },
    error => {
      console.log(error);
      this.messageAlert = 'It was not possible to connect to the server';
      this.showSpinner = false;
      this.showAlert = true;
    });
  }

  fillCityList(): void {
    if ( this.provinceSelected !== '') {
      this.cityList = this.provinceAndCities.find(province => province.name === this.provinceSelected).cities;
      this.citySelected = '';

      if (this.showNextMatches){
        this.reservationsUserListFilter = this.reservationsUserList
        .filter(reservation => reservation.provinceClub === this.provinceSelected);

        if (this.reservationsUserListFilter.length === 0){
          this.messageAlert = 'No have Results';
          this.showAlert = true;
        }
      } else {
        this.reservationsPublicListFilter = this.reservationsPublicList
        .filter(reservation => reservation.provinceClub === this.provinceSelected);

        if (this.reservationsPublicListFilter.length === 0){
          this.messageAlert = 'No have Results';
          this.showAlert = true;
        }
      }
    } else {
      this.cityList = [];
      if (this.showNextMatches){
        this.reservationsUserListFilter = this.reservationsUserList;

        if (this.reservationsUserListFilter.length === 0){
          this.messageAlert = 'No have Results';
          this.showAlert = true;
        }

      } else {
        this.reservationsPublicListFilter = this.reservationsPublicList;

        if (this.reservationsPublicListFilter.length === 0){
          this.messageAlert = 'No have Results';
          this.showAlert = true;
        }
      }
    }
  }

  changeProvince(value: string): void {
    this.provinceSelected = value;
    this.fillCityList();
  }

  changeCity(value: string): void {
    if (this.showNextMatches){
      if (value !== ''){
        this.reservationsUserListFilter = this.reservationsUserList
        .filter(group => group.provinceClub === this.provinceSelected && group.cityClub === value);

        if (this.reservationsUserListFilter.length === 0){
          this.messageAlert = 'No have Results';
          this.showAlert = true;
        }
      } else {
        this.reservationsUserListFilter = this.reservationsUserList.filter(group => group.provinceClub === this.provinceSelected);

        if (this.reservationsUserListFilter.length === 0){
          this.messageAlert = 'No have Results';
          this.showAlert = true;
        }
      }
    } else {
      if (value !== ''){
        this.reservationsPublicListFilter = this.reservationsPublicList
        .filter(group => group.provinceClub === this.provinceSelected && group.cityClub === value);

        if (this.reservationsPublicListFilter.length === 0){
          this.messageAlert = 'No have Results';
          this.showAlert = true;
        }
      } else {
        this.reservationsPublicListFilter = this.reservationsPublicList.filter(group => group.provinceClub === this.provinceSelected);

        if (this.reservationsPublicListFilter.length === 0){
          this.messageAlert = 'No have Results';
          this.showAlert = true;
        }
      }
    }
  }
}
