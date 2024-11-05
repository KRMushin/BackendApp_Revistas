import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepRevistasMasPopularesComponent } from './rep-revistas-mas-populares.component';

describe('RepRevistasMasPopularesComponent', () => {
  let component: RepRevistasMasPopularesComponent;
  let fixture: ComponentFixture<RepRevistasMasPopularesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepRevistasMasPopularesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepRevistasMasPopularesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
