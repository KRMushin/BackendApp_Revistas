import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FiltroEditorDTO } from '../../FiltroEditorDTO';
import { ReportesEditorService } from '../../../../service/Reportes/ReportesEditorService.service';
import { CommonModule } from '@angular/common';
interface Comentario {
  idComentario: number;
  nombreUsuario: string;
  fechaComentario: Date;
  comentario: string;
}

interface RevistaConComentarios {
  idRevista: number;
  tituloRevista: string;
  comentarios: Comentario[];
}
@Component({
  selector: 'app-rep-comentarios-revistas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rep-comentarios-revistas.component.html',
  styleUrl: './rep-comentarios-revistas.component.css'
})
export class RepComentariosRevistasComponent implements OnChanges{

  @Input() filtro!: FiltroEditorDTO; // Recibe el filtro aplicado del componente principal
  datosReporte: RevistaConComentarios[] = [];
    sinDatos: boolean = false; // Nueva bandera para indicar que no hay datos

  constructor(private reportesService: ReportesEditorService) {}
  
  ngOnChanges(changes: SimpleChanges) {
    if (changes['filtro'] && this.filtro) {
      console.log("Filtro aplicado en Comentarios:", this.filtro);
      this.cargarDatos();
    }
  }

  cargarDatos() {
    this.datosReporte = [];
    this.sinDatos = false;
    this.reportesService.obtenerReporteComentarios(this.filtro).subscribe({
      next: (data) => {
        this.datosReporte = this.procesarDatos(data);
        console.log("Datos del Reporte de Comentarios Procesados:", this.datosReporte);
      },
      error: (error) => {
        console.error("Error al cargar los comentarios:", error);
        this.datosReporte = [];
      },
      complete: () => {
        if (this.datosReporte.length === 0) {
          this.sinDatos = true; // Activa la bandera si no hay datos
        }
      }
    });
  }

  procesarDatos(data: any[]): RevistaConComentarios[] {
    const agrupados: { [key: number]: RevistaConComentarios } = {};
  
    data.forEach(item => {
      const comentarioData = item.comentariosRevista;
      const idRevista = item.idRevista ?? 0;
      const tituloRevista = item.tituloRevista || comentarioData.tituloRevista || 'Sin título';
  
      const comentario: Comentario = {
        idComentario: comentarioData.idComentario ?? 0,
        nombreUsuario: comentarioData.nombreUsuario || 'Anónimo',
        fechaComentario: comentarioData.fechaComentario
          ? new Date(
              comentarioData.fechaComentario[0],
              comentarioData.fechaComentario[1] - 1,
              comentarioData.fechaComentario[2]
            )
          : new Date(),
        comentario: comentarioData.comentario || 'Sin comentario'
      };
  
      // Agrupar los comentarios bajo cada revista
      if (agrupados[idRevista]) {
        agrupados[idRevista].comentarios.push(comentario);
      } else {
        agrupados[idRevista] = {
          idRevista,
          tituloRevista,
          comentarios: [comentario]
        };
      }
    });
  
    return Object.values(agrupados);
  }
  
  
}
