import { inject, Injectable } from '@angular/core';
import { appSettings } from '../../settings/appSettings';
import { HttpClient } from '@angular/common/http';
import { ConfiguracionAnuncio } from '../../interfaces/Anuncios/Configuracion-anuncio';
import { Observable } from 'rxjs';
import { CarteraDigital } from '../../interfaces/Usuarios/CarteraDigital';

@Injectable({
  providedIn: 'root'
})
export class CarteraDigitalService {

  private http = inject(HttpClient);
  private baseUrl: String = appSettings.apiUrl; // api url de la clase appSettings raiz del proyecto

  constructor() { }

  public obtenerDatosUsuario(nombreUsuario: string): Observable<CarteraDigital> {
    return this.http.get<CarteraDigital>(`${this.baseUrl}cartera/digital/${nombreUsuario}`);
  }
  public recargarCartera(cartera: CarteraDigital): Observable<any> {
    return this.http.put(`${this.baseUrl}cartera/digital`, cartera);
  }

}
