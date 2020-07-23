import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import es from '@angular/common/locales/es';
import { registerLocaleData } from '@angular/common';
registerLocaleData(es);


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

import { NgbModule, NgbTimepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { MapComponent } from './components/map/map.component';

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
    ReservationsListComponent,
    MapComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgbModule,
    NgbTimepickerModule
  ],
  providers: [
    { provide: LOCALE_ID, useValue: "es-ES" }, //your locale
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
