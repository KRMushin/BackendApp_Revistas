import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { appSettings } from '../../settings/appSettings';
import { Observable } from 'rxjs';
import { PrecioGlobal } from '../../interfaces/Precios/PrecioGlobal';
@Injectable({
  providedIn: 'root'
})
export class preciosGlobales {
  private http = inject(HttpClient);
  private baseUrl: String = appSettings.apiUrl; // api url de la clase appSettings raiz del proyecto
  
  public obtenerPreciosGlobales(): Observable<PrecioGlobal[]> {
    return this.http.get<PrecioGlobal[]>(`${this.baseUrl}preciosGlobales`);
  }

  public actualizarPrecio(idPrecio: number, nuevoPrecio: number): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}preciosGlobales/${idPrecio}/actualizar`, nuevoPrecio);
  }
}