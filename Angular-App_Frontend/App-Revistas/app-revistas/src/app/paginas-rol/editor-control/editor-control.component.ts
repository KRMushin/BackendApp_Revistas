import { Component, OnInit } from '@angular/core';
import { CerrarSesionComponent } from '../../paginas/cerrar-sesion/cerrar-sesion.component';
import { NavigationEnd, Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { BarraLateralAnuncioComponent } from '../../paginas/barra-lateral-anuncio/barra-lateral-anuncio.component';
import { ControladorAnunciosService } from '../../../service/Anuncios/controlador-anuncios.service';
import { filter } from 'rxjs';
import { ModalComponentComponent } from '../../modal-component/modal-component.component';

@Component({
  selector: 'app-editor-control',
  standalone: true,
  imports: [CerrarSesionComponent, RouterLink, RouterLinkActive, RouterOutlet, BarraLateralAnuncioComponent, ModalComponentComponent],
  templateUrl: './editor-control.component.html',
  styleUrl: './editor-control.component.css'
})
export class EditorControlComponent{

  constructor(private controladorAnuncios: ControladorAnunciosService,
              private router: Router
  ) { }
  // metodo para recargar las barras de navegacion de las barras 
  
  // ngOnInit(): void {

  //   this.router.events.pipe(
  //     filter(event => event instanceof NavigationEnd) // cuando se cambia de componente
    
  //   ).subscribe(() => {
  //     console.log('Recargando anuncios');
  //     this.controladorAnuncios.recargarAnuncios();
  //   });

  // }
  

}
