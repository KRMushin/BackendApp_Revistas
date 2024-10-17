import { Component, Input} from '@angular/core';
import { Anuncio } from '../../../interfaces/Anuncios/Anuncio';
import { CommonModule } from '@angular/common';
import { ConfiguracionAnuncioService } from '../../../service/Anuncios/configuracion-anuncio.service';

@Component({
  selector: 'app-anuncios-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './anuncios-list.component.html',
  styleUrl: './anuncios-list.component.css'
})
export class AnunciosListComponent {
  @Input() anuncios: Anuncio[] | undefined;

  constructor(private serviceAnuncios: ConfiguracionAnuncioService) { }

  actualizarAnuncio(idAnuncio: number, estado: boolean) : void {
    console.log(`Actualizando estado del anuncio ${idAnuncio} a ${estado}`);
    this.serviceAnuncios.actualizarEstadAnuncio(idAnuncio, estado).subscribe(() => {
      if (this.anuncios) {
        const anuncio = this.anuncios.find(anuncio => anuncio.idAnuncio === idAnuncio);
        if (anuncio) {
          anuncio.anuncioHabilitado = estado;
        }
      }
    })
  }
  
}
