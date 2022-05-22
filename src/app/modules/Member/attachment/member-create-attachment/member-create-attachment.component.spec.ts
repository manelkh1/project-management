import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberCreateAttachmentComponent } from './member-create-attachment.component';

describe('MemberCreateAttachmentComponent', () => {
  let component: MemberCreateAttachmentComponent;
  let fixture: ComponentFixture<MemberCreateAttachmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MemberCreateAttachmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberCreateAttachmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
