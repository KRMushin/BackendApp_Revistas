import { Component, Input, SimpleChanges } from '@angular/core';
import { FiltroAdminDTO } from '../FiltroAdminDTO';
import { Anuncio } from '../../../../interfaces/Anuncios/Anuncio';
import { Revista } from '../../../../interfaces/Revistas/Revista';
import { CompraBloqueo } from '../../../../interfaces/Revistas/Compras/CompraBloqueo';
import { ReportesAdminService } from '../../../../service/Reportes/ReportesAdminService.service';
import { CommonModule } from '@angular/common';
import { ReporteGanancias } from '../../../../interfaces/Reportes/ReporteGanancias';
import { ExportarReportesService } from '../../../../service/Reportes/ExportarReportes.service';




@Component({
  selector: 'app-rep-ganancias-sistema',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rep-ganancias-sistema.component.html',
  styleUrl: './rep-ganancias-sistema.component.css'
})
export class RepGananciasSistemaComponent {
  @Input() filtro!: FiltroAdminDTO;
  reporteGanancias!: ReporteGanancias;
  cargando: boolean = false;
  sinDatos: boolean = false;

  constructor(private reportesEdService: ReportesAdminService,
    private exporta: ExportarReportesService
  ) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['filtro'] && this.filtro) {
      this.cargarDatos();
    }
  }

  cargarDatos() {
    this.cargando = true;
    this.reportesEdService.obtnerReporteGananciasSistema(this.filtro).subscribe({
      next: (reporte: ReporteGanancias) => {
        this.reporteGanancias = reporte;
        this.cargando = false;
        this.sinDatos = false;
      },
      error: (error) => {
        console.error('Error al obtener reporte de ganancias del sistema:', error);
        this.cargando = false;
        this.sinDatos = true;
      }
    });
  }

  
  exportarReporte(): void {
    if (!this.reporteGanancias) {
      console.warn("No hay datos disponibles para exportar.");
      return;
    }
  
    this.exporta.abrirReporteGananciasSistema(this.reporteGanancias);
  }
  
}
