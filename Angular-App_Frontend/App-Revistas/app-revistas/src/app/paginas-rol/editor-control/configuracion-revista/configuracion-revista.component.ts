import { Component, inject, Input, OnInit } from '@angular/core';
import { RevistasService } from '../../../../service/Revistas/revistas-service.service';
import { ConfiguracionesRevista } from '../../../../interfaces/Revistas/ConfiguracionesRevista';
import { CommonModule } from '@angular/common';
import { NuevoEstadoRevista } from '../../../../interfaces/Revistas/Estados/NuevoEstadoRevista';
import { EstadosRevistaService } from '../../../../service/Revistas/estados-revista.service';

@Component({
  selector: 'app-configuracion-revista',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './configuracion-revista.component.html',
  styleUrl: './configuracion-revista.component.css'
})
export class ConfiguracionRevistaComponent implements OnInit{
  
  @Input() idRevista!: number;

  ConfiguracionRevista!: ConfiguracionesRevista; 
  
  private revistasService = inject(RevistasService);
  private estadosRevistaService = inject(EstadosRevistaService);

  ngOnInit(): void {
    this.cargarConfiguracion();
  }
  cargarConfiguracion(){
    this.revistasService.obtenerConfiguracionesRevista(this.idRevista).subscribe({
      next: (configuracionRevista: ConfiguracionesRevista) => {
        this.ConfiguracionRevista = configuracionRevista;
      },
      error: (error: any) => {
        console.log(error);
      }
    })
  }
  actualizarSuscripciones(): void {
    this.actualizarEstado(this.ConfiguracionRevista.aceptaSuscripciones, this.idRevista, "SUSCRIPCIONES");
  }

  actualizarComentarios(): void {
    this.actualizarEstado(this.ConfiguracionRevista.esComentable, this.idRevista, "COMENTARIOS");
  }

  actualizarLikes(): void {
    this.actualizarEstado(this.ConfiguracionRevista.esLikeable, this.idRevista, "LIKES");
  }

  private actualizarEstado(estadoActual: boolean, idRevista: number, estadoN: string): void {
    if (!idRevista || idRevista <= 0 || typeof estadoActual !== 'boolean') {
      console.error('Datos inválidos');
      return;
    }

    const nuevoEstadoRevista: NuevoEstadoRevista = {
      idRevista: idRevista,
      estado: !estadoActual,
      tipoEstado: estadoN
    };


    this.estadosRevistaService.actualizarEstado(nuevoEstadoRevista).subscribe({
      next: () => {
        this.cargarConfiguracion();
      },
      error: (error) => {
        console.error('Error al actualizar el estado', error);
      }
    });
  }

}
