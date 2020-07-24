import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationsDetailsComponent } from './reservations-details.component';

describe('ReservationsDetailsComponent', () => {
  let component: ReservationsDetailsComponent;
  let fixture: ComponentFixture<ReservationsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReservationsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
