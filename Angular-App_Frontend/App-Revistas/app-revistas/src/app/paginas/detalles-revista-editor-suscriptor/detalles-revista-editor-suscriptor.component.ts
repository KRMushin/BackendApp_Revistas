import { Component, inject, Inject, Input, OnInit } from '@angular/core';
import { RevistasService } from '../../../service/Revistas/revistas-service.service';
import { CommonModule } from '@angular/common';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { RevistaDatosDTO } from '../../../interfaces/Revistas/RevistaDatosDTO';
import { utileriaToken } from '../../../service/utileria-token.service';
import { ConfiguracionRevistaComponent } from "../../paginas-rol/editor-control/configuracion-revista/configuracion-revista.component";
import { Categoria } from '../../../interfaces/Revistas/Categoria';
import { CategoriasService } from '../../../service/Revistas/categorias-service.service';
import { ControladorAnunciosService } from '../../../service/Anuncios/controlador-anuncios.service';



@Component({
  selector: 'app-detalles-revista-editor-suscriptor',
  standalone: true,
  imports: [CommonModule, ConfiguracionRevistaComponent],
  templateUrl: './detalles-revista-editor-suscriptor.component.html',
  styleUrl: './detalles-revista-editor-suscriptor.component.css'
})
export class DetallesRevistaEditorSuscriptorComponent implements OnInit{
  revista!: RevistaDatosDTO;
  categoria!: Categoria;
  idRevista!: number;
  editorAutorizado: boolean = false;

  constructor(
    private ruta: ActivatedRoute,
    private location: Location,
    private utileriaToken: utileriaToken,
    private serviceCategoria: CategoriasService) {}

    private revistasService = inject(RevistasService);
    private controladorAnuncios = inject(ControladorAnunciosService)

  ngOnInit(): void {
    this.controladorAnuncios.bloquearAnuncios();
    this.idRevista = Number(this.ruta.snapshot.paramMap.get('idRevista'));

    this.revistasService.obtenerDatosRevista(this.idRevista).subscribe({
      
      next: (revista: RevistaDatosDTO) => {
        if(revista.bloquearAnuncios){
          this.controladorAnuncios.bloquearAnuncios();
        }else{
          this.controladorAnuncios.permitirAnuncios();
        }
        this.revista = revista;
      },
      error: (err) => {
        console.error('Error al cargar los datos de la revista', err);
      }
    });

    this.serviceCategoria.obtnerCategoriaEtiqueta(this.idRevista).subscribe({
      next: (categoria: Categoria) => {
        this.categoria = categoria;
      },
      error: (err) => {
        console.error('Error al cargar los datos de la categoria', err);
      },
    });

    if(this.utileriaToken.autorizarEditor()){
      this.editorAutorizado = true;
    }
  }
  ngOnDestroy(): void {
    this.controladorAnuncios.permitirAnuncios();
  }

  goBack(): void {
    this.location.back();
  }
}
