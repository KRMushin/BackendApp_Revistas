import { Component, inject, OnInit } from '@angular/core';
import { ConfiguracionAnuncio } from '../../../../interfaces/Anuncios/Configuracion-anuncio';
import { ConfiguracionAnuncioService } from '../../../../service/Anuncios/configuracion-anuncio.service';
import { CurrencyPipe, NgFor } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-anuncios-configuracion',
  standalone: true,
  imports: [NgFor, FormsModule, CurrencyPipe],
  templateUrl: './anuncios-configuracion.component.html',
  styleUrl: './anuncios-configuracion.component.css'
})
export class AnunciosConfiguracionComponent implements OnInit{

  private serviceConfig = inject(ConfiguracionAnuncioService);

  configuraciones: ConfiguracionAnuncio[] = [];

  ngOnInit(){
    this.obtenerConfiguraciones();
  }

  obtenerConfiguraciones(){
    this.serviceConfig.obtenerConfiguracionAnuncio().subscribe({
      next: (data) => {
        this.configuraciones = data;
      },
      error: (error) => {
        console.log('Error al obtener las configuraciones de anuncios:', error);
      }
    });
  }
  
  esNumero(valor: any): boolean {
    return typeof valor === 'number' && !isNaN(valor);
  }
  
  guardarCambios(configuracion: ConfiguracionAnuncio) {

    if(!this.esNumero(configuracion.precio)){
      alert('El precio del anuncio debe ser un número');
      return;
    }
    if(!this.esNumero(configuracion.tiempoDuracion)){
      alert('El precio de duracion por dia debe ser un número');
      return;
    }

    console.log('Configuración actualizada:', configuracion);
    
    this.serviceConfig.guardarConfiguracion(configuracion).subscribe({
      next: (response) => {
        alert('Se actualizo correctamente');
      },
      error: (error) => {
        alert('Error al guardar los cambios');
      }
    });



    
  }
}
