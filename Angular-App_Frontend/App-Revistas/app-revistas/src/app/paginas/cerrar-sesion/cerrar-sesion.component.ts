import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cerrar-sesion',
  standalone: true,
  imports: [],
  templateUrl: './cerrar-sesion.component.html',
  styleUrl: './cerrar-sesion.component.css'
})
export class CerrarSesionComponent {

  constructor(private router: Router) { }

  cerrarSesion() {
    localStorage.removeItem('token'); // Elimina el token de autenticación
    this.router.navigate(['/login']); // Redirige al login
  }

}
