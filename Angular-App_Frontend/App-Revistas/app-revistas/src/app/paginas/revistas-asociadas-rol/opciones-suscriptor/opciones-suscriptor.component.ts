import { Component, inject, Input } from '@angular/core';
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';
import { CargarPdfRevistaService } from '../../../vistas-archivos/cargar-pdf-revista.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-opciones-suscriptor',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './opciones-suscriptor.component.html',
  styleUrl: './opciones-suscriptor.component.css'
})
export class OpcionesSuscriptorComponent {
alertComentario() {
  alert('El autor ha desactivado comentarios..');
}
  @Input() revista: any;
  private router = inject(Router); 

  constructor(private cargarPdf: CargarPdfRevistaService,
    private controladorAnuncios:ControladorAnunciosService
  ) { }

    comentarRevista(idRevista: number, tituloRevista: string) : void {
      this.controladorAnuncios.bloquearAnuncios(); // esto para que el bloqueo lo decida la logica de negocio
      this.router.navigate(['/suscriptor-control/misSuscripciones/comentarRevista', idRevista, tituloRevista]);
    }
      
    verNumerosRevista(idRevista: number) : void {
      this.controladorAnuncios.bloquearAnuncios();
      this.router.navigate(['/editor-control/misPublicaciones/comprarBloqueoAnuncios', idRevista]);
    }

    calificarRevista(idRevista: number, tituloRevista: string) : void {
      this.controladorAnuncios.bloquearAnuncios();
      this.router.navigate(['/editor-control/misPublicaciones/publicarNumero', idRevista, tituloRevista]);
    }
}
