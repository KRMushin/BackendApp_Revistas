import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepEfectividadAnunciantesComponent } from './rep-efectividad-anunciantes.component';

describe('RepEfectividadAnunciantesComponent', () => {
  let component: RepEfectividadAnunciantesComponent;
  let fixture: ComponentFixture<RepEfectividadAnunciantesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepEfectividadAnunciantesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepEfectividadAnunciantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
