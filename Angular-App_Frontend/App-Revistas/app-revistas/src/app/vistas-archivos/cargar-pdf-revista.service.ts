import { Injectable } from '@angular/core';
import { RevistasService } from '../../service/Revistas/revistas-service.service';
import { VerPdfComponent } from './ver-pdf/ver-pdf.component';
import { map, Observable } from 'rxjs';
import { NumeroRevistasService } from '../../service/numero-revistas.service';

@Injectable({
  providedIn: 'root'
})
export class CargarPdfRevistaService {
  

  constructor(private revistaService: RevistasService,
              private numerosService: NumeroRevistasService
  ) { }

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

  obtenerPDFRevista(idRevista: number): Observable<string> {
    return this.revistaService.obtenerPdfRevista(idRevista).pipe(
      map((pdfBlob: Blob) => URL.createObjectURL(pdfBlob))  
  )};

  obtenerPDFNumeroRevista(idNumeroRevista: number): Observable<string> { 
    return this.numerosService.verNumeroRevista(idNumeroRevista).pipe(
      map((pdfBlob: Blob) => URL.createObjectURL(pdfBlob))
    )
  }
}
