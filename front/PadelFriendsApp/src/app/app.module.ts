import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
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

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    IndexComponent,
    ClubsDetailsComponent,
    ClubsFormComponent,
    ClubsListComponent,
    GroupsDetailsComponent,
    GroupsFormComponent,
    GroupsListComponent,
    ReservationsDetailsComponent,
    ReservationsFormComponent,
    ReservationsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
