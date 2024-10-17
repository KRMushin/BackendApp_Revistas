import { Component } from '@angular/core';
import { CerrarSesionComponent } from '../../paginas/cerrar-sesion/cerrar-sesion.component';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-editor-control',
  standalone: true,
  imports: [CerrarSesionComponent, RouterLink, RouterLinkActive, RouterOutlet],
  templateUrl: './editor-control.component.html',
  styleUrl: './editor-control.component.css'
})
export class EditorControlComponent {

}
