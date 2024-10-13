import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { CerrarSesionComponent } from '../../paginas/cerrar-sesion/cerrar-sesion.component';

@Component({
  selector: 'app-admin-control',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, CerrarSesionComponent],
  templateUrl: './admin-control.component.html',
  styleUrl: './admin-control.component.css'
})
export class AdminControlComponent {

}
