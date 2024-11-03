import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { appSettings } from "../../settings/appSettings";
import { LlaveAnuncioDTO } from "../../interfaces/Anuncios/LlaveAnuncioDTO";
import { Observable } from "rxjs";
import { LlaveRevista } from "../../interfaces/Revistas/LlaveRevista";

@Injectable({
    providedIn: 'root'
  })
export class SuscripcionesService {
    private http = inject(HttpClient);
    private baseApiUrl = appSettings.apiUrl;
    
    constructor() { }
    
    public obtenerRevistasSuscriptor(nombreUsuario: string): Observable<LlaveRevista[]> {
        return this.http.get<LlaveRevista[]>(`${this.baseApiUrl}suscripciones/revistas/${nombreUsuario}`);
    }
    
    suscribirseARevista(datosSuscripcion: any): Observable<any> {
        return this.http.post(`${this.baseApiUrl}suscripciones/suscribirse/revista`, datosSuscripcion);
    }
}

