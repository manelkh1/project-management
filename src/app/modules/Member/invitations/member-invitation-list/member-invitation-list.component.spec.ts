import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberInvitationListComponent } from './member-invitation-list.component';

describe('MemberInvitationListComponent', () => {
  let component: MemberInvitationListComponent;
  let fixture: ComponentFixture<MemberInvitationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MemberInvitationListComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberInvitationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
