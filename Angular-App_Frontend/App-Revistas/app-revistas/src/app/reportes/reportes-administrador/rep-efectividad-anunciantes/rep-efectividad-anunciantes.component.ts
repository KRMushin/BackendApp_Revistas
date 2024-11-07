import { Component, Input, SimpleChanges } from '@angular/core';
import { FiltroAdminDTO } from '../FiltroAdminDTO';
import { ReportesAdminService } from '../../../../service/Reportes/ReportesAdminService.service';
import { CommonModule } from '@angular/common';
export interface EfectividadAnuncioReporte {
  idAnuncio: number;
  nombreUsuario: string;
  tipoAnuncio: string;
  rutaUrl: string;
  fechaVisualizacion: [number, number, number]; // Año, mes, día
  totalVisualizaciones: number;
}

@Component({
  selector: 'app-rep-efectividad-anunciantes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rep-efectividad-anunciantes.component.html',
  styleUrl: './rep-efectividad-anunciantes.component.css'
})
export class RepEfectividadAnunciantesComponent {
  @Input() filtro!: FiltroAdminDTO;
  datosReporte: any[] = [];
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

    this.reportesService.obtenerReporteEfectividad(this.filtro).subscribe({
      next: (data: EfectividadAnuncioReporte[]) => {
        this.datosReporte = this.procesarDatos(data);
        this.sinDatos = this.datosReporte.length === 0;
      },
      error: (error) => {
        console.error("Error al cargar el reporte de efectividad de anunciantes:", error);
        this.sinDatos = true;
      },
      complete: () => {
        this.cargando = false;
      }
    });
  }

  procesarDatos(data: EfectividadAnuncioReporte[]): any[] {
    const agrupados: any = {};
  
    data.forEach(item => {
      // Agrupar por nombreUsuario
      if (!agrupados[item.nombreUsuario]) {
        agrupados[item.nombreUsuario] = {
          nombreUsuario: item.nombreUsuario,
          anuncios: []
        };
      }
  
      const anunciante = agrupados[item.nombreUsuario];
  
      let anuncio = anunciante.anuncios.find((a: any) => 
        a.idAnuncio === item.idAnuncio && a.rutaUrl === item.rutaUrl
      );
  
      if (!anuncio) {
        anuncio = {
          idAnuncio: item.idAnuncio,
          rutaUrl: item.rutaUrl,
          tipoAnuncio: item.tipoAnuncio,
          totalVisualizaciones: 0,
          fechaVisualizacion: item.fechaVisualizacion.join('-') // Usamos la primera fecha encontrada
        };
        anunciante.anuncios.push(anuncio);
      }
  
      anuncio.totalVisualizaciones += item.totalVisualizaciones;
    });
  
    return Object.values(agrupados).map((anunciante: any) => ({
      nombreUsuario: anunciante.nombreUsuario,
      anuncios: anunciante.anuncios.map((anuncio: any) => ({
        idAnuncio: anuncio.idAnuncio,
        rutaUrl: anuncio.rutaUrl,
        tipoAnuncio: anuncio.tipoAnuncio,
        totalVisualizaciones: anuncio.totalVisualizaciones,
        fechaVisualizacion: anuncio.fechaVisualizacion // Solo una fecha
      }))
    }));
  }
  
  
  

  
  
  

}
