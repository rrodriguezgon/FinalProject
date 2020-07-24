import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ClubViewModel } from 'src/app/models/ViewModel/ClubViewModel';
import { UserViewModel } from '../../../models/User/UserViewModel';

import { ClubService } from '../../../services/club.service';

@Component({
  selector: 'app-clubs-details',
  templateUrl: './clubs-details.component.html',
  styleUrls: ['./clubs-details.component.css']
})
export class ClubsDetailsComponent implements OnInit {

  user: UserViewModel;
  clubId: string;
  clubDetails: ClubViewModel;

  showEdit = false;

  showSpinner = false;
  showAlert = false;
  messageAlert = '';

  constructor(private route: ActivatedRoute,
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
            this.clubDetails = data;
            this.checkPermissions();
            this.showSpinner = false;
          }
        );
      }
    }
  }

  hideAlert(): void {
    this.showAlert = false;
  }
  
  checkPermissions(): void {
    if (this.user.role === 'ROLE_ADMIN'){
      this.showEdit = true;
    }
  }
}
