import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FiltroEditorDTO } from '../FiltroEditorDTO';
import { RepComentariosRevistasComponent } from "./rep-comentarios-revistas/rep-comentarios-revistas.component";
import { utileriaToken } from '../../../service/utileria-token.service';
import { RepMasgustadasRevistasComponent } from "./rep-masgustadas-revistas/rep-masgustadas-revistas.component";
import { RepPagosRevistasComponent } from "./rep-pagos-revistas/rep-pagos-revistas.component";
import { RepSuscripcionRevistasComponent } from "./rep-suscripcion-revistas/rep-suscripcion-revistas.component";
import { LlaveRevista } from '../../../interfaces/Revistas/LlaveRevista';
import { RevistasService } from '../../../service/Revistas/revistas-service.service';

@Component({
  selector: 'app-reportes-editor',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, RepComentariosRevistasComponent, RepMasgustadasRevistasComponent, RepPagosRevistasComponent, RepSuscripcionRevistasComponent],
  templateUrl: './reportes-editor.component.html',
  styleUrl: './reportes-editor.component.css'
})
export class ReportesEditorComponent implements OnInit{
  filtroForm: FormGroup; // fformulario reactivo para los filtros
  filtroAplicado!: FiltroEditorDTO; 
  reporteSeleccionado!: string; // tipo de reporte seleccionado
  llavesRevistas: LlaveRevista[] = []; // lista de revistas

  constructor(private fb: FormBuilder, private UtileriaToken: utileriaToken, 
              private serviceRevistas: RevistasService
  ) {
    this.filtroForm = this.fb.group({
      fechaInicio: [null],
      fechaFin: [null],
      idRevista: [null],
      nombreEditor: [null]
    });
  }
  ngOnInit(): void {
    const nombreUsuario = this.UtileriaToken.obtenerNombreUsuario();
    if (nombreUsuario) {
      this.serviceRevistas.obtenerLlavesRevistasEditor(nombreUsuario).subscribe({
        next: (llavesRevistas: LlaveRevista[]) => this.llavesRevistas = llavesRevistas,
        error: (error) => console.error('Error  anuncios:', error)
      });
    } else {
      console.error('Nombre de usuario es null');
    }
  
  }

  // Para aplicar los filtros y enviar al subcomponente
  aplicarFiltros() {
    this.filtroAplicado = {
      tipoReporte: this.reporteSeleccionado,
      fechaInicio: this.filtroForm.value.fechaInicio,
      fechaFin: this.filtroForm.value.fechaFin,
      idRevista: this.filtroForm.value.idRevista,
      nombreEditor: this.UtileriaToken.obtenerNombreUsuario(),
    };
    console.log("Filtros aplicados:", this.filtroAplicado);
  }
  mostrarFiltro(campo: string): boolean {
    switch (this.reporteSeleccionado) {
      case 'REVISTA_COMENTARIOS':
        return true; // mostrar todos los filtros para Reporte1 
      case 'REVISTA_SUSCRIPCIONES':
          return true; // mostrar todos los filtros para Reporte1 
      case 'REVISTAS_COSTOS':
            return true; // mostrar todos los filtros para Reporte1 
      case 'REVISTAS_MAS_GUSTADAS':
              return true; // mostrar todos los filtros para Reporte1 
      default:
        return false;
    }
  }
  limpiarFiltros() {
    // Resetea solo los campos específicos y mantiene el nombre de usuario
    this.filtroForm.patchValue({
      fechaInicio: null,
      fechaFin: null,
      idRevista: null
    });
    console.log("Filtros limpiados");
  }
  // return campo !== 'nombreEditor';
}
