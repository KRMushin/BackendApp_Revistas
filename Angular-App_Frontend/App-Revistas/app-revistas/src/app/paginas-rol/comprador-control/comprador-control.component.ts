import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { CerrarSesionComponent } from '../../paginas/cerrar-sesion/cerrar-sesion.component';

@Component({
  selector: 'app-comprador-control',
  standalone: true,
  imports: [CerrarSesionComponent,RouterLink, RouterLinkActive , RouterOutlet],
  templateUrl: './comprador-control.component.html',
  styleUrl: './comprador-control.component.css'
})
export class CompradorControlComponent {

}
