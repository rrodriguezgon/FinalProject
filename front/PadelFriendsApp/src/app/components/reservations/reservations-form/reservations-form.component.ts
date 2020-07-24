import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ReservationService } from '../../../services/reservation.service';
import { ClubService } from '../../../services/club.service';
import { UserService } from '../../../services/user.service';
import { GroupService } from '../../../services/group.service';

import { Club } from 'src/app/models/Club/Club';
import { Player } from 'src/app/models/User/Player';
import { Group } from 'src/app/models/Group/Group';

import { UserViewModel } from '../../../models/User/UserViewModel';
import { ReservationItemViewModel } from '../../../models/ViewModel/ReservationItemViewModel';
import { NgbDateStruct, NgbCalendar, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/User/User';

import { CreateReservationDto } from 'src/app/models/Reservation/CreateReservationDto';
import { flatten } from '@angular/compiler';
import { Reservation } from 'src/app/models/Reservation/Reservation';

@Component({
  selector: 'app-reservations-form',
  templateUrl: './reservations-form.component.html',
  styleUrls: ['./reservations-form.component.css']
})
export class ReservationsFormComponent implements OnInit {

  user: UserViewModel;
  reservationDetails: ReservationItemViewModel;
  reservationId: string;
  showEdit = false;

  dateReservation: NgbDateStruct = this.calendar.getToday();
  hourReservation = {hour: 13, minute: 30};
  isprivateReservation: boolean;
  clubIdReservation: string;

  clubs: Club[];
  clubSelected: Club;

  statusList = ['CLOSED'];

  playerList: Player[] = [];

  playerIdSelected = '';
  playerNameSelected = '';

  groupList: Group[] = [];

  groupIdSelected = '';
  groupNameSelected = '';

  showSpinner = true;
  showSpinnerClub = false;
  showButtonSpinner = false;
  showAlert = false;
  messageAlert = '';

  title = 'Create';

  constructor(private calendar: NgbCalendar,
              private reservationService: ReservationService,
              private userService: UserService,
              private clubService: ClubService,
              private groupService: GroupService,
              private router: Router,
              private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.route.params.subscribe(
        (params) => {
          this.reservationId = params.id;
        });

      if (this.reservationId != null){
        this.title = 'Edit';
        this.showSpinner = true;
        this.reservationService.getReservation(this.reservationId).subscribe(
          data => {
            this.fillClubs();
            this.changeClub(data.club.id);
            this.fillPlayers(data.userList);
            this.fillGroups(data.groupList);

            const year = Number(data.date.toString().substr(0, 4));
            const day = Number(data.date.toString().substr(8, 2));
            const month = Number(data.date.toString().substr(5, 2));

            this.dateReservation = new NgbDate(year, month, day);

            const hour = Number(data.date.toString().substr(11, 2));
            const minute = Number(data.date.toString().substr(14, 2));

            this.hourReservation.hour = hour;
            this.hourReservation.minute = minute;

            this.reservationDetails = data;
            this.showSpinner = false;
          }
        );
      } else {
        this.fillClubs();
        this.fillPlayers([]);
        this.fillGroups([]);
        this.reservationDetails = new ReservationItemViewModel();
        this.dateReservation = this.calendar.getToday();
        this.showSpinner = false;
      }
    }
  }

  hideAlert(): void {
    this.showAlert = false;
  }

  selectToday(): void {
    this.dateReservation = new NgbDate(2020, 2, 19);
  }

  fillClubs(): void{
    this.clubService.getClubs().subscribe(
      data => {
        this.clubs = data;

        if (this.clubs.length === 0){
          this.messageAlert = 'No have Results Clubs';
          this.showAlert = true;
        }
    });
  }

  changeClub(value: string ): void{
    this.showSpinnerClub = true;
    this.clubService.getClub(value).subscribe(
      data => {
        this.clubSelected = data;

        if (this.clubSelected === null){
          this.messageAlert = 'No have Results Club';
          this.showAlert = true;
        }

        this.showSpinnerClub = false;
      });
  }

  changedPrivate(value: boolean): void {
    this.reservationDetails.private = value;
  }

  fillPlayers(users: User[]): void {
    this.userService.getPlayers().subscribe(data => {
      this.playerList = data;

      if (this.playerList.length === 0){
        this.messageAlert = 'No have Results players';
        this.showAlert = true;
      }

      if (users.length > 0){
        users.map(user => {
          this.playerList = this.playerList.filter(item => item.id !== user.id);
        });
      }
    });
  }

  removePlayer(idPlayer: string, namePlayer: string): void {
    this.reservationDetails.userList = this.reservationDetails.userList.filter(item => item.id !== idPlayer);

    const player = new Player();
    player.id = idPlayer;
    player.name = namePlayer;

    this.playerList.push(player);
  }

  changedPlayer(event: Event): void{
    const selectedOptions = event.target['options'];
    const selectedIndex = selectedOptions.selectedIndex;
    const selectElementText = selectedOptions[selectedIndex].text;
    const selectElementValue = selectedOptions[selectedIndex].value;

    this.playerIdSelected = selectElementValue;
    this.playerNameSelected = selectElementText;
  }

  addPlayer(): void {
    if (this.playerIdSelected !== '') {

      const user = new User();
      user.id = this.playerIdSelected;
      user.name = this.playerNameSelected;

      this.reservationDetails.userList.push(user);
      this.playerList = this.playerList.filter(item => item.id !== this.playerIdSelected);
      this.playerIdSelected = '';
      this.playerNameSelected = '';
    }
  }

  fillGroups(groups: Group[]): void {
    this.groupService.getGroups().subscribe(data => {
      this.groupList = data;

      if (this.groupList.length === 0){
        this.messageAlert = 'No have Results groups';
        this.showAlert = true;
      }

      if (groups.length > 0){
        groups.map(group => {
          this.groupList = this.groupList.filter(item => item.id !== group.id);
        });
      }
    });
  }

  removeGroup(idGroup: string, nameGroup: string): void {
    this.reservationDetails.groupList = this.reservationDetails.groupList.filter(item => item.id !== idGroup);

    const group = new Group();
    group.id = idGroup;
    group.name = nameGroup;

    this.groupList.push(group);
  }

  changedGroup(event: Event): void{
    const selectedOptions = event.target['options'];
    const selectedIndex = selectedOptions.selectedIndex;
    const selectElementText = selectedOptions[selectedIndex].text;
    const selectElementValue = selectedOptions[selectedIndex].value;

    this.groupIdSelected = selectElementValue;
    this.groupNameSelected = selectElementText;
  }

  changeAmount(event: number): void{
    this.reservationDetails.amount = event;
  }

  changeStatus(value: string): void {
    this.reservationDetails.status = value;
  }
  addGroup(): void {
    if (this.groupIdSelected !== '') {
      const group = new Group();
      group.id = this.groupIdSelected;
      group.name = this.groupNameSelected;

      this.reservationDetails.groupList.push(group);

      this.groupList = this.groupList.filter(item => item.id !== this.groupIdSelected);
      this.groupIdSelected = '';
      this.groupNameSelected = '';
    }
  }

  transformDate(date: NgbDateStruct ): string{
    return this.padLeft(date.day.toString(), '0', 2) + '-' + this.padLeft(date.month.toString(), '0', 2)  + '-' + date.year;
  }

  padLeft(text: string, padChar: string, size: number): string {
    return (String(padChar).repeat(size) + text).substr( (size * -1), size) ;
  }

  submitForm(): void{
    this.showButtonSpinner = true;
    if (this.checkValidations()){

      const userList = [];

      this.reservationDetails.userList.map(user => {
          userList.push(user.id);
      });

      const groupList = [];

      this.reservationDetails.groupList.map(group => {
        groupList.push(group.id);
      });

      const newReservation = new CreateReservationDto(this.clubSelected.id, this.reservationDetails.amount,
                this.transformDate(this.dateReservation) + ' ' + this.hourReservation.hour + ':' + this.hourReservation.minute,
                this.reservationDetails.private, this.reservationDetails.status, userList, groupList);

      if (this.reservationId != null){
            this.reservationService.editReservation(this.reservationId, newReservation).subscribe(
              data => {
                this.router.navigate(['/reservations']);
              },
              error => {
                console.log(error);
                this.messageAlert = 'It was not possible to connect to the server or have error. ';
                this.showAlert = true;
                this.showButtonSpinner = false;
              }
            );
          } else {
            this.reservationService.createReservation(newReservation).subscribe(
              data => {
                this.router.navigate(['/reservations']);
              },
              error => {
                console.log(error);
                this.messageAlert = 'It was not possible to connect to the server or have error. ';
                this.showAlert = true;
                this.showButtonSpinner = false;
              }
            );
          }
    }

    this.showButtonSpinner = false;
  }

  checkValidations(): boolean{
    if (this.dateReservation === null){
      this.showAlert = true;

      this.messageAlert = 'Date Reservation is empty.';
      return false;
    }

    if (this.hourReservation === null){
      this.showAlert = true;

      this.messageAlert = 'Hour Reservation is empty.';
      return false;
    }

    if (this.clubSelected === null || this.clubSelected === undefined){
      this.showAlert = true;

      this.messageAlert = 'Club not selected.';
      return false;
    }

    if (this.reservationDetails.amount === null){
      this.showAlert = true;

      this.messageAlert = 'Amount not inserted.';
      return false;
    }

    if (this.reservationDetails.status === ''){
      this.showAlert = true;

      this.messageAlert = 'Status not selected.';
      return false;
    }

    return true;
  }
}
