import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserViewModel } from 'src/app/models/User/UserViewModel';

import { Club } from 'src/app/models/Club/Club';

import { ClubService } from 'src/app/services/club.service';

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

  constructor(private router: Router, private clubService: ClubService) {  }

  ngOnInit(): void {

    this.user = JSON.parse(localStorage.getItem('player'));


    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.clubService.getClubs().subscribe(
      data => {
        console.log(data);
        this.clubList = data;
        this.fillProvinceList(data);
    });
    }
  }

  fillProvinceList(data: Club[]): void {
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
