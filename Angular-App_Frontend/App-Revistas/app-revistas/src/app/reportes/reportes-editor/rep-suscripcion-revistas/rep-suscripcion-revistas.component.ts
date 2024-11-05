import { Component, Input, SimpleChanges } from '@angular/core';
import { FiltroEditorDTO } from '../../FiltroEditorDTO';
import { ReportesEditorService } from '../../../../service/Reportes/ReportesEditorService.service';
import { CommonModule } from '@angular/common';
interface SuscripcionProcesada {
  idSuscripcion: number;
  nombreUsuario: string;
  idRevista: number | null;
  tituloRevista: string;
  nombreAutor: string;
  fechaSuscripcion: Date;
  calificoRevista: boolean;
}
@Component({
  selector: 'app-rep-suscripcion-revistas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rep-suscripcion-revistas.component.html',
  styleUrl: './rep-suscripcion-revistas.component.css'
})
export class RepSuscripcionRevistasComponent {
  @Input() filtro!: FiltroEditorDTO;
  datosReporte: SuscripcionProcesada[] = [];
  cargando: boolean = false;
  sinDatos: boolean = false;

  constructor(private reportesService: ReportesEditorService) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['filtro'] && this.filtro) {
      console.log("Filtro aplicado en Suscripciones:", this.filtro);
      this.cargarDatos();
    }
  }

  cargarDatos() {
    this.datosReporte = [];
    this.cargando = true;
    this.sinDatos = false;

    this.reportesService.obtenerReporteSuscripciones(this.filtro).subscribe({
      next: (data) => {
        this.datosReporte = this.procesarSuscripciones(data);
        console.log("Datos del Reporte de Suscripciones Procesados:", this.datosReporte);
      },
      error: (error) => {
        console.error("Error al cargar las suscripciones:", error);
        this.datosReporte = [];
      },
      complete: () => {
        this.cargando = false;
        if (this.datosReporte.length === 0) {
          this.sinDatos = true;
        }
      }
    });
  }

  procesarSuscripciones(data: any[]): SuscripcionProcesada[] {
    return data.map(item => {
      const suscripcion = item.suscripcionesRevista || {};
      return {
        idSuscripcion: suscripcion.idSuscripcion ?? 0,
        nombreUsuario: suscripcion.nombreUsuario || 'Anónimo',
        idRevista: suscripcion.idRevista ?? null,
        tituloRevista: item.tituloRevista || 'Sin título',
        nombreAutor: item.nombreAutor || 'Desconocido',
        fechaSuscripcion: suscripcion.fechaSuscripcion
          ? new Date(
              suscripcion.fechaSuscripcion[0], 
              suscripcion.fechaSuscripcion[1] - 1, 
              suscripcion.fechaSuscripcion[2]
            )
          : new Date(),
        calificoRevista: suscripcion.calificoRevista ?? false
      };
    });
  }
}
