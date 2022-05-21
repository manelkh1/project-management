import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarBankComponent } from './sidebar-bank.component';

describe('SidebarBankComponent', () => {
  let component: SidebarBankComponent;
  let fixture: ComponentFixture<SidebarBankComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SidebarBankComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarBankComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
