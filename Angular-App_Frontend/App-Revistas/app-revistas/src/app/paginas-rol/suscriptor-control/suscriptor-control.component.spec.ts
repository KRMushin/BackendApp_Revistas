import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuscriptorControlComponent } from './suscriptor-control.component';

describe('SuscriptorControlComponent', () => {
  let component: SuscriptorControlComponent;
  let fixture: ComponentFixture<SuscriptorControlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuscriptorControlComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuscriptorControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
