import { Component, OnInit } from '@angular/core';
import { CargarPdfRevistaService } from '../cargar-pdf-revista.service';
import { ActivatedRoute } from '@angular/router';
import { ControladorAnunciosService } from '../../../service/Anuncios/controlador-anuncios.service';
import { RevistasService } from '../../../service/Revistas/revistas-service.service';
import { ConfiguracionesRevista } from '../../../interfaces/Revistas/ConfiguracionesRevista';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-ver-pdf',
  standalone: true,
  imports: [],
  templateUrl: './ver-pdf.component.html',
  styleUrl: './ver-pdf.component.css'
})
export class VerPdfComponent implements OnInit{

  private idRevista!: number;
  private tipoArchivo!: string;
  public urlSource: SafeResourceUrl | null = null;


  
  constructor(private serviceArchivo: CargarPdfRevistaService,
              private route: ActivatedRoute,
              private controladorAnuncios: ControladorAnunciosService,
              private revistasService: RevistasService,
              private seguridadPDF: DomSanitizer
  ){ }

  ngOnInit(): void {
    this.idRevista = this.route.snapshot.params['idRevista'];
    this.tipoArchivo = this.route.snapshot.params['tipoArchivo'];
    if(this.idRevista != null && this.tipoArchivo != null){
      this.configurarAnuncios();
      this.cargarArchivoPDf();
    }
  }

  configurarAnuncios(){
    this.revistasService.obtenerConfiguracionesRevista(this.idRevista).subscribe((data: ConfiguracionesRevista) => {
      if(data.anunciosBloqueados){
        this.controladorAnuncios.bloquearAnuncios();
      }else{
        this.controladorAnuncios.permitirAnuncios();
      }
    });
  }
  cargarArchivoPDf(){
    if(this.tipoArchivo == 'revista_pdf'){
      this.serviceArchivo.obtenerPDFRevista(this.idRevista).subscribe((url: string) => {
        this.urlSource = this.seguridadPDF.bypassSecurityTrustResourceUrl(url);
      });
    }else if(this.tipoArchivo == 'numeroRevista_pdf'){
      this.serviceArchivo.obtenerPDFNumeroRevista(this.idRevista).subscribe((url: string) => {
        this.urlSource = this.seguridadPDF.bypassSecurityTrustResourceUrl(url);
      });
    }
  }

  regresar(){
    window.history.back();
  }

  ngOnDestroy(): void {
    this.controladorAnuncios.permitirAnuncios();
  }

  
}
