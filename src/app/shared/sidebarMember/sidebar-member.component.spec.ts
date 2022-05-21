import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarmemberComponent } from './sidebar-member.component';

describe('SidebarmemberComponent', () => {
  let component: SidebarmemberComponent;
  let fixture: ComponentFixture<SidebarmemberComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SidebarmemberComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarmemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
