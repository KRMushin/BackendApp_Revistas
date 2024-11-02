import { Component, OnInit } from '@angular/core';
import { Categoria } from '../../../../interfaces/Revistas/Categoria';
import { Etiqueta } from '../../../../interfaces/Revistas/Etiqueta';
import { CategoriasService } from '../../../../service/Revistas/categorias-service.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RevistasService } from '../../../../service/Revistas/revistas-service.service';
import { Revista } from '../../../../interfaces/Revistas/Revista';
import { FiltroNavegacion } from '../../../../interfaces/NavegacionRevistas/FiltroNavegacion';
import { CommonModule } from '@angular/common';
import { revistasNavegacion } from '../../../../service/RevistaNavegacion/RevistaNavegacion.service';
import { RevistaNavegacion } from '../../../../interfaces/NavegacionRevistas/RevistaNavegacion';
import { Router } from '@angular/router';
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';

@Component({
  selector: 'app-navegar-en-revistas',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './navegar-en-revistas.component.html',
  styleUrl: './navegar-en-revistas.component.css'
})
export class NavegarEnRevistasComponent implements OnInit{
  
  public categorias: Categoria[] = [];
  public etiquetas: Etiqueta[] = [];
  public revistas: RevistaNavegacion[] = [];

  public selectedCategorias: number[] = [];
  public selectedEtiquetas: number[] = [];
  public tipoFiltro: string = 'Todas';  

  public showCategoriasDropdown: boolean = false;
  public showEtiquetasDropdown: boolean = false;

  constructor(
    private catService: CategoriasService,
    private navService: revistasNavegacion,
    private router: Router,
    private anunciosService: ControladorAnunciosService 
  ) {}

  ngOnInit(): void {
    this.cargarEtiquetasCategorias();
  }

  cargarEtiquetasCategorias(): void {
    this.catService.obtenerCategorias().subscribe((categorias: Categoria[]) => {
      this.categorias = categorias;
    });
    this.catService.obtenerEtiquetas().subscribe((etiquetas: Etiqueta[]) => {
      this.etiquetas = etiquetas;
    });
  }

  toggleCategoria(id: number): void {
    if (this.selectedCategorias.includes(id)) {
      this.selectedCategorias = this.selectedCategorias.filter(cat => cat !== id);
    } else {
      this.selectedCategorias.push(id);
    }
  }

  toggleEtiqueta(id: number): void {
    if (this.selectedEtiquetas.includes(id)) {
      this.selectedEtiquetas = this.selectedEtiquetas.filter(etiqueta => etiqueta !== id);
    } else {
      this.selectedEtiquetas.push(id);
    }
  }

  toggleCategoriasDropdown(): void {
    this.showCategoriasDropdown = !this.showCategoriasDropdown;
  }

  toggleEtiquetasDropdown(): void {
    this.showEtiquetasDropdown = !this.showEtiquetasDropdown;
  }

  filtrarRevistas(): void {
    const filtro: FiltroNavegacion = {
      idRevista: null,
      tipoFiltro: 'REVISTAS_ACTIVAS',
      valoresFiltros: [],
      idCategoria: 0
    };

    switch (this.tipoFiltro) {
      case 'Categorias':
        filtro.valoresFiltros = this.selectedCategorias;
        filtro.tipoFiltro = 'REVISTAS_POR_CATEGORIA';
        break;
        case 'Etiquetas':
          filtro.valoresFiltros = this.selectedEtiquetas;
          filtro.tipoFiltro = 'REVISTAS_POR_ETIQUETAS';
          break;
          case 'Categoria y Etiquetas':
            if (this.selectedCategorias.length > 1 ) {
              alert('Solo puedes seleccionar una categoria para este filtro');
              return;
            }
            if (this.selectedEtiquetas.length == 0) {
              alert('Selecciona al menos una etiqueta para este filtro');
              return;
            }
            filtro.valoresFiltros = this.selectedEtiquetas;
            filtro.idCategoria = this.selectedCategorias[0];
            filtro.tipoFiltro = 'REVISTAS_POR_CATEGORIA_ETIQUETA';
        break;
      default:
        filtro.valoresFiltros = [];
        filtro.idCategoria = 0;
        break;
    }
    console.log(filtro);
    this.navService.obtenerRevistasNavegacionPorFiltro(filtro).subscribe((revistas: RevistaNavegacion[]) => {
      this.revistas = revistas;
      //limpiar despues de la busqueda
      console.log(this.revistas);
      this.selectedCategorias = [];
      this.selectedEtiquetas = [];
    });

  }

  previsualizar(idRevista: number): void {
    this.anunciosService.bloquearAnuncios();
      this.router.navigate(['/suscriptor-control/navegacionRevistas/detallesRevista', idRevista]);
  }
}
