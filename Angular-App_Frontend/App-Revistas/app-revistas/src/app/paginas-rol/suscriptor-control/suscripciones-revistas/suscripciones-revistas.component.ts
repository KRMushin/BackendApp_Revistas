import { Component, inject } from '@angular/core';
import { RevistasService } from '../../../../service/Revistas/revistas-service.service';
import { utileriaToken } from '../../../../service/utileria-token.service';
import { LlaveRevista } from '../../../../interfaces/Revistas/LlaveRevista';
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';
import { SuscripcionesService } from '../../../../service/Suscripciones/SuscripcionesService';
import { RevistasAsociadasRolComponent } from '../../../paginas/revistas-asociadas-rol/revistas-asociadas-rol.component';

@Component({
  selector: 'app-suscripciones-revistas',
  standalone: true,
  imports: [RevistasAsociadasRolComponent],
  templateUrl: './suscripciones-revistas.component.html',
  styleUrl: './suscripciones-revistas.component.css'
})
export class SuscripcionesRevistasComponent {

  private utileriaToken = inject(utileriaToken);

  public llavesRevistas: LlaveRevista[] = [];

  constructor(private controladorAnuncios: ControladorAnunciosService,
              private suscripcionesService: SuscripcionesService,
  ) {}
  ngOnInit(): void {

    const nombreUsuario = this.utileriaToken.obtenerNombreUsuario();
    if (nombreUsuario) {
      this.suscripcionesService.obtenerRevistasSuscriptor(nombreUsuario).subscribe({
        next: (llavesRevistas: LlaveRevista[]) => this.llavesRevistas = llavesRevistas,
        error: (error) => console.error('Error fetching anuncios:', error)
      });
    } else {
      console.error('Nombre de usuario es null');
    }
  }

  ngOnDestroy(): void {
    this.controladorAnuncios.recargarAnuncios();
  }
}
