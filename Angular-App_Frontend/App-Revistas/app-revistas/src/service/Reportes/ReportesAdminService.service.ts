import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { FiltroAdminDTO } from "../../app/reportes/reportes-administrador/FiltroAdminDTO";
import { Observable } from "rxjs";
import { appSettings } from "../../settings/appSettings";

@Injectable({
    providedIn: 'root'
})
export class ReportesAdminService {
    
    
    private http = inject(HttpClient);
    private baseUrl: String = appSettings.apiUrl;
    
    
    public obtnerReporteGananciasSistema(filtro: FiltroAdminDTO): Observable<any>{
        return this.http.post(`${this.baseUrl}reportesAdmin/ganaciasSistema`, filtro);
    }
    obtenerReporteAnuncios(filtro: FiltroAdminDTO): Observable<any> {
        return this.http.post(`${this.baseUrl}reportesAdmin/anunciosComprados`, filtro);
    }
    obtenerGananciasAnunciantes(filtro: FiltroAdminDTO): Observable<any> {
        return this.http.post(`${this.baseUrl}reportesAdmin/gananciasAnunciantes`, filtro);
    }

    obtenerReporteSuscripciones(filtro: FiltroAdminDTO): Observable<any> {
        return this.http.post(`${this.baseUrl}reportesAdmin/revistasPopulares`, filtro);
    }
}
   