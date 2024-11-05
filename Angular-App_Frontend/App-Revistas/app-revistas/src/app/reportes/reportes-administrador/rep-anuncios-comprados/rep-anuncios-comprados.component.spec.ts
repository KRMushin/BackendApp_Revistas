import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepAnunciosCompradosComponent } from './rep-anuncios-comprados.component';

describe('RepAnunciosCompradosComponent', () => {
  let component: RepAnunciosCompradosComponent;
  let fixture: ComponentFixture<RepAnunciosCompradosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepAnunciosCompradosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepAnunciosCompradosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
