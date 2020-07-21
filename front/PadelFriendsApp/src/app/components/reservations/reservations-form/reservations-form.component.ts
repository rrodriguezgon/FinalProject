import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { UserService } from '../../../services/user.service';
import { UserViewModel } from '../../../models/User/UserViewModel';
@Component({
  selector: 'app-reservations-form',
  templateUrl: './reservations-form.component.html',
  styleUrls: ['./reservations-form.component.css']
})
export class ReservationsFormComponent implements OnInit {

  createReservationForm: FormGroup;
  user: UserViewModel;
  pronvices: ['Barcelona', 'Gerona', 'Madrid'];
  cities: ['Figueres', 'Pozuelo de Alarcón', 'Alcorcon'];
  clubs: ['Mad4Padel', 'Bularás Padel', 'Alpha Padel'];

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user != null) {
      this.router.navigate(['/']);
    } else {
      this.createReservationForm = this.formBuilder.group({
        provinceResevation : ['', [Validators.required]]
      });
    }
  }

  createReservation(): void {

  }
}
