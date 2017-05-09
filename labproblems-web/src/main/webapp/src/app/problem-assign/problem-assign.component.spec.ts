import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemAssignComponent } from './problem-assign.component';

describe('ProblemAssignComponent', () => {
  let component: ProblemAssignComponent;
  let fixture: ComponentFixture<ProblemAssignComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProblemAssignComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProblemAssignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
