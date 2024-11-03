import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalificarSuscripcionComponent } from './calificar-suscripcion.component';

describe('CalificarSuscripcionComponent', () => {
  let component: CalificarSuscripcionComponent;
  let fixture: ComponentFixture<CalificarSuscripcionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CalificarSuscripcionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CalificarSuscripcionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
