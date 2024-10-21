import { Component, inject, Input, OnInit } from '@angular/core';
import { LlaveRevista } from '../../../interfaces/Revistas/LlaveRevista';
import { CommonModule } from '@angular/common';
import { utileriaToken } from '../../../service/utileria-token.service';
import { Router } from '@angular/router';

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
    private router = inject(Router); // Inyecta Router para navegar

    editorAutorizado: boolean = false;

    constructor() { }
    
    ngOnInit(): void {

      if (this.utileriaToken.obtenerRol() === 'EDITOR') {
        this.editorAutorizado = true;
      }
    }

    verRevista(idRevista: number) : void {
    }
    //editor
    detallesRevista(idRevista: number) : void {
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
