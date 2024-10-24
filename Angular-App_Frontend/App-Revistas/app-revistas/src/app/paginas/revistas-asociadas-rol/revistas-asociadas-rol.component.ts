import { Component, inject, Input, OnInit } from '@angular/core';
import { LlaveRevista } from '../../../interfaces/Revistas/LlaveRevista';
import { CommonModule } from '@angular/common';
import { utileriaToken } from '../../../service/utileria-token.service';
import { Router } from '@angular/router';
import { CargarPdfRevistaService } from '../../vistas-archivos/cargar-pdf-revista.service';
import { ControladorAnunciosService } from '../../../service/Anuncios/controlador-anuncios.service';

@Component({
  selector: 'app-revistas-asociadas-rol',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './revistas-asociadas-rol.component.html',
  styleUrl: './revistas-asociadas-rol.component.css'
})
export class RevistasAsociadasRolComponent implements OnInit{
    @Input() revistas: LlaveRevista[] | undefined;
    
    private utileriaToken = inject(utileriaToken);
    private router = inject(Router); 


    editorAutorizado: boolean = false;

    constructor(private cargarPdf: CargarPdfRevistaService,
                private controladorAnuncios:ControladorAnunciosService
    ) { }
    
    ngOnInit(): void {

      if (this.utileriaToken.obtenerRol() === 'EDITOR') {
        this.editorAutorizado = true;
      }
      
    }

    verRevista(idRevista: number) : void {
      this.cargarPdf.cargarPDFEnOtraVentana(idRevista);
    }
    //editor
    detallesRevista(idRevista: number) : void {
      this.controladorAnuncios.bloquearAnuncios();
      this.router.navigate(['/editor-control/misPublicaciones/detallesRevista', idRevista]);
    }
    
    comprarBloqueoAnuncios(idRevista: number) : void {
    }
    // suscriptor
    comentarRevista(idRevista: number) : void {
      
    }
    previsualizarRevista(idRevista: number) : void {
    }

    
}
