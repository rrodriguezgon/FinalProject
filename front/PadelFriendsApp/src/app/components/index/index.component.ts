import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserViewModel } from '../../models/User/UserViewModel';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  user: UserViewModel;
  isShowNextMatches = true;
  isshowNextMatchesGroups = false;
  isshowNextPublicMatches = false;
  constructor(private router: Router) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('player'));

    
    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      console.log(this.user);
    }
    
  }


  showNextMatches(): void {
    this.isShowNextMatches = true;
    this.isshowNextMatchesGroups = false;
    this.isshowNextPublicMatches = false;
  }

  showNextMatchesGroups(): void {
    this.isShowNextMatches = false;
    this.isshowNextMatchesGroups = true;
    this.isshowNextPublicMatches = false;
  }

  showNextPublicMatches(): void {
    this.isShowNextMatches = false;
    this.isshowNextMatchesGroups = false;
    this.isshowNextPublicMatches = true;
  }
}
