import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerAutorComponent } from './ver-autor.component';

describe('VerAutorComponent', () => {
  let component: VerAutorComponent;
  let fixture: ComponentFixture<VerAutorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerAutorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerAutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
