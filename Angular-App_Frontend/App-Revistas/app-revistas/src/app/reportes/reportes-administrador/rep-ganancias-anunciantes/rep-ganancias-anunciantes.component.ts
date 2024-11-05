import { Component, Input, SimpleChanges } from '@angular/core';
import { FiltroAdminDTO } from '../FiltroAdminDTO';
import { ReportesAdminService } from '../../../../service/Reportes/ReportesAdminService.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

export interface GananciaAnuncio {
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
  selector: 'app-rep-ganancias-anunciantes',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './rep-ganancias-anunciantes.component.html',
  styleUrl: './rep-ganancias-anunciantes.component.css'
})
export class RepGananciasAnunciantesComponent {
  @Input() filtro!: FiltroAdminDTO;
  datosReporte: GananciaAnuncio[] = [];
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

    this.reportesService.obtenerGananciasAnunciantes(this.filtro).subscribe({
      next: (data: GananciaAnuncio[]) => {
        this.datosReporte = data;
        this.sinDatos = this.datosReporte.length === 0;
      },
      error: (error) => {
        console.error("Error al cargar el reporte de ganancias de anunciantes:", error);
        this.sinDatos = true;
      },
      complete: () => {
        this.cargando = false;
      }
    });
  }

}
