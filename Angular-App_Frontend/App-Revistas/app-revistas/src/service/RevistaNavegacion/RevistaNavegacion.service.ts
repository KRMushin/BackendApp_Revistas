import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { appSettings } from "../../settings/appSettings";
import { RevistaNavegacion } from "../../interfaces/NavegacionRevistas/RevistaNavegacion";
import { Observable } from "rxjs";
import { FiltroNavegacion } from "../../interfaces/NavegacionRevistas/FiltroNavegacion";

@Injectable({

    providedIn: 'root'
})

export class revistasNavegacion{

    private http = inject(HttpClient);
    private baseUrl: String = appSettings.apiUrl;


    public obtenerRevistasNavegacionPorFiltro(filtro: FiltroNavegacion): Observable<RevistaNavegacion[]> {
        return this.http.post<RevistaNavegacion[]>(`${this.baseUrl}revistasPorParametro/navegacion/revistas`, filtro);
    }

}
