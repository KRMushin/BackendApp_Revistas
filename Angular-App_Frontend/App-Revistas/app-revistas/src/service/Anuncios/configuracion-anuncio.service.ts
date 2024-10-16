import { inject, Injectable } from '@angular/core';
import { appSettings } from '../../settings/appSettings';
import { HttpClient } from '@angular/common/http';
import { ConfiguracionAnuncio } from '../../interfaces/Anuncios/Configuracion-anuncio';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfiguracionAnuncioService {

  private http = inject(HttpClient);
  private baseUrl: String = appSettings.apiUrl; // api url de la clase appSettings raiz del proyecto

  constructor() { }

  public obtenerConfiguracionAnuncio(): Observable<ConfiguracionAnuncio[]> {
    return this.http.get<ConfiguracionAnuncio[]>(`${this.baseUrl}anuncios/configuraciones`);
  }
  
  public guardarConfiguracion(objeto: ConfiguracionAnuncio): Observable<ConfiguracionAnuncio> {
    return this.http.put<ConfiguracionAnuncio>(`${this.baseUrl}anuncios/configuraciones`, objeto);
  }

  public comprarAnuncio(formData: FormData): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}anuncios`, formData);
  }


}
