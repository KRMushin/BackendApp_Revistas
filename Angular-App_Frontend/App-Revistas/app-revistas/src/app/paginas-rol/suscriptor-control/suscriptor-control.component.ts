import { Component, OnInit } from '@angular/core';
import { CerrarSesionComponent } from '../../paginas/cerrar-sesion/cerrar-sesion.component';
import { NavigationEnd, Router, RouterLink, RouterOutlet } from '@angular/router';
import { BarraLateralAnuncioComponent } from '../../paginas/barra-lateral-anuncio/barra-lateral-anuncio.component';
import { filter } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-suscriptor-control',
  standalone: true,
  imports: [CerrarSesionComponent, RouterLink, BarraLateralAnuncioComponent, RouterOutlet, CommonModule],
  templateUrl: './suscriptor-control.component.html',
  styleUrl: './suscriptor-control.component.css'
})
export class SuscriptorControlComponent implements OnInit{
  showWelcomeContent: boolean = true;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.events.pipe(
      filter((event: any) => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.showWelcomeContent = event.url === '/';
    });
  }

}
