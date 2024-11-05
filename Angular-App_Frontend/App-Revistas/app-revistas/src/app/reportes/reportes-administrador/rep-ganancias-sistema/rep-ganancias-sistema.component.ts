import { Component, Input, SimpleChanges } from '@angular/core';
import { FiltroAdminDTO } from '../FiltroAdminDTO';
import { Anuncio } from '../../../../interfaces/Anuncios/Anuncio';
import { Revista } from '../../../../interfaces/Revistas/Revista';
import { CompraBloqueo } from '../../../../interfaces/Revistas/Compras/CompraBloqueo';
import { ReportesAdminService } from '../../../../service/Reportes/ReportesAdminService.service';
import { CommonModule } from '@angular/common';
interface CompraR {
  idRevista: number | null;
  fechaCompra: Date;
  diasCompra: number;
  nombreUsuario: string;
  costoTotal: number;
}

interface AnuncioR {
  idAnuncio: number | null;
  nombreUsuario: string;
  fechaCompra: Date;
  tipoAnuncio: string;
  precioTotal: number;
  anuncioHabilitado: boolean;
  diasDuracion: number;
}

interface RevistaR {
  idRevista: number | null;
  tituloRevista: string;
  nombreAutor: string;
  costoMantenimiento: number;
  fechaCreacion: Date;
  bloquearAnuncios: boolean;
}

interface ReporteGanancias {
  ingresosEditores: { totalIngresos: number; compras: CompraR[] };
  ingresosAnuncios: { totalIngresos: number; anuncios: AnuncioR[] };
  costosRevista: { totalCostosMantenimiento: number; revistas: RevistaR[] };
  totalIngresos: number;
  totalCostos: number;
  totalGanancia: number;
}

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

  constructor(private reportesEdService: ReportesAdminService) {}

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
}
