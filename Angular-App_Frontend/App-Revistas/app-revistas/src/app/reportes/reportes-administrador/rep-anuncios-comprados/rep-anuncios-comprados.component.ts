import { Component, Input, SimpleChanges } from '@angular/core';
import { FiltroAdminDTO } from '../FiltroAdminDTO';
import { ReportesAdminService } from '../../../../service/Reportes/ReportesAdminService.service';
import { CommonModule } from '@angular/common';

export interface AnuncioReporte {
  idAnuncio: number | null;
  nombreUsuario: string;
  fechaCompra: Date | null;
  tipoAnuncio: string;
  precioTotal: number;
  rutaImagenTexto: string | null;
  rutaVideo: string | null;
  rutaTexto: string | null;
  anuncioHabilitado: boolean;
  diasDuracion: number;
}

@Component({
  selector: 'app-rep-anuncios-comprados',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rep-anuncios-comprados.component.html',
  styleUrl: './rep-anuncios-comprados.component.css'
})
export class RepAnunciosCompradosComponent {
  @Input() filtro!: FiltroAdminDTO;
  datosReporte: AnuncioReporte[] = [];
  sinDatos: boolean = false;
  cargando: boolean = false;

  constructor(private reportesService: ReportesAdminService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['filtro'] && this.filtro) {
      this.cargarDatos();
    }
  }

  cargarDatos(): void {
    this.cargando = true;
    this.sinDatos = false;
    this.datosReporte = [];

    this.reportesService.obtenerReporteAnuncios(this.filtro).subscribe({
      next: (data: AnuncioReporte[]) => {
        this.datosReporte = data;
        this.sinDatos = this.datosReporte.length === 0;
      },
      error: (error) => {
        console.error("Error al cargar el reporte de anuncios:", error);
        this.sinDatos = true;
      },
      complete: () => {
        this.cargando = false;
      }
    });
  }

}
