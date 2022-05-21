import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerCreateAttachmentComponent } from './create-attachment.component';

describe('ManagerCreateAttachmentComponent', () => {
  let component: ManagerCreateAttachmentComponent;
  let fixture: ComponentFixture<ManagerCreateAttachmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ManagerCreateAttachmentComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerCreateAttachmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
