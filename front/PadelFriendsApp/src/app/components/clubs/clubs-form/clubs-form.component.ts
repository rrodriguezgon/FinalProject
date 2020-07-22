import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ClubViewModel } from 'src/app/models/ViewModel/ClubViewModel';
import { Club } from 'src/app/models/Club/Club';

import { UserViewModel } from '../../../models/User/UserViewModel';

import { ClubService } from '../../../services/club.service';

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

  showEdit = false;

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
        this.clubService.getClub(this.clubId).subscribe(
          data => {
            this.clubDetails = data;
            this.createForm();
          }
        );
      } else {
        this.createForm();
        this.clubDetails = new ClubViewModel();
      }
    }
  }

  createForm(): void{
    this.clubForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      numberCourts: ['', [Validators.required]],
      city: ['', [Validators.required, Validators.minLength(4)]],
      province: ['', [Validators.required, Validators.minLength(4)]],
      ubication: ['', [Validators.required, Validators.minLength(4)]],
    });
  }

  submitForm(): void {
    if (this.clubForm.valid){

      const newClub = new Club(this.clubForm.value.name, this.clubForm.value.ubication , 'image',
        this.clubForm.value.city, this.clubForm.value.province, this.clubForm.value.numberCourts, 0, 0);

      console.log(newClub);
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

}
