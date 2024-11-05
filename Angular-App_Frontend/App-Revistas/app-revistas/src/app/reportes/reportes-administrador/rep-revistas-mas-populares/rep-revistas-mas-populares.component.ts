import { Component, Input, SimpleChanges } from '@angular/core';
import { FiltroAdminDTO } from '../FiltroAdminDTO';
import { ReportesAdminService } from '../../../../service/Reportes/ReportesAdminService.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

export interface SuscripcionReporte {
  idRevista: number | null;
  nombreAutor: string;
  tituloRevista: string;
  suscripciones: {  // Cambiamos suscripcionesRevista a suscripciones como un array
    idSuscripcion: number | null;
    nombreUsuario: string | null;
    idRevista: number;
    fechaSuscripcion: Date;
    calificoRevista: boolean;
  }[];
}


@Component({
  selector: 'app-rep-revistas-mas-populares',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './rep-revistas-mas-populares.component.html',
  styleUrl: './rep-revistas-mas-populares.component.css'
})
export class RepRevistasMasPopularesComponent {
  @Input() filtro!: FiltroAdminDTO;
  datosReporte: SuscripcionReporte[] = [];
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

    this.reportesService.obtenerReporteSuscripciones(this.filtro).subscribe({
      next: (data: SuscripcionReporte[]) => {
        this.datosReporte = this.procesarDatos(data);
        this.sinDatos = this.datosReporte.length === 0;
      },
      error: (error) => {
        console.error("Error al cargar el reporte de suscripciones:", error);
        this.sinDatos = true;
      },
      complete: () => {
        this.cargando = false;
      }
    });
  }

  procesarDatos(data: any[]): any[] {
    const groupedData = data.reduce((acc, item) => {
      const tituloRevista = item.tituloRevista;
      if (!acc[tituloRevista]) {
        acc[tituloRevista] = {
          nombreAutor: item.nombreAutor,
          tituloRevista: tituloRevista,
          suscripciones: [] // La propiedad aquí es `suscripciones`
        };
      }
  
      acc[tituloRevista].suscripciones.push({
        idSuscripcion: item.suscripcionesRevista.idSuscripcion || null,
        nombreUsuario: item.suscripcionesRevista.nombreUsuario || 'Anónimo',
        fechaSuscripcion: new Date(
          item.suscripcionesRevista.fechaSuscripcion[0],
          item.suscripcionesRevista.fechaSuscripcion[1] - 1,
          item.suscripcionesRevista.fechaSuscripcion[2]
        ),
        calificoRevista: item.suscripcionesRevista.calificoRevista
      });
      return acc;
    }, {});
  
    return Object.values(groupedData);
  }
  
  
  
}
