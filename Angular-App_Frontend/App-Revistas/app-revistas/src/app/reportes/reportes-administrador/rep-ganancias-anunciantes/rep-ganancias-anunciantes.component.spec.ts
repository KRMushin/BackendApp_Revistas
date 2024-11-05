import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepGananciasAnunciantesComponent } from './rep-ganancias-anunciantes.component';

describe('RepGananciasAnunciantesComponent', () => {
  let component: RepGananciasAnunciantesComponent;
  let fixture: ComponentFixture<RepGananciasAnunciantesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepGananciasAnunciantesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepGananciasAnunciantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
