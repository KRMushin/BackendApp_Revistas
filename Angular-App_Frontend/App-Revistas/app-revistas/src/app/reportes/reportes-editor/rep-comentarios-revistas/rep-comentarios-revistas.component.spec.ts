import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepComentariosRevistasComponent } from './rep-comentarios-revistas.component';

describe('RepComentariosRevistasComponent', () => {
  let component: RepComentariosRevistasComponent;
  let fixture: ComponentFixture<RepComentariosRevistasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepComentariosRevistasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepComentariosRevistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
