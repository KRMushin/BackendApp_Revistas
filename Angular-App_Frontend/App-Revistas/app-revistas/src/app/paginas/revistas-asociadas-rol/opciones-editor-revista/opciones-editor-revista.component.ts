import { Component, inject, Input } from '@angular/core';
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';
import { CargarPdfRevistaService } from '../../../vistas-archivos/cargar-pdf-revista.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-opciones-editor-revista',
  standalone: true,
  imports: [],
  templateUrl: './opciones-editor-revista.component.html',
  styleUrl: './opciones-editor-revista.component.css'
})
export class OpcionesEditorRevistaComponent {
  @Input() revista: any;
  private router = inject(Router); 

  constructor(private cargarPdf: CargarPdfRevistaService,
    private controladorAnuncios:ControladorAnunciosService
  ) { }

    detallesRevista(idRevista: number) : void {
      this.controladorAnuncios.bloquearAnuncios(); // esto para que el bloqueo lo decida la logica de negocio
      this.router.navigate(['/editor-control/misPublicaciones/detallesRevista', idRevista]);
    }
      
    comprarBloqueoAnuncios(idRevista: number) : void {
      this.controladorAnuncios.bloquearAnuncios();
      this.router.navigate(['/editor-control/misPublicaciones/comprarBloqueoAnuncios', idRevista]);
    }

    publicarNumeroRevista(idRevista: number, tituloRevista: string) : void {
      this.controladorAnuncios.bloquearAnuncios();
      this.router.navigate(['/editor-control/misPublicaciones/publicarNumero', idRevista, tituloRevista]);
    }
}
