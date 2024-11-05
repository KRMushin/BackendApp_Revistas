import { Component, Input, SimpleChanges } from '@angular/core';
import { FiltroEditorDTO } from '../../FiltroEditorDTO';
import { ReportesEditorService } from '../../../../service/Reportes/ReportesEditorService.service';
import { CommonModule } from '@angular/common';
interface RevistaLikeProcesada {
  idRevista: number;
  nombreAutor: string;
  tituloRevista: string;
  totalLikes: number;
  nombreUsuario: string;
  fechaLike: Date;
  likeHecho: boolean;
}
@Component({
  selector: 'app-rep-masgustadas-revistas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rep-masgustadas-revistas.component.html',
  styleUrl: './rep-masgustadas-revistas.component.css'
})
export class RepMasgustadasRevistasComponent {
  @Input() filtro!: FiltroEditorDTO;
  datosReporte: RevistaLikeProcesada[] = [];
  cargando: boolean = false;
  sinDatos: boolean = false;

  constructor(private reportesService: ReportesEditorService) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['filtro'] && this.filtro) {
      console.log("Filtro aplicado en Likes:", this.filtro);
      this.cargarDatos();
    }
  }

  cargarDatos() {
    this.datosReporte = [];
    this.cargando = true;
    this.sinDatos = false;

    this.reportesService.obtenerReporteLikes(this.filtro).subscribe({
      next: (data) => {
        this.datosReporte = this.procesarRevistasMasGustadas(data);
        console.log("Datos del Reporte de Revistas Más Gustadas Procesados:", this.datosReporte);
      },
      error: (error) => {
        console.error("Error al cargar el reporte de likes:", error);
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

  procesarRevistasMasGustadas(data: any[]): RevistaLikeProcesada[] {
    return data.map(item => {
      const like = item.like || {};
      return {
        idRevista: item.idRevista ?? 0,
        nombreAutor: item.nombreAutor || 'Desconocido',
        tituloRevista: item.tituloRevista || 'Sin título',
        totalLikes: item.totalLikes ?? 0,
        nombreUsuario: like.nombreUsuario || 'Anónimo',
        fechaLike: like.fechaLike
          ? new Date(
              like.fechaLike[0], 
              like.fechaLike[1] - 1, 
              like.fechaLike[2]
            )
          : new Date(),
        likeHecho: like.likeHecho ?? false
      };
    });
  }

}
