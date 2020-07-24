import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { GroupDetails } from 'src/app/models/Group/GroupDetails';
import { UserViewModel } from '../../../models/User/UserViewModel';

import { GroupService } from '../../../services/group.service';

@Component({
  selector: 'app-groups-details',
  templateUrl: './groups-details.component.html',
  styleUrls: ['./groups-details.component.css']
})
export class GroupsDetailsComponent implements OnInit {

  user: UserViewModel;
  groupId: string;
  groupDetails: GroupDetails;

  showEdit = false;

  showSpinner = false;
  showAlert = false;
  messageAlert = '';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private groupService: GroupService) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.route.params.subscribe((params) => (this.groupId = params.id));

      if (this.groupId != null){
        this.showSpinner = true;
        this.groupService.getGroupDetails(this.groupId).subscribe(
          data => {
            this.groupDetails = data;
            this.checkPermissions();
            this.showSpinner = false;
          }
        );
      }
    }
  }

  checkPermissions(): void {
    if (this.user.role !== 'ROLE_ADMIN'){
      const result = this.groupDetails.userGroupList.filter(x => x.userId === this.user.id);

      if (result.length > 0){
        this.showEdit = result[0].admin;
      }
    } else {
      this.showEdit = true;
    }
  }
}
