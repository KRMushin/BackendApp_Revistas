import { Component, inject, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';
import { CargarPdfRevistaService } from '../../../vistas-archivos/cargar-pdf-revista.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DarLikeRevistaComponent } from "../../../paginas-rol/suscriptor-control/dar-like-revista/dar-like-revista.component";
import { Like } from '../../../../interfaces/LIkes/Like';
import { LikesService } from '../../../../service/Comentarios/Likes.service';
import { utileriaToken } from '../../../../service/utileria-token.service';

@Component({
  selector: 'app-opciones-suscriptor',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './opciones-suscriptor.component.html',
  styleUrl: './opciones-suscriptor.component.css'
})
export class OpcionesSuscriptorComponent implements OnChanges{



  @Input() revista: any;
  private router = inject(Router); 
  public Like!: Like;
  public likeHecho: boolean = false;

  constructor(private cargarPdf: CargarPdfRevistaService,
    private controladorAnuncios:ControladorAnunciosService,
    private likesService: LikesService,
    private UtileriaToken: utileriaToken,
  ) { }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['revista'] && changes['revista'].currentValue) {
      this.obtenerEstadoLike();
    }
  }


    obtenerEstadoLike(): void {
    const nombreUsuario = this.UtileriaToken.obtenerNombreUsuario();
    if (nombreUsuario && this.revista) {
      this.likesService.obtnerLike(nombreUsuario, this.revista.idRevista).subscribe({
        next: (response: any) => {
          // Verificamos el estado de vacio en la respuesta
          if (response.empty) {
            this.likeHecho = false; 
            console.log("Usuario aún no ha dado like.");
          } else {
            this.likeHecho = true; 
            console.log("Estado del like obtenido:", response);
          }
        },
        error: (error) => {
          this.likeHecho = false; 
          console.log("No se encontró un like previo o hubo un error:", error);
        }
      });
    }
    }

    comentarRevista(idRevista: number, tituloRevista: string) : void {
      this.controladorAnuncios.bloquearAnuncios(); // esto para que el bloqueo lo decida la logica de negocio
      this.router.navigate(['/suscriptor-control/misSuscripciones/comentarRevista', idRevista, tituloRevista]);
    }
      
    verNumerosRevista(idRevista: number) : void {
      this.controladorAnuncios.bloquearAnuncios();
      this.router.navigate(['/suscriptor-control/misSuscripciones/verNumerosRevista', idRevista]);
    }

    calificarRevista(idRevista: number, tituloRevista: string) : void {
    }

    alertCalificacion() {
      alert('El autor ha desactivado likes..');
    }
    alertComentario() {
      alert('El autor ha desactivado comentarios..');
    }

    darLike(idRevista: number): void {
      const nombreUsuario = this.UtileriaToken.obtenerNombreUsuario();
      if (nombreUsuario) {
        const likeRevista = {
          idRevista: idRevista,
          nombreUsuario: nombreUsuario
        };
        console.log(likeRevista);
        this.likesService.publicarLike(likeRevista).subscribe({
          next: (response) => {
            this.Like = response; // Actualiza el estado a "like hecho"
            alert("Like dado");
            this.obtenerEstadoLike();
          },
          error: (error) => {
            console.log("Error al dar like:", error);
          }
        });
      }
    }
    verDetalles(arg0: any) {
      this.controladorAnuncios.bloquearAnuncios();
      this.router.navigate(['/suscriptor-control/detallesRevista', arg0]);
    }
    
}
