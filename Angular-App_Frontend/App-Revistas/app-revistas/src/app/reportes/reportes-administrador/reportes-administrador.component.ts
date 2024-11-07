import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { FiltroEditorDTO } from '../FiltroEditorDTO';
import { LlaveRevista } from '../../../interfaces/Revistas/LlaveRevista';
import { RevistasService } from '../../../service/Revistas/revistas-service.service';
import { utileriaToken } from '../../../service/utileria-token.service';
import { FiltroAdminDTO } from './FiltroAdminDTO';
import { UsuariosService } from '../../../service/Usuarios/usuarios-service.service';
import { CommonModule } from '@angular/common';
import { RepGananciasSistemaComponent } from "./rep-ganancias-sistema/rep-ganancias-sistema.component";
import { RepAnunciosCompradosComponent } from "./rep-anuncios-comprados/rep-anuncios-comprados.component";
import { RepGananciasAnunciantesComponent } from "./rep-ganancias-anunciantes/rep-ganancias-anunciantes.component";
import { RepRevistasMasPopularesComponent } from "./rep-revistas-mas-populares/rep-revistas-mas-populares.component";
import { RepEfectividadAnunciantesComponent } from "./rep-efectividad-anunciantes/rep-efectividad-anunciantes.component";

@Component({
  selector: 'app-reportes-administrador',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule, RepGananciasSistemaComponent, RepAnunciosCompradosComponent, RepGananciasAnunciantesComponent, RepRevistasMasPopularesComponent, RepEfectividadAnunciantesComponent],
  templateUrl: './reportes-administrador.component.html',
  styleUrl: './reportes-administrador.component.css'
})
export class ReportesAdministradorComponent implements OnInit {
  
  filtroForm: FormGroup; // Formulario reactivo para los filtros
  filtroAplicado!: FiltroAdminDTO; 
  reporteSeleccionado!: string; // Tipo de reporte seleccionado
  llavesRevistas: LlaveRevista[] = []; // Lista de revistas
  usuarios: String[] = [];

  constructor(private fb: FormBuilder, private UtileriaToken: utileriaToken, 
              private usuariosService: UsuariosService
  ) {
    this.filtroForm = this.fb.group({
      fechaInicio: [null],
      fechaFin: [null],
      tipoAnuncio: [null],
      diasPeriodo: [null, [Validators.min(1), Validators.pattern("^[0-9]*$")]], // Solo números positivos
      nombreAnunciante: [null],
    });
  }

  ngOnInit(): void {
    this.usuariosService.obtenerUsuarios().subscribe({
      next: (usuarios: String[]) => this.usuarios = usuarios,
      error: (error) => console.error('Error al obtener usuarios:', error)
    });
  
  }
  aplicarFiltros() {
    this.filtroAplicado = {
      tipoReporte: this.reporteSeleccionado,
      fechaInicio: this.filtroForm.value.fechaInicio,
      fechaFin: this.filtroForm.value.fechaFin,
      tipoAnuncio: this.filtroForm.value.tipoAnuncio,
      diasPeriodo: this.filtroForm.value.diasPeriodo,
      nombreAnunciante: this.filtroForm.value.nombreAnunciante,
    };
    console.log("Filtros aplicados:", this.filtroAplicado);
  }

  // Método para mostrar u ocultar campos de acuerdo al reporte seleccionado
  mostrarFiltro(campo: string): boolean {
    switch (this.reporteSeleccionado) {
      case 'REPORTE_GANANCIAS':
        return campo === 'fechaInicio' || campo === 'fechaFin';
        
      case 'REVISTAS_MAS_COMENTADAS':
        return campo === 'fechaInicio' || campo === 'fechaFin' || campo === 'nombreAnunciante';
        
      case 'REVISTAS_MAS_POPULARES':
        return campo === 'fechaInicio' || campo === 'fechaFin';
        
      case 'ANUNCIOS_COMPRADOS':
        return campo === 'diasPeriodo' || campo === 'tipoAnuncio' ||campo === 'fechaInicio' || campo === 'fechaFin';
        
      case 'GANANCIAS_ANUNCIANTES':
        return campo === 'nombreAnunciante';
      case 'EFECTIVIDAD_ANUNCIOS':
        return campo === 'nombreAnunciante' ||campo === 'fechaInicio' || campo === 'fechaFin';
        
      default:
        return false;
    }
  }

  limpiarFiltros() {
    this.filtroForm.patchValue({
      fechaInicio: null,
      fechaFin: null,
      tipoAnuncio: null,
      diasPeriodo: null,
      nombreAnunciante: null,
    });
    console.log("Filtros limpiados");
  }
}

