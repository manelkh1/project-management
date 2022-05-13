import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessefullyActivatedComponent } from './successefully-activated.component';

describe('SuccessefullyActivatedComponent', () => {
  let component: SuccessefullyActivatedComponent;
  let fixture: ComponentFixture<SuccessefullyActivatedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccessefullyActivatedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessefullyActivatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
