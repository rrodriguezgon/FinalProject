import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { GroupDetails } from 'src/app/models/Group/GroupDetails';
import { UserGroupViewModel } from 'src/app/models/Group/UserGroupViewModel';
import { CreateGroupDto } from 'src/app/models/Group/CreateGroupDto';
import { UserGroup } from 'src/app/models/Group/UserGroup';
import { UserGroupID } from 'src/app/models/Group/UserGroupID';

import { UserViewModel } from 'src/app/models/User/UserViewModel';


import { GroupService } from 'src/app/services/group.service';
import { UserService } from 'src/app/services/user.service';
import { Player } from 'src/app/models/User/Player';

import { ProvinceAndCity } from 'src/app/models/ProvincesCities';
@Component({
  selector: 'app-groups-form',
  templateUrl: './groups-form.component.html',
  styleUrls: ['./groups-form.component.css']
})
export class GroupsFormComponent implements OnInit {

  user: UserViewModel;
  groupId: string;

  playerList: Player[];

  playerIdSelected = '';
  playerNameSelected = '';
  playerAdmin = false;
  groupDetails: GroupDetails;
  groupForm: FormGroup;

  cityList: string[] = [];

  provinceSelected: string;
  citySelected: string;

  provinceAndCities = new ProvinceAndCity().provinces;

  showSpinner = false;
  showAlert = false;
  messageAlert = '';

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private groupService: GroupService,
              private userService: UserService) { }

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
            this.fillPlayers();
            this.createForm(data);
            this.groupDetails = data;
            this.playerList = [];
            this.showSpinner = false;
          }
        );
      } else {
        this.fillPlayers();
        this.createForm(null);
        this.groupDetails = new GroupDetails();
      }
    }
  }

  hideAlert(): void {
    this.showAlert = false;
  }

  createForm(data: GroupDetails): void{
    this.groupForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      description: ['', [Validators.required, Validators.minLength(4)]],
      city: ['', [Validators.required, Validators.minLength(4)]],
      province: ['', [Validators.required, Validators.minLength(4)]],
    });

    if (data != null){
      this.groupForm.get('province').setValue(data.province);
      this.provinceSelected = data.province;
      this.fillCityList(data.city);
    }
  }

  fillPlayers(): void {
    this.userService.getPlayers().subscribe(data => {
      this.playerList = data;
      this.removePlayer(this.user.id);
    });
  }

  removePlayer(idPlayer: string): void {
      this.playerList = this.playerList.filter(item => item.id !== idPlayer);
  }

  changedPlayer(event: Event): void{
    const selectedOptions = event.target['options'];
    const selectedIndex = selectedOptions.selectedIndex;
    const selectElementText = selectedOptions[selectedIndex].text;
    const selectElementValue = selectedOptions[selectedIndex].value;

    this.playerIdSelected = selectElementValue;
    this.playerNameSelected = selectElementText;
  }

  changedAdmin(value: boolean): void{
    this.playerAdmin = value;
  }

  addPlayer(): void {
    if (this.playerIdSelected !== '') {
      const newPlayer: UserGroupViewModel = new UserGroupViewModel();
      newPlayer.groupId = this.groupId;
      newPlayer.nameGroup = this.groupDetails.name;
      newPlayer.nameUser = this.playerNameSelected;
      newPlayer.userId = this.playerIdSelected;
      newPlayer.property = false;
      newPlayer.admin = this.playerAdmin;

      this.groupDetails.userGroupList.push(newPlayer);

      this.playerList = this.playerList.filter(item => item.id !== this.playerIdSelected);
      this.playerIdSelected = '';
      this.playerNameSelected = '';
    }
  }

  removePlayerList(idPlayer: string, namePlayer: string): void {
    this.groupDetails.userGroupList = this.groupDetails.userGroupList.filter(item => item.userId !== idPlayer);

    const player = new Player();
    player.id = idPlayer;
    player.name = namePlayer;

    this.playerList.push(player);
  }

  submitGroup(): void {
    if (this.groupForm.valid){

    const userGroupList: UserGroup[] = [];

    this.groupDetails.userGroupList.map(
      userGroupView => {
        const userGroup = new UserGroup();
        userGroup.admin = userGroupView.admin;
        userGroup.property = userGroupView.property;
        userGroup.userGroupID = new UserGroupID(userGroupView.userId);

        userGroupList.push(userGroup);
      }
    );

    const newGroup = new CreateGroupDto(this.groupForm.value.name, this.groupForm.value.description, this.groupDetails.image,
      this.groupForm.value.city, this.groupForm.value.province, userGroupList);

    if (this.groupId != null){
      this.groupService.editGroup(this.groupId, newGroup).subscribe(
        data => {
          this.router.navigate(['/groups']);
        },
        error => {
          console.log(error);
        }
      );
    } else {
      const userGroup = new UserGroup();
      userGroup.admin = true;
      userGroup.property = true;
      userGroup.userGroupID = new UserGroupID(this.user.id);

      userGroupList.push(userGroup);

      newGroup.image = '';

      this.groupService.createGroup(newGroup).subscribe(
        data => {
          this.user.groupList.push(data);
          localStorage.setItem('player', JSON.stringify(this.user));
          this.router.navigate(['/groups']);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
  }

  fillCityList(nameCity: string): void {
    if ( this.provinceSelected !== ''){
      this.cityList = this.provinceAndCities.find(province => province.name === this.provinceSelected).cities;
  
      if (nameCity !== ''){
        this.groupForm.get('city').setValue(nameCity);
        this.citySelected = nameCity;
      } else {
        this.groupForm.get('city').setValue('');
        this.citySelected = '';
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
}
