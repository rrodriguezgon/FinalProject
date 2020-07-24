import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ClubViewModel } from 'src/app/models/ViewModel/ClubViewModel';
import { Club } from 'src/app/models/Club/Club';

import { UserViewModel } from '../../../models/User/UserViewModel';

import { ClubService } from '../../../services/club.service';

import { ProvinceAndCity } from 'src/app/models/ProvincesCities';

@Component({
  selector: 'app-clubs-form',
  templateUrl: './clubs-form.component.html',
  styleUrls: ['./clubs-form.component.css']
})
export class ClubsFormComponent implements OnInit {

  user: UserViewModel;
  clubId: string;
  clubDetails: ClubViewModel;
  clubForm: FormGroup;

  cityList: string[] = [];

  provinceSelected: string;
  citySelected: string;

  provinceAndCities = new ProvinceAndCity().provinces;

  showEdit = false;

  showSpinner = false;
  showAlert = false;
  messageAlert = '';

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private clubService: ClubService) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.route.params.subscribe((params) => (this.clubId = params.id));

      if (this.clubId != null){
        this.showSpinner = true;
        this.clubService.getClub(this.clubId).subscribe(
          data => {
            this.createForm(data);
            this.clubDetails = data;
            this.showSpinner = false;
          }
        );
      } else {
        this.createForm(null);
        this.clubDetails = new ClubViewModel();
      }
    }
  }

  hideAlert(): void {
    this.showAlert = false;
  }

  createForm(data: ClubViewModel): void{
    this.clubForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      numberCourts: ['', [Validators.required]],
      city: ['', [Validators.required, Validators.minLength(4)]],
      province: ['', [Validators.required, Validators.minLength(4)]],
      ubication: ['', [Validators.required, Validators.minLength(4)]],
    });

    if (data != null){
      this.clubForm.get('province').setValue(data.province);
      this.provinceSelected = data.province;
      this.fillCityList(data.city);
    }
  }

  submitForm(): void {
    if (this.clubForm.valid){

      const newClub = new Club(this.clubForm.value.name, this.clubForm.value.ubication , 'image',
        this.clubForm.value.city, this.clubForm.value.province, this.clubForm.value.numberCourts,
        this.clubDetails.latitude, this.clubDetails.longitude);

      if (this.clubId != null){
        this.clubService.editClub(this.clubId, newClub).subscribe(
          data => {
            this.router.navigate(['/clubs']);
          },
          error => {
            console.log(error);
          }
        );
      } else {
        this.clubService.createClub(newClub).subscribe(
          data => {
            this.router.navigate(['/clubs']);
          },
          error => {
            console.log(error);
          }
        );
      }
  }
}

checkValidations(): boolean {
  let valid = this.clubForm.valid;


  if (this.clubForm.invalid){

  }

  return valid;
}

fillCityList(nameCity: string): void {
  if ( this.provinceSelected !== ''){
    this.cityList = this.provinceAndCities.find(province => province.name === this.provinceSelected).cities;

    if (nameCity !== ''){
      this.clubForm.get('city').setValue(nameCity);
      this.citySelected = nameCity;
    } else {
      this.citySelected = '';
      this.clubForm.get('city').setValue('');
    }
  } else {
    this.cityList = [];
  }
}

changeProvince(value: string): void {
  this.provinceSelected = value;
  this.fillCityList('');
}

changeCity(value: string): void {
  this.citySelected = value;
}

countChangedHandler(count: any) {
  console.log(count);
  this.clubDetails.latitude = count.result.center[1];
  this.clubDetails.longitude = count.result.center[0];
  this.clubDetails.ubication = count.result.place_name;
}
}
