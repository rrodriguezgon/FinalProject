import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Group } from 'src/app/models/Group/Group';
import { UserViewModel } from '../../../models/User/UserViewModel';

import { GroupService } from '../../../services/group.service';

import {NgbDateStruct, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';

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

  model: NgbDateStruct;
  date: {year: number, month: number};

  constructor(private router: Router, private groupService: GroupService, private calendar: NgbCalendar) {  }

  ngOnInit(): void {

    this.user = JSON.parse(localStorage.getItem('player'));


    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      console.log('getgroups');
      this.groupService.getGroups().subscribe(
      data => {
        console.log(data);
        this.groupList = data;
        this.fillProvinceList(data);
    });
    }
  }

  fillProvinceList(data: Group[]): void {
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

  selectToday(): void {
    this.model = this.calendar.getToday();
  }
}
