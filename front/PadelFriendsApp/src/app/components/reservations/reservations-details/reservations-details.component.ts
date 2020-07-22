import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ReservationService } from '../../../services/reservation.service';

import { UserViewModel } from '../../../models/User/UserViewModel';
import { ReservationItemViewModel } from '../../../models/ViewModel/ReservationItemViewModel';
@Component({
  selector: 'app-reservations-details',
  templateUrl: './reservations-details.component.html',
  styleUrls: ['./reservations-details.component.css']
})
export class ReservationsDetailsComponent implements OnInit {

  user: UserViewModel;
  reservationDetails: ReservationItemViewModel;
  reservationId: string;
  showEdit = false;

  constructor(
    private reservationService: ReservationService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.route.params.subscribe((params) => (this.reservationId = params.id));

      if (this.reservationId != null){
        this.reservationService.getReservation(this.reservationId).subscribe(
          data => {
            console.log(data);
            this.reservationDetails = data;
            this.checkPermissions();
          }
        );
      }
    }
  }

  checkPermissions(): void {
    if (this.user.role === 'ROLE_ADMIN'){
      this.showEdit = true;
    }
  }
}
