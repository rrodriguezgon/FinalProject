import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserViewModel } from 'src/app/models/User/UserViewModel';

import { Club } from 'src/app/models/Club/Club';

import { ClubService } from 'src/app/services/club.service';

import { ProvinceAndCity } from 'src/app/models/ProvincesCities';

@Component({
  selector: 'app-clubs-list',
  templateUrl: './clubs-list.component.html',
  styleUrls: ['./clubs-list.component.css']
})
export class ClubsListComponent implements OnInit {

  user: UserViewModel;

  provinceList: string[] = [];
  cityList: string[] = [];

  provinceSelected: string;
  citySelected: string;

  clubList: Club[];
  clubListFilter: Club[];

  provinceAndCities = new ProvinceAndCity().provinces;

  showSpinner = true;
  showAlert = false;
  messageAlert = '';

  constructor(private router: Router, private clubService: ClubService) {  }

  ngOnInit(): void {

    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.showSpinner = true;

      this.clubService.getClubs().subscribe(
      data => {
        console.log(data);
        this.clubList = data;
        this.clubListFilter = data;

        this.showSpinner = false;
    });
    }
  }

  hideAlert(): void {
    this.showAlert = false;
  }

  fillCityList(): void {
    if ( this.provinceSelected !== ''){
      this.cityList = this.provinceAndCities.find(province => province.name === this.provinceSelected).cities;
      this.citySelected = '';

      this.clubListFilter = this.clubList.filter(group => group.province === this.provinceSelected);

    } else {
      this.cityList = [];
      this.clubListFilter = this.clubList;
    }
  }

  changeProvince(value: string): void {
    this.provinceSelected = value;
    this.fillCityList();
  }

  changeCity(value: string): void {

    if (value !== ''){
      this.clubListFilter = this.clubListFilter.filter(group => group.city === value);
    } else {
      this.clubListFilter = this.clubList.filter(group => group.province === this.provinceSelected);
    }
  }
}
