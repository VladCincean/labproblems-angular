import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemNewComponent } from './problem-new.component';

describe('ProblemNewComponent', () => {
  let component: ProblemNewComponent;
  let fixture: ComponentFixture<ProblemNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProblemNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProblemNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
