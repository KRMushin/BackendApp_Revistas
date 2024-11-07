import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { DespliegueAnunciosService } from '../../../service/Anuncios/despliegue-anuncios.service';
import { LlaveAnuncioDTO } from '../../../interfaces/Anuncios/LlaveAnuncioDTO';
import { CommonModule } from '@angular/common';
import { ControladorAnunciosService } from '../../../service/Anuncios/controlador-anuncios.service';
import { AnuncioVisualizacion } from '../../../service/Anuncios/AnuncioVisualizacion';

@Component({
  selector: 'app-barra-lateral-anuncio',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './barra-lateral-anuncio.component.html',
  styleUrl: './barra-lateral-anuncio.component.css'
})
export class BarraLateralAnuncioComponent implements OnInit{

  @ViewChild('videoRef') videoElement!: ElementRef;

  LlaveAnuncio!: LlaveAnuncioDTO;
  anuncioVideo?: string;
  anuncioImagen?: string;
  cargarAnuncio?: boolean;

  mostrarAnuncios?: boolean;

    constructor(private despliegueService: DespliegueAnunciosService,
              private controladorAnuncios: ControladorAnunciosService,
              private cdr: ChangeDetectorRef
    ) { }
    
    ngOnInit(): void {
      // obteniendo el estado global
      this.controladorAnuncios.estadoAnuncios$.subscribe((permitirAnuncios: boolean) => {
        this.mostrarAnuncios = permitirAnuncios;
        
        if (permitirAnuncios) {
            this.cargarAnuncioAleatorio(); 
        } else {
          console.log('Anuncios bloqueados.');
          this.cargarAnuncio = false;
        }
          this.cdr.detectChanges();
    });

    this.controladorAnuncios.suscribirseRecargaAnuncios(() => {
      if (this.mostrarAnuncios) {
        this.cargarAnuncioAleatorio();  
        this.cargarAnuncio = true;
      }
    });
    
  }
  cargarAnuncioAleatorio(){
      
      this.despliegueService.obtnerAnuncioAleatorio().subscribe({
        next: (response: LlaveAnuncioDTO) => {
          this.LlaveAnuncio = response;
          this.cargarAnuncio = true;
          if(this.LlaveAnuncio.tipoAnuncio != 'TEXTO'){
            this.obtenerArchivo(this.LlaveAnuncio);
          }
          console.log('Anuncio cargado:', this.LlaveAnuncio.idAnuncio);
          this.guardarVisualizacion(this.LlaveAnuncio);
        },
        error: (error: any) => {
          console.error('Error al cargar el anuncio:', error);
        }
      });
    }

    obtenerArchivo(LlaveAnuncio: LlaveAnuncioDTO) {
      this.despliegueService.obtenerArchivoPorId(LlaveAnuncio.idAnuncio).subscribe({
        next: (archivoBlob: Blob) => {
          if(LlaveAnuncio.tipoAnuncio == 'IMAGEN_TEXTO'){
            this.anuncioImagen = URL.createObjectURL(archivoBlob);  
          }else if(LlaveAnuncio.tipoAnuncio == 'VIDEO'){
            this.anuncioVideo = URL.createObjectURL(archivoBlob);  
          }
        },
        error: (error: any) => {
          console.error('Error al obtener el archivo:', error);
        }
      });
    }
    onVideoLoaded(event: Event) {
      const video = event.target as HTMLVideoElement;
      video.muted = true; 
      video.play(); 
      console.log('Video cargado un ');
    }

    onVideoEnded(event: Event) {
      const video = event.target as HTMLVideoElement;
      video.currentTime = 0; 
      video.play();
    }

    guardarVisualizacion(LlaveAnuncio: LlaveAnuncioDTO) {
      const AnuncioVisualizacion: AnuncioVisualizacion = {
        idAnuncio: LlaveAnuncio.idAnuncio,
        ruta: window.location.href
      }
      this.despliegueService.guardarVisualizacion(AnuncioVisualizacion).subscribe({
        next: (response: any) => {
          console.log('Visualización guardada:', LlaveAnuncio.idAnuncio);
        },
        error: (error: any) => {
          console.error('Error al guardar la visualización:', error);
        }
      });
    }

  

  }


