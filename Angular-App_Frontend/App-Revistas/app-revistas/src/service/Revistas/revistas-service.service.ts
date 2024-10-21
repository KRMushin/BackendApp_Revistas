import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { appSettings } from '../../settings/appSettings';
import { Observable } from 'rxjs';
import { Revista } from '../../interfaces/Revistas/Revista';
import { LlaveRevista } from '../../interfaces/Revistas/LlaveRevista';
import { RevistaDatosDTO } from '../../interfaces/Revistas/RevistaDatosDTO';
import { ConfiguracionesRevista } from '../../interfaces/Revistas/ConfiguracionesRevista';

@Injectable({
  providedIn: 'root'
})
export class RevistasService {
  private http = inject(HttpClient);
  private baseApiUrl = appSettings.apiUrl;

  constructor() { }

  public publicarRevista(revistaDTO: Revista): Observable<number> {
    return this.http.post<number>(`${this.baseApiUrl}revistas/publicar`, revistaDTO);
  }

  public publicarRevistaPDF(revistaPDF: File, idRevista: number): Observable<boolean> {
    const formData = new FormData();
    formData.append('revistaPDF', revistaPDF);  
    return this.http.post<boolean>(`${this.baseApiUrl}revistas/${idRevista}/guardarPDF`, formData);
  }

  public obtenerLlavesRevistasEditor(nombreUsuario: string): Observable<LlaveRevista[]> {
    return this.http.get<LlaveRevista[]>(`${this.baseApiUrl}revistas/${nombreUsuario}/publicaciones`);
  }

  public obtenerDatosRevista(idRevista: number): Observable<RevistaDatosDTO> {
    return this.http.get<RevistaDatosDTO>(`${this.baseApiUrl}revistas/${idRevista}/datosRevista`);
  }

  public obtenerConfiguracionesRevista(idRevista: number): Observable<ConfiguracionesRevista> {
    return this.http.get<ConfiguracionesRevista>(`${this.baseApiUrl}estadoRevista/${idRevista}/obtenerEstados`);
    // estadoRevista/2/obtenerEstados
  }
  
  
}
