import { Component, Input, SimpleChanges } from '@angular/core';
import { FiltroEditorDTO } from '../../FiltroEditorDTO';
import { ReportesEditorService } from '../../../../service/Reportes/ReportesEditorService.service';
import { CommonModule } from '@angular/common';

interface PagoProcesado {
  idRevista: number;
  nombreAutor: string;
  tituloRevista: string;
  totalPagos: number;
  fechaCompra: Date;
  diasCompra: number;
  nombreUsuario: string;
  costoTotal: number;
  estadoCompraDescripcion: string;
  

}
@Component({
  selector: 'app-rep-pagos-revistas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rep-pagos-revistas.component.html',
  styleUrl: './rep-pagos-revistas.component.css'
})
export class RepPagosRevistasComponent {
  @Input() filtro!: FiltroEditorDTO;
  datosReporte: PagoProcesado[] = [];
  cargando: boolean = false;
  sinDatos: boolean = false;

  constructor(private reportesService: ReportesEditorService) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['filtro'] && this.filtro) {
      console.log("Filtro aplicado en Pagos:", this.filtro);
      this.cargarDatos();
    }
  }

  cargarDatos() {
    this.datosReporte = [];
    this.cargando = true;
    this.sinDatos = false;

    this.reportesService.obtenerReportePagos(this.filtro).subscribe({
      next: (data) => {
        this.datosReporte = this.procesarPagos(data);
        console.log("Datos del Reporte de Pagos Procesados:", this.datosReporte);
      },
      error: (error) => {
        console.error("Error al cargar el reporte de pagos:", error);
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

  procesarPagos(data: any[]): PagoProcesado[] {
    return data.map(item => {
      const compra = item.compra || {};
      
      // Accede directamente a `item.estadoCompra`
      return {
        idRevista: compra.idRevista ?? 0,
        nombreAutor: item.nombreAutor || 'Desconocido',
        tituloRevista: item.tituloRevista || 'Sin título',
        totalPagos: item.totalPagos ?? 0,
        fechaCompra: compra.fechaCompra
          ? new Date(
              compra.fechaCompra[0], 
              compra.fechaCompra[1] - 1, 
              compra.fechaCompra[2]
            )
          : new Date(),
        diasCompra: compra.diasCompra ?? 0,
        nombreUsuario: compra.nombreUsuario || 'Anónimo',
        costoTotal: compra.costoTotal ?? 0,
        estadoCompraDescripcion: item.estadoCompra === false ? 'BLOQUEO VENCIDO' : 'BLOQUEO ACTIVO'
      };
    });
  }
  
  

}
