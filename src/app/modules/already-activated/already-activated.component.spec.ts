import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlreadyActivatedComponent } from './already-activated.component';

describe('AlreadyActivatedComponent', () => {
  let component: AlreadyActivatedComponent;
  let fixture: ComponentFixture<AlreadyActivatedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlreadyActivatedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AlreadyActivatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
