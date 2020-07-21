import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { IndexComponent } from './components/index/index.component';

import { ClubsDetailsComponent } from './components/clubs/clubs-details/clubs-details.component';
import { ClubsFormComponent } from './components/clubs/clubs-form/clubs-form.component';
import { ClubsListComponent } from './components/clubs/clubs-list/clubs-list.component';

import { GroupsDetailsComponent } from './components/groups/groups-details/groups-details.component';
import { GroupsFormComponent } from './components/groups/groups-form/groups-form.component';
import { GroupsListComponent } from './components/groups/groups-list/groups-list.component';

import { ReservationsDetailsComponent } from './components/reservations/reservations-details/reservations-details.component';
import { ReservationsFormComponent } from './components/reservations/reservations-form/reservations-form.component';
import { ReservationsListComponent } from './components/reservations/reservations-list/reservations-list.component';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'reservations', component: ReservationsListComponent },
  { path: 'reservations/:id', component: ReservationsDetailsComponent },
  { path: 'createreservation', component: ReservationsFormComponent },
  { path: 'reservations/edit/:id', component: ReservationsFormComponent },
  { path: 'clubs', component: ClubsListComponent },
  { path: 'clubs/:id', component: ClubsDetailsComponent },
  { path: 'createclub', component: ClubsFormComponent },
  { path: 'clubs/edit/:id', component: ClubsFormComponent },
  { path: 'groups', component: GroupsListComponent },
  { path: 'groups/:id', component: GroupsDetailsComponent },
  { path: 'creategroup', component: GroupsFormComponent },
  { path: 'groups/edit/:id', component: GroupsFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
