import { Injectable } from '@angular/core';
import { RevistasService } from '../../service/Revistas/revistas-service.service';

@Injectable({
  providedIn: 'root'
})
export class CargarPdfRevistaService {

  constructor(private revistaService: RevistasService) { }

  cargarPDFEnOtraVentana(id: number) {
    this.revistaService.obtenerPdfRevista(id).subscribe({
      next: (pdfBlob: Blob) => {
        const url = URL.createObjectURL(pdfBlob);
        window.open(url);  // Abrir el PDF en una nueva pestaña
      },
      error: (error) => {
        console.error('Error al cargar el PDF:', error);
      }
    });
  }
}
