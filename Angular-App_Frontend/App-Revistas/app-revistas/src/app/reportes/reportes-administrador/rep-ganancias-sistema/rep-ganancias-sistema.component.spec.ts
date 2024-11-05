import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepGananciasSistemaComponent } from './rep-ganancias-sistema.component';

describe('RepGananciasSistemaComponent', () => {
  let component: RepGananciasSistemaComponent;
  let fixture: ComponentFixture<RepGananciasSistemaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepGananciasSistemaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepGananciasSistemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
