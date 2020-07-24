import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClubsDetailsComponent } from './clubs-details.component';

describe('ClubsDetailsComponent', () => {
  let component: ClubsDetailsComponent;
  let fixture: ComponentFixture<ClubsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClubsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClubsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
