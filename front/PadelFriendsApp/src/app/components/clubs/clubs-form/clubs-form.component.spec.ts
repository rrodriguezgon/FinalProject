import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClubsFormComponent } from './clubs-form.component';

describe('ClubsFormComponent', () => {
  let component: ClubsFormComponent;
  let fixture: ComponentFixture<ClubsFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClubsFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClubsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
