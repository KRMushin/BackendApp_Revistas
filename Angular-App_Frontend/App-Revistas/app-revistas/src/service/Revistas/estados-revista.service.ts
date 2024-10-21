import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { appSettings } from '../../settings/appSettings';
import { Observable } from 'rxjs';
import { Categoria } from '../../interfaces/Revistas/Categoria';
import { NuevoEstadoRevista } from '../../interfaces/Revistas/Estados/NuevoEstadoRevista';

@Injectable({
  providedIn: 'root'
})
export class EstadosRevistaService {
  
  private http = inject(HttpClient);
  private baseApiUrl = appSettings.apiUrl;

  constructor() { }

    public actualizarEstado(nuevoEstado: NuevoEstadoRevista): Observable<any> {
      console.log(nuevoEstado)
        return this.http.put<any>(`${this.baseApiUrl}estadoRevista/${nuevoEstado.idRevista}/actualizarEstado`, nuevoEstado);
    }


}