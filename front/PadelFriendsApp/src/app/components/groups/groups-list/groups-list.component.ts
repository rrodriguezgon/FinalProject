import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserViewModel } from '../../../models/User/UserViewModel';
import { Group } from 'src/app/models/Group/Group';

import { GroupService } from '../../../services/group.service';

import { ProvinceAndCity } from 'src/app/models/ProvincesCities';
@Component({
  selector: 'app-groups-list',
  templateUrl: './groups-list.component.html',
  styleUrls: ['./groups-list.component.css']
})
export class GroupsListComponent implements OnInit {

  user: UserViewModel;

  provinceList: string[] = [];
  cityList: string[] = [];

  provinceSelected: string;
  citySelected: string;

  groupList: Group[];

  groupListFilter: Group[];

  provinceAndCities = new ProvinceAndCity().provinces;

  showSpinner = false;
  showAlert = false;
  messageAlert = '';

  constructor(private router: Router, private groupService: GroupService) {  }

  ngOnInit(): void {

    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.showSpinner = true;
      this.groupService.getGroups().subscribe(
      data => {
        this.groupList = data;
        this.groupListFilter = data;
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

      this.groupListFilter = this.groupList.filter(group => group.province === this.provinceSelected);

    } else {
      this.cityList = [];
      this.groupListFilter = this.groupList;
    }
  }

  changeProvince(value: string): void {
    this.provinceSelected = value;
    this.fillCityList();
  }

  changeCity(value: string): void {

    if (value !== ''){
      this.groupListFilter = this.groupList.filter(group => group.city === value);
    } else {
      this.groupListFilter = this.groupList.filter(group => group.province === this.provinceSelected);
    }
  }
}
