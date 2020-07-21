import { Component, OnInit } from '@angular/core';
import { Group } from 'src/app/models/Group/Group';

@Component({
  selector: 'app-groups-list',
  templateUrl: './groups-list.component.html',
  styleUrls: ['./groups-list.component.css']
})
export class GroupsListComponent implements OnInit {

  provinceList: string[] = [];
  cityList: string[] = [];

  provinceSelected: string;
  citySelected: string;

  groupList: Group[];

  constructor() { }

  ngOnInit(): void {
    this.fillProvinceList();
  }

  fillProvinceList(): void {
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
