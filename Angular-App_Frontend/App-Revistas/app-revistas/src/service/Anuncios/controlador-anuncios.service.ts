import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

// clase encarcagada de manejar el control de las barras domo por ejemplo el bloqueo de anuncios

export class ControladorAnunciosService {

  private estadoAnunciosSubject = new BehaviorSubject<boolean>(true); //mostrar anuncios
  estadoAnuncios$ = this.estadoAnunciosSubject.asObservable();

  private recargarAnuncio = new BehaviorSubject<boolean>(false);
  recargarAnuncios$ = this.recargarAnuncio.asObservable();

  constructor() {}

  permitirAnuncios(): void {
    this.estadoAnunciosSubject.next(true);  //actualizar estado
  }

  bloquearAnuncios(): void {
    this.estadoAnunciosSubject.next(false);  //bloquear estado
  }

  recargarAnuncios(): void {
    this.recargarAnuncio.next(true);
    setTimeout(() => this.recargarAnuncio.next(false), 0); // reiniciar el estado
  }

  // recargarAnuncios(): void {
  //   this.recargarAnuncio.next(true);
  //   setTimeout(() => this.recargarAnuncio.next(false), 0); // reiniciar el estado
  // }

  suscribirseRecargaAnuncios(callback: () => void): void {
    this.recargarAnuncios$.subscribe((recargar: boolean) => {
      if (recargar) {
        callback(); 
      }
    });
  }
  
}
