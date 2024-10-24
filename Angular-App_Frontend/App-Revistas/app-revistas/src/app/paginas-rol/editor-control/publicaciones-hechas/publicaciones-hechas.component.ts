import { Component, inject, OnInit } from '@angular/core';
import { utileriaToken } from '../../../../service/utileria-token.service';
import { RevistasService } from '../../../../service/Revistas/revistas-service.service';
import { LlaveRevista } from '../../../../interfaces/Revistas/LlaveRevista';
import { RevistasAsociadasRolComponent } from "../../../paginas/revistas-asociadas-rol/revistas-asociadas-rol.component";
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';

@Component({
  selector: 'app-publicaciones-hechas',
  standalone: true,
  imports: [RevistasAsociadasRolComponent],
  templateUrl: './publicaciones-hechas.component.html',
  styleUrl: './publicaciones-hechas.component.css'
})
export class PublicacionesHechasComponent implements OnInit{
  
  private utileriaToken = inject(utileriaToken);
  private serviceRevistas = inject(RevistasService);

  public llavesRevistas: LlaveRevista[] = [];

  constructor(private controladorAnuncios: ControladorAnunciosService) {}
  ngOnInit(): void {

    const nombreUsuario = this.utileriaToken.obtenerNombreUsuario();
    if (nombreUsuario) {
      this.serviceRevistas.obtenerLlavesRevistasEditor(nombreUsuario).subscribe({
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
