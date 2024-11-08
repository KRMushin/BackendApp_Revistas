import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

// clase encarcagada de manejar el control de las barras domo por ejemplo el bloqueo de anuncios

export class ControladorAnunciosService {

  private estadoAnunciosSubject = new BehaviorSubject<boolean>(this.obtenerEstadoAnuncios()); //mostrar anuncios
  estadoAnuncios$ = this.estadoAnunciosSubject.asObservable();

  private recargarAnuncio = new BehaviorSubject<boolean>(false);
  recargarAnuncios$ = this.recargarAnuncio.asObservable();


  permitirAnuncios(): void {
    this.actualizarEstadoAnuncios(true);

    // this.estadoAnunciosSubject.next(true);  //actualizar estado
  }

  bloquearAnuncios(): void {
    this.actualizarEstadoAnuncios(false);
    // this.estadoAnunciosSubject.next(false);  //bloquear estado
  }

  recargarAnuncios(): void {
    this.recargarAnuncio.next(true);
    setTimeout(() => this.recargarAnuncio.next(false), 0); // reiniciar el estado
  }

  suscribirseRecargaAnuncios(callback: () => void): void {
    this.recargarAnuncios$.subscribe((recargar: boolean) => {
      if (recargar) {
        callback(); 
      }
    });
  }

  private actualizarEstadoAnuncios(mostrar: boolean): void {
    localStorage.setItem('estadoAnuncios', JSON.stringify(mostrar));
    this.estadoAnunciosSubject.next(mostrar);
  }

  private obtenerEstadoAnuncios(): boolean {
    const estado = localStorage.getItem('estadoAnuncios');
    return estado !== null ? JSON.parse(estado) : true; // Por defecto, mostrar anuncios
  }

  sincronizarEstadoAnuncios(): void {
    const estadoActual = this.obtenerEstadoAnuncios();
    this.estadoAnunciosSubject.next(estadoActual);
  }
  
}
