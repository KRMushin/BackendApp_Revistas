import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { appSettings } from '../../settings/appSettings';
import { Observable } from 'rxjs';
import { LlaveAnuncioDTO } from '../../interfaces/Anuncios/LlaveAnuncioDTO';

@Injectable({
  providedIn: 'root'
})
export class DespliegueAnunciosService {

  private http = inject(HttpClient);
  private baseUrl: String = appSettings.apiUrl;

  public obtnerAnuncioAleatorio(): Observable<LlaveAnuncioDTO> {
    return this.http.get<LlaveAnuncioDTO>(`${this.baseUrl}anunciosDespliegue/anuncioAleatorio`);
  }

  public obtenerArchivoPorId(idAnuncio: number): Observable<Blob> {
    return this.http.get(`${this.baseUrl}anunciosDespliegue/${idAnuncio}/archivo`, { responseType: 'blob' });
  }
}
