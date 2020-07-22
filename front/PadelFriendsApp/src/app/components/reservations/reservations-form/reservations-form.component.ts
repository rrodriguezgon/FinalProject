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
  hourReservation: {hour: number, minute: number};
  isprivateReservation: boolean;
  clubIdReservation: string;

  pronvices: ['Barcelona', 'Gerona', 'Madrid'];
  cities: ['Figueres', 'Pozuelo de Alarcón', 'Alcorcon'];
  clubs: Club[];
  clubSelected: Club;

  playerList: Player[] = [];

  playerIdSelected = '';
  playerNameSelected = '';

  groupList: Group[] = [];

  groupIdSelected = '';
  groupNameSelected = '';

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
      this.route.params.subscribe((params) => (this.reservationId = params.id));

      if (this.reservationId != null){
        this.reservationService.getReservation(this.reservationId).subscribe(
          data => {
            this.fillClubs();
            this.changeClub(data.club.id);
            this.fillPlayers(data.userList);
            this.fillGroups(data.groupList);

            this.dateReservation = new NgbDate(2020, 19, 2);
            /*
            this.dateReservation = new NgbDate(Number(data.date.toString().substr(8, 2)),
                              Number(data.date.toString().substr(5, 2)),
                              Number(data.date.toString().substr(0, 4)));
            */
            console.log(this.dateReservation);
            /*this.hourReservation.hour = Number(data.date.toString().substr(11,2));
            this.hourReservation.minute = Number(data.date.toString().substr(14,2));*/
            this.reservationDetails = data;
          }
        );
      } else {
        this.fillClubs();
        this.fillPlayers(null);
        this.fillGroups(null);
        this.reservationDetails = new ReservationItemViewModel();
      }
    }
  }

  selectToday(): void {
    this.dateReservation = new NgbDate(2020, 19, 2);
  }

  fillClubs(): void{
    this.clubService.getClubs().subscribe(
      data => {
        this.clubs = data;
    });
  }

  changeClub(value: string ): void{
    this.clubService.getClub(value).subscribe(
      data => {
        this.clubSelected = data;
        console.log(this.clubSelected);
      });
  }

  fillPlayers(users: User[]): void {
    this.userService.getPlayers().subscribe(data => {
      this.playerList = data;

      users.map(user => {
        this.playerList = this.playerList.filter(item => item.id !== user.id);
      });
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

      groups.map(group => {
        this.groupList = this.groupList.filter(item => item.id !== group.id);
      });
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
    if (this.dateReservation != null &&
      this.hourReservation != null &&
      this.clubSelected != null &&
      this.reservationDetails.amount != null){

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
              }
            );
          } else {
            this.reservationService.createReservation(newReservation).subscribe(
              data => {
                this.router.navigate(['/reservations']);
              },
              error => {
                console.log(error);
              }
            );
          }
    }
  }
}
