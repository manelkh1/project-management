import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerInvitationListComponent } from './invitation-list.component';

describe('ManagerInvitationListComponent', () => {
  let component: ManagerInvitationListComponent;
  let fixture: ComponentFixture<ManagerInvitationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ManagerInvitationListComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerInvitationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
