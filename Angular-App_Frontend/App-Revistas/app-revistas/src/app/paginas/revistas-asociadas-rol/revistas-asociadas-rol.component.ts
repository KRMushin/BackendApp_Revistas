import { Component, inject, Input, OnInit } from '@angular/core';
import { LlaveRevista } from '../../../interfaces/Revistas/LlaveRevista';
import { CommonModule } from '@angular/common';
import { utileriaToken } from '../../../service/utileria-token.service';
import { Router } from '@angular/router';
import { CargarPdfRevistaService } from '../../vistas-archivos/cargar-pdf-revista.service';
import { ControladorAnunciosService } from '../../../service/Anuncios/controlador-anuncios.service';
import { OpcionesEditorRevistaComponent } from "./opciones-editor-revista/opciones-editor-revista.component";
import { OpcionesSuscriptorComponent } from './opciones-suscriptor/opciones-suscriptor.component';

@Component({
  selector: 'app-revistas-asociadas-rol',
  standalone: true,
  imports: [CommonModule, OpcionesEditorRevistaComponent, OpcionesSuscriptorComponent],
  templateUrl: './revistas-asociadas-rol.component.html',
  styleUrl: './revistas-asociadas-rol.component.css'
})
export class RevistasAsociadasRolComponent implements OnInit{
    @Input() revistas: LlaveRevista[] | undefined;
    
    private utileriaToken = inject(utileriaToken);
    private router = inject(Router); 


    editorAutorizado: boolean = false;
    suscriptorAutorizado: boolean = false;

    constructor(private cargarPdf: CargarPdfRevistaService,
                private controladorAnuncios:ControladorAnunciosService
    ) { }
    
    ngOnInit(): void {
      this.controladorAnuncios.permitirAnuncios();
      if (this.utileriaToken.obtenerRol() === 'EDITOR') {
        this.editorAutorizado = true;
        
      }else if(this.utileriaToken.obtenerRol() === 'SUSCRIPTOR'){
        this.suscriptorAutorizado = true;
      }
      
    }

    verRevista(idRevista: number) : void {
      this.controladorAnuncios.bloquearAnuncios(); // esto para que el bloqueo lo decida la logica de negocio
      
      if(this.editorAutorizado){
        this.router.navigate(['/editor-control/misPublicaciones/verArchivoPDF', idRevista, 'revista_pdf']);
      }else if(this.suscriptorAutorizado){
        this.router.navigate(['/suscriptor-control/misSuscripciones/verArchivoPDF', idRevista, 'revista_pdf']);
      }
    }
    comentarRevista(idRevista: number) : void {
      
    }
    previsualizarRevista(idRevista: number) : void {
    }

    
}
