import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserViewModel } from '../../../models/User/UserViewModel';
import { Reservation } from 'src/app/models/Reservation/Reservation';

import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-reservations-list',
  templateUrl: './reservations-list.component.html',
  styleUrls: ['./reservations-list.component.css']
})
export class ReservationsListComponent implements OnInit {

  user: UserViewModel;
  isShowNextMatches = true;
  isshowNextMatchesGroups = false;
  isshowNextPublicMatches = false;

  reservationsUserList: Reservation[];
  reservationsGroupList: Reservation[];
  reservationsPublicList: Reservation[];

  provinceList: string[] = [];
  cityList: string[] = [];

  provinceSelected: string;
  citySelected: string;

  constructor(private router: Router, private reservationService: ReservationService) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.reservationService.getReservations().subscribe(
      data => {
        this.reservationsUserList = data;
        this.fillProvinceList(data);
    });
    }
  }

  showNextMatches(): void {
    this.isShowNextMatches = true;
    this.isshowNextMatchesGroups = false;
    this.isshowNextPublicMatches = false;

    this.reservationService.getReservations().subscribe(
      data => {
        this.reservationsUserList = data;
    });

  }

  showNextMatchesGroups(): void {
    this.isShowNextMatches = false;
    this.isshowNextMatchesGroups = true;
    this.isshowNextPublicMatches = false;

    this.reservationService.getReservations().subscribe(
      data => {
        this.reservationsGroupList = data;
    });
  }

  showNextPublicMatches(): void {
    this.isShowNextMatches = false;
    this.isshowNextMatchesGroups = false;
    this.isshowNextPublicMatches = true;

    this.reservationService.getReservations().subscribe(
      data => {
        this.reservationsPublicList = data;
    });
  }


  fillProvinceList(data: Reservation[]): void {
    this.provinceSelected = '';
    this.provinceList.push('MADRID');
    this.provinceList.push('GIRONA');
  }

  fillCityList(): void {
    this.cityList = [];
    this.citySelected = '';

    if (this.provinceSelected === 'MADRID'){
      this.cityList.push('MOSTOLES');
      this.cityList.push('ALCORCON');
      this.cityList.push('POZUELO DE ALARCON');
    } else if (this.provinceSelected === 'GIRONA')
    {
      this.cityList.push('FIGUERES');
      this.cityList.push('GIRONA');
    }
  }

  changeProvince(value: string): void {
    this.provinceSelected = value;
    this.fillCityList();
  }

  changeCity(value: string): void {
    this.citySelected = value;
  }

}
