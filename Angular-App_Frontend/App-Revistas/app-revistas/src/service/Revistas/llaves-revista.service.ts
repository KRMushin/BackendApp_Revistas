import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { appSettings } from "../../settings/appSettings";
import { Observable } from "rxjs";
import { LlaveRevista } from "../../interfaces/Revistas/LlaveRevista";

@Injectable({
    providedIn: 'root'
  })
  export class LlavesRevistaService {
    private http = inject(HttpClient);
    private baseApiUrl = appSettings.apiUrl;

    public obtenerLlavePorIdRevista(idRevista: number): Observable<LlaveRevista> {
        return this.http.get<LlaveRevista>(`${this.baseApiUrl}revistasPorParametro/${idRevista}/llaveRevista`);
    }

  }