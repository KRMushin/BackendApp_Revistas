import { inject, Injectable } from "@angular/core";
import { appSettings } from "../../settings/appSettings";
import { HttpClient } from "@angular/common/http";
import { CompraBloqueo } from "../../interfaces/Revistas/Compras/CompraBloqueo";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
export class ComprasRevistaService {
  private http = inject(HttpClient);
  private baseApiUrl = appSettings.apiUrl;

  constructor() { }

  public comprarBloqueoRevista(compra: CompraBloqueo): Observable<any> {
    return this.http.post<any>(`${this.baseApiUrl}bloqueosRevista/comprarBloqueo`, compra);
  }

}