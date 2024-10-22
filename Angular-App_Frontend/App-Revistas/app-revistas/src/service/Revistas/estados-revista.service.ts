import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { appSettings } from '../../settings/appSettings';
import { Observable } from 'rxjs';
import { NuevoEstadoRevista } from '../../interfaces/Revistas/Estados/NuevoEstadoRevista';
import { RevistaDatosDTO } from '../../interfaces/Revistas/RevistaDatosDTO';
import { CostoNuevoRevista} from '../../interfaces/Revistas/CostoNuevoRevista';

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

    public obtenerCostosRevistas(parametro: string): Observable<RevistaDatosDTO[]> {
      return this.http.get<RevistaDatosDTO[]>(`${this.baseApiUrl}revistasPorParametro/${parametro}/listar`);
    }  
    
    public activarRevista(idRevista: number): Observable<any> {
      console.log(idRevista)
        return this.http.put<any>(`${this.baseApiUrl}revistas/activarRevista/${idRevista}`, idRevista);
    }

    public actualizarCosto(costo: CostoNuevoRevista): Observable<any> {
        return this.http.put<any>(`${this.baseApiUrl}estadoRevista/actualizarCostoRevista`, costo);
    }



}