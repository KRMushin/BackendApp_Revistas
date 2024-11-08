import { Component, OnInit } from '@angular/core';
import { utileriaToken } from '../../../service/utileria-token.service';
import { PublicarNumeroRevistaComponent } from "../../paginas-rol/editor-control/publicar-numero-revista/publicar-numero-revista.component";
import { NumeroRevistasService } from '../../../service/numero-revistas.service';
import { NumeroRevista } from '../../../interfaces/numerosRevista/NumeroRevista';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ControladorAnunciosService } from '../../../service/Anuncios/controlador-anuncios.service';
import { ConfiguracionesRevista } from '../../../interfaces/Revistas/ConfiguracionesRevista';
import { RevistasService } from '../../../service/Revistas/revistas-service.service';
import { CargarPdfRevistaService } from '../../vistas-archivos/cargar-pdf-revista.service';

@Component({
  selector: 'app-numeros-revista-rol',
  standalone: true,
  imports: [PublicarNumeroRevistaComponent, CommonModule],
  templateUrl: './numeros-revista-rol.component.html',
  styleUrl: './numeros-revista-rol.component.css'
})
export class NumerosRevistaRolComponent implements OnInit{

  editorAutorizado!: boolean;
  suscriptorAutorizado!: boolean;
  numerosRevista?: NumeroRevista[];
  idRevista?: number;
  configuracionRevista?: ConfiguracionesRevista;

  constructor(private utileriaToken: utileriaToken,
              private numerosRevistaService: NumeroRevistasService,
              private route: ActivatedRoute,
              private controladorAnuncios: ControladorAnunciosService,
              private revistasService: RevistasService,
              private verPDF: CargarPdfRevistaService,
              private router: Router
  ){
    this.editorAutorizado = this.utileriaToken.autorizarEditor();
    this.suscriptorAutorizado = this.utileriaToken.autorizarSuscriptor();
  }

  ngOnInit(): void {
    this.controladorAnuncios.bloquearAnuncios();
    this.idRevista = Number(this.route.snapshot.params['idRevista']);
    this.cargarNumerosRevista();
  }
  
  cargarNumerosRevista(){
    if(this.idRevista == null){
      return;
    }
    this.numerosRevistaService.obtnerNumerosRevista(this.idRevista).subscribe({
      next: (response) => {
        this.numerosRevista = response;
      },
      error: (error) => {
        console.log('Error al obtener los números de la revista: ', error);
      }
    });  
    
    this.verificarBloqueos(this.idRevista);
    
  }

  verNumeroRevista(idnumero: number) : void {
    this.controladorAnuncios.bloquearAnuncios(); // esto para que el bloqueo lo decida la logica de negocio
    if(this.editorAutorizado){
      this.router.navigate(['/editor-control/misPublicaciones/verArchivoPDF', idnumero, 'numeroRevista_pdf']);
    }else{
      this.router.navigate(['/suscriptor-control/misSuscripciones/verArchivoPDF', idnumero, 'numeroRevista_pdf']);
    }

  }

  
  verificarBloqueos(id: number){
    this.revistasService.obtenerConfiguracionesRevista(id).subscribe({
      next: (response) => {
        this.configuracionRevista = response;
        if(this.configuracionRevista?.anunciosBloqueados){
          this.controladorAnuncios.bloquearAnuncios();
        }else{
          this.controladorAnuncios.permitirAnuncios();
        }
      },
      error: (error) => {
        console.log('Error al obtener la configuración de la revista: ', error);
      }
    })
    
  }
  regresar(){ 
    window.history.back();
  }
  ngOnDestroy(): void {
    this.controladorAnuncios.sincronizarEstadoAnuncios();
  }


}

