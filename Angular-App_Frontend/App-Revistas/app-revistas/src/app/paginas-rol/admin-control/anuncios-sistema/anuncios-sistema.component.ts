import { Component, inject, OnInit } from '@angular/core';
import { utileriaToken } from '../../../../service/utileria-token.service';
import { ConfiguracionAnuncioService } from '../../../../service/Anuncios/configuracion-anuncio.service';
import { Anuncio } from '../../../../interfaces/Anuncios/Anuncio';
import { AnunciosListComponent } from "../../../component-list/anuncios-list/anuncios-list.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-anuncios-sistema',
  standalone: true,
  imports: [AnunciosListComponent, CommonModule],
  templateUrl: './anuncios-sistema.component.html',
  styleUrl: './anuncios-sistema.component.css'
})
export class AnunciosSistemaComponent implements OnInit{
  private utileriaToken = new utileriaToken();
  private serviceAnuncios = inject(ConfiguracionAnuncioService);
  
  public anuncios: Anuncio[] = [];
  public anunciosSinVigencia: Anuncio[] = [];
  public today: Date = new Date(); // Inicializa `today` con la fecha actual


  ngOnInit(): void {
    const nombreUsuario = this.utileriaToken.obtenerNombreUsuario();
    if (nombreUsuario) {
      this.serviceAnuncios.obtenerAnunciosEnSistema(nombreUsuario).subscribe({
        next: (anuncios: Anuncio[]) => this.anuncios = anuncios,
        error: (error) => console.error('Error fetching anuncios:', error)
      });

      this.serviceAnuncios.obtenerAnunciosSinVigencia(nombreUsuario).subscribe({
        next: (anuncios: Anuncio[]) => this.anunciosSinVigencia = anuncios,
        error: (error) => console.error('Error fetching anuncios sin vigencia:', error)
      });
    } else {
      console.error('Nombre de usuario es null');
    }
  }
  
}
